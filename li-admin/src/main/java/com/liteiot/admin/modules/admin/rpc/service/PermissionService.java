package com.liteiot.admin.modules.admin.rpc.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liteiot.admin.modules.admin.biz.*;
import com.liteiot.common.bmsenum.OpenApiRespCode;
import com.liteiot.common.constant.AdminCommonConstant;
import com.liteiot.admin.modules.admin.entity.*;
import com.liteiot.admin.modules.admin.util.Sha256PasswordEncoder;
import com.liteiot.admin.modules.auth.util.user.JwtTokenUtil;
import com.liteiot.api.vo.authority.CheckPermissionInfo;
import com.liteiot.api.vo.authority.PermissionInfo;
import com.liteiot.api.vo.user.UserInfo;
import com.liteiot.common.constant.RedisKeyConstant;
import com.liteiot.common.context.BaseContextHandler;
import com.liteiot.common.exception.BaseException;
import com.liteiot.common.exception.auth.UserInvalidException;
import com.liteiot.common.util.TreeUtil;
import com.liteiot.common.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 权限接口
 */
@Service
public class PermissionService {

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private MenuBiz menuBiz;

    @Autowired
    private ElementBiz elementBiz;

    @Autowired
    private JwtTokenUtil userAuthUtil;

    private Sha256PasswordEncoder encoder = new Sha256PasswordEncoder();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RoleBiz roleBiz;

    @Autowired
    private GroupBiz groupBiz;

    private UserInfo getUserByUsername(String username) {
        UserInfo info = new UserInfo();
        User user = userBiz.getUserByUsername(username);
        BeanUtils.copyProperties(user, info);
        info.setId(user.getId().toString());
        return info;
    }

