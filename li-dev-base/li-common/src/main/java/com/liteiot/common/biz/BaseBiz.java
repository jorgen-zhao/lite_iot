package com.liteiot.common.biz;

import com.liteiot.common.constant.RedisKeyConstant;
import com.liteiot.common.context.BaseContextHandler;
import com.liteiot.common.exception.BaseException;
import com.liteiot.common.msg.TableResultResponse;
import com.liteiot.common.util.EntityUtils;
import com.liteiot.common.util.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 基类: CRUD封装类
 */

public abstract class BaseBiz<M extends Mapper<T>, T> {

    @Autowired
    protected M mapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }


    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }


    public List<T> selectListAll() {
        return mapper.selectAll();
    }


    public Long selectCount(T entity) {
        return new Long(mapper.selectCount(entity));
    }

    /**
     * 通用查询(精准匹配)
     * @param queryDto 查询参数实体
     * @return 集合
     */
    public List<T> queryWithCommonByCondition(T queryDto) {
        return mapper.select(queryDto);
    }


    public void insert(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insert(entity);
    }

    public void insertPlain(T entity) {
        mapper.insert(entity);
    }


    public void insertSelective(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insertSelective(entity);
    }


    public void delete(T entity) {
        mapper.delete(entity);
    }


    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(Object example) {
        mapper.deleteByExample(example);
    }


    public void updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKey(entity);
    }


    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKeySelective(entity);

    }

    public void updateSelectiveWithPlain(T entity) {
        mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    @SuppressWarnings("all")
    public TableResultResponse<T> selectByQuery(Query query) {
        Example example = buildQueryExample(query);
        // 注意：排序使用的是表中的列名，不是对象属性名。 example.setOrderByClause("crt_time desc");
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = mapper.selectByExample(example);
        return TableResultResponse.build(result.getTotal(), list);
    }

    /**
     * 构建查询条件
     *
     * @param query
     * @return
     */
    public Example buildQueryExample(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if (query.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : query.entrySet()) {

                String key = entry.getKey();
                final String startTime = "start";
                final String endTime = "end";
                // 添加时间查询支持
                if (key.contains(startTime)) {
                    criteria.andGreaterThanOrEqualTo(key, entry.getValue());
                    continue;
                }
                if (key.contains(endTime)) {
                    criteria.andLessThanOrEqualTo(key, entry.getValue());
                    continue;
                }
                // PS: 使用该接口记录 user/packetInfo/deviceInfo
                if (key.contains("groupId")) {
                    // 如果存在查询操作
                    List<String> groupIds = (List<String>) entry.getValue();
                    //  判空处理
                    if (groupIds.size() > 0) {
                        criteria.andIn("groupId", groupIds);
                    } else {
                        criteria.andEqualTo("groupId", "");
                    }
                    continue;
                }
                if (key.contains("id")) {
                    List<String> userIds = (List<String>) entry.getValue();
                    if (userIds.size() > 0) {
                        criteria.andIn("id", userIds);
                    }
                    continue;
                }

                // 非时间都可以查询
                criteria.andLike(key, "%" + entry.getValue().toString() + "%");
            }
        }

        // 时间倒序 注意：排序使用的是对象属性。
        example.orderBy("crtTime").desc();
        return example;
    }

    /**
     * 构造组织树
     * 前提：该用户登录，肯定存在组织结构，即使只存在一级也是存在。
     *
     * @return
     */
    public List<String> buildGroupIdsWithRedis() {
        String username = BaseContextHandler.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new BaseException("当前用户不存在", 500);
        }
        String groupIds = (String) stringRedisTemplate.opsForHash().get(RedisKeyConstant.REDIS_USER_GROUP_IDS, username);
        if (StringUtils.isBlank(groupIds)) {
            return Collections.emptyList();
        }
        String[] groupIdArray = groupIds.split(",");
        if (groupIdArray.length == 0) {
            return Collections.emptyList();
        }
        return Stream.of(groupIdArray).filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }

    /**
     * 获取当前用户名称
     *
     * @return
     */
    protected String getCurrentUserName() {
        return BaseContextHandler.getUsername();
    }

    /**
     * 获取当前用户Id
     *
     * @return
     */
    protected String getCurrentUserId() {
        return BaseContextHandler.getUserID();
    }
}