    public UserInfo validate(String username, String password) {
        UserInfo info = new UserInfo();
        User user = userBiz.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UserInvalidException("用户不存在!");
        }
        if (encoder.matches(password, user.getPassword())) {
            BeanUtils.copyProperties(user, info);
            info.setId(user.getId().toString());
        } else {
            throw new UserInvalidException("账户密码错误!");
        }
        return info;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User validateOpen(String username, String password) {
        User user = userBiz.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new BaseException(OpenApiRespCode.USER_NOT_EXIST.getMsg(), OpenApiRespCode.USER_NOT_EXIST.getCode());
        }
        if (!encoder.matchesOpen(password, user.getPassword())) {
            throw new BaseException(OpenApiRespCode.USER_PASS_ERROR.getMsg(), OpenApiRespCode.USER_PASS_ERROR.getCode());
        }
        return user;
    }

    /**
     * 获取所有的权限列表
     *
     * @return
     */
    public List<PermissionInfo> getAllPermission() {
        String key = RedisKeyConstant.REDIS_KEY_ALL_PERMISSION;
        String cachedPermissions = stringRedisTemplate.opsForValue().get(key);
        // 从Redis中获取菜单资源，如果不存在则去数据库查询。缓存12小时
        if (cachedPermissions == null || org.apache.commons.lang.StringUtils.isBlank(cachedPermissions)) {
            List<Menu> menus = menuBiz.selectListAll();
            List<PermissionInfo> result = new ArrayList<>();
            menu2permission(menus, result);
            List<Element> elements = elementBiz.getAllElementPermissions();
            element2permission(result, elements);
            cachedPermissions = JSON.toJSONString(result);
            stringRedisTemplate.opsForValue().set(key, cachedPermissions, 12, TimeUnit.HOURS);
        }
        return JSON.parseArray(cachedPermissions, PermissionInfo.class);
    }

    /**
     * 将访问的菜单资源列表转换为权限列表
     *
     * @param menus  菜单资源列表
     * @param result 权限列表
     */
    private void menu2permission(List<Menu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for (Menu menu : menus) {
            if (StringUtils.isBlank(menu.getHref())) {
                menu.setHref("/" + menu.getCode());
            }
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
            result.add(info);
            info.setMenu(menu.getTitle());
        }
    }

    /**
     * 通过用户名获取其权限列表
     * TODO 更改完用户权限后，需要等待12小时才会生效。角色更改完成对应的资源后，需要清理该角色对应用户列表，清理其缓存的权限资源。
     *
     * @param username 用户名
     * @return
     */
    public List<PermissionInfo> getPermissionByUsername(String username) {
        String key = String.format(RedisKeyConstant.REDIS_KEY_USER_PERMISSION, username);
        String cachePermissionsByUsername = stringRedisTemplate.opsForValue().get(key);
        if (cachePermissionsByUsername == null || org.apache.commons.lang.StringUtils.isBlank(cachePermissionsByUsername)) {
            User user = userBiz.getUserByUsername(username);
            List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId(), user.getGroupId());
            List<PermissionInfo> result = new ArrayList<PermissionInfo>();
            menu2permission(menus, result);
            List<Element> elements = elementBiz.getAuthorityElementByUserIdAndGroupId(user.getId() + "", user.getGroupId());
            element2permission(result, elements);
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(result), 12, TimeUnit.HOURS);
            return result;
        }
        return JSON.parseArray(cachePermissionsByUsername, PermissionInfo.class);
    }

    /**
     * 将资源列表转换为权限列表
     *
     * @param permissionInfoList 资源列表
     * @param elements           权限列表
     */
    private void element2permission(List<PermissionInfo> permissionInfoList, List<Element> elements) {
        PermissionInfo info;
        for (Element element : elements) {
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getUri());
            info.setMethod(element.getMethod());
            info.setName(element.getName());
            info.setMenu(element.getMenuId());
            permissionInfoList.add(info);
        }
    }


    private List<MenuTree> getMenuTree(List<Menu> menus) {
        List<MenuTree> trees = new ArrayList<>();
        MenuTree node;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.build(trees, AdminCommonConstant.ROOT);
    }

    public FrontUserV2 getUserInfoV2() {
        String username = BaseContextHandler.getUsername();
        if (username == null) {
            return null;
        }
        User user = userBiz.getUserByUsername(username);
        FrontUserV2 frontUser = new FrontUserV2();
        List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId(), user.getGroupId());
        List<AccessMenuTree> menuTrees = new ArrayList<>();
        List<AccessRouteTree> routeTrees = new ArrayList<>();
        List<Integer> menuIds = new ArrayList<>();
        List<AccessMenuTree> header = new ArrayList<>();
        // 设置菜单树
        for (Menu menu : menus) {
            buildAccessMenuTreeNode(menuTrees, menu);
            // 设置系统头部菜单
            if (menu.getParentId().equals(AdminCommonConstant.ROOT)) {
                menuIds.add(menu.getId());
                buildAccessMenuTreeNode(header, menu);
                continue;
            }
            buildAccessRouteTree(routeTrees, menu);
        }
        // 配置页面资源权限
        List<Element> elements = elementBiz.getAuthorityElementByUserIdAndGroupId(user.getId() + "", user.getGroupId());
        List<String> permissions = new ArrayList<>();
        List<AccessInterface> interfaces = new ArrayList<>();
        for (Element element : elements) {
            buildAccessInterface(permissions, interfaces, element);
        }
        // 配置路由权限
        List<AccessRouteTree> accessRoutes = new ArrayList<>();
        for (Integer menuId : menuIds) {
            accessRoutes.addAll(TreeUtil.build(routeTrees, menuId));
        }
        frontUser.setAccessMenus(TreeUtil.build(menuTrees, AdminCommonConstant.ROOT));
        frontUser.setAccessHeader(header);
        frontUser.setAccessRoutes(accessRoutes);
        frontUser.setUserPermissions(permissions);
        frontUser.setUserName(user.getName());
        frontUser.setAccessInterfaces(interfaces);
        //TODO 待接入头像附件上传服务
        frontUser.setAvatarUrl("https://api.adorable.io/avatars/85/abott@adorable.png");
        return frontUser;
    }

    /**
     * 构建页面资源权限
     *
     * @param permissions 权限
     * @param interfaces  接口
     * @param element     资源
     */
    private void buildAccessInterface(List<String> permissions, List<AccessInterface> interfaces, Element element) {
        AccessInterface accessInterface;
        accessInterface = new AccessInterface();
        permissions.add(element.getCode());
        accessInterface.setMethod(element.getMethod());
        accessInterface.setPath(element.getUri());
        interfaces.add(accessInterface);
    }

    /**
     * 构建可访问路由树
     *
     * @param routeTrees
     * @param menu
     */
    private void buildAccessRouteTree(List<AccessRouteTree> routeTrees, Menu menu) {
        AccessRouteTree routeNode;
        routeNode = new AccessRouteTree();
        routeNode.setIcon(menu.getIcon());
        routeNode.setId(menu.getId());
        routeNode.setPath(menu.getHref());
        routeNode.setParentId(menu.getParentId());
        routeNode.setName(menu.getCode());
        routeTrees.add(routeNode);
        routeNode.setComponent(menu.getComponent());
        routeNode.setComponentPath(menu.getComponentPath());
        JSONObject jsonObject = JSONObject.parseObject(menu.getMeta());
        jsonObject.put("title", menu.getTitle());
        routeNode.setMeta(jsonObject);
    }

    /**
     * 构建可访问菜单树节点
     *
     * @param menuTrees
     * @param menu
     */
    private void buildAccessMenuTreeNode(List<AccessMenuTree> menuTrees, Menu menu) {
        AccessMenuTree node;
        node = new AccessMenuTree();
        node.setIcon(menu.getIcon());
        node.setPath(menu.getHref());
        node.setTitle(menu.getTitle());
        node.setId(menu.getId());
        node.setParentId(menu.getParentId());
        menuTrees.add(node);
    }


    public List<MenuTree> getMenusByUsername(String token) throws Exception {
        String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        User user = userBiz.getUserByUsername(username);
        List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId(), user.getGroupId());
        return getMenuTree(menus);
    }

    /**
     * 用户鉴权
     *
     * @param username      用户名
     * @param requestUri    请求Uri
     * @param requestMethod 请求方法
     * @return
     */
    public Mono<CheckPermissionInfo> checkUserPermission(String username, String requestUri, String requestMethod) {
        CheckPermissionInfo checkPermissionInfo = new CheckPermissionInfo();
        List<PermissionInfo> allPermission = this.getAllPermission();
        // 判断当前访问资源是否有权限控制
        List<PermissionInfo> matchPermission = allPermission.parallelStream().filter(permissionInfo -> {
            String uri = permissionInfo.getUri();
            if (uri.indexOf("{") > 0) {
                uri = uri.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
            }
            // 使用正则表达式通配 请求Uri与权限资源中的Uri一致
            String regEx = "^" + uri + "$";
            return (Pattern.compile(regEx).matcher(requestUri).find())
                    && requestMethod.equals(permissionInfo.getMethod());
        }).collect(Collectors.toList());
        // 说明当前访问资源不做权限控制
        if (matchPermission.size() == 0) {
            checkPermissionInfo.setIsAuth(true);
            return Mono.just(checkPermissionInfo);
        }
        // 判断当前用户是否拥有该访问资源的权限
        List<PermissionInfo> permissions = this.getPermissionByUsername(username);
        PermissionInfo current = null;
        for (PermissionInfo info : permissions) {
            // 判断用户具有的code与数据库的code是否一致
            boolean anyMatch = matchPermission.parallelStream().anyMatch(permissionInfo -> permissionInfo.getCode().equals(info.getCode()));
            if (anyMatch) {
                current = info;
                break;
            }
        }
        if (current == null) {
            // 当前用户不拥有该权限
            checkPermissionInfo.setIsAuth(false);
        } else {
            // 当前用户拥有该资源的访问权限
            checkPermissionInfo.setIsAuth(true);
            checkPermissionInfo.setPermissionInfo(current);
        }
        return Mono.just(checkPermissionInfo);
    }

    /**
     * 小程序获取用户信息
     *
     * @return
     */
    public MiniUserInfo getMiniUserInfo() {
        String username = BaseContextHandler.getUsername();
        if (username == null) {
            return null;
        }
        User user = userBiz.getUserByUsername(username);
        MiniUserInfo miniUser = new MiniUserInfo();
        // 配置页面资源权限
        String currentGroupId = user.getGroupId();
        List<Element> elements = elementBiz.getAuthorityElementByUserIdAndGroupId(user.getId() + "", currentGroupId);
        List<String> permissions = new ArrayList<>();
        for (Element element : elements) {
            permissions.add(element.getCode());
        }
        List<Role> roles = new ArrayList<>();
        Group currentGroup;
        if (StringUtils.isBlank(currentGroupId)) {
            // 获取用户角色
            Role role = new Role();
            role.setCode(AdminCommonConstant.USER_ROLE);
            Role one = roleBiz.selectOne(role);
            roles.add(one);
            // 获取当前操作的组织信息
            currentGroup = null;
        } else {
            // 获取用户角色
            roles = roleBiz.getRolesByUserIdAndGroupId(user.getId(), currentGroupId);
            // 获取当前操作的组织信息
            currentGroup = groupBiz.selectById(Integer.parseInt(currentGroupId));
        }
        miniUser.setUserPermissions(permissions);
        miniUser.setUserName(user.getName());
        miniUser.setUserRoles(roles);
        // 判断roles中的编码含不含ADMIN_ROLE
        boolean isAdmin = roles.parallelStream().anyMatch(role -> role.getCode().equals(AdminCommonConstant.ADMIN_ROLE));
        miniUser.setAdmin(isAdmin);
        miniUser.setCurrentGroup(currentGroup);
        miniUser.setUserName(user.getUsername());
//        miniUser.setAvatarUrl("https://api.adorable.io/avatars/85/abott@adorable.png");
        return miniUser;
    }
}
