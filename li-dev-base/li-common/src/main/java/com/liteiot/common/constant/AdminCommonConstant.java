package com.liteiot.common.constant;

/**
 * Admin 实体相关
 */
public class AdminCommonConstant {
    public final static int ROOT = -1;
    public final static int FIRST_CLASS = 1;
    public final static int DEFAULT_GROUP_TYPE = 0;
    /**
     * 权限关联类型
     */
    public final static String AUTHORITY_TYPE_GROUP = "group";
    /**
     * 权限资源类型
     */
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";

    public final static String RESOURCE_REQUEST_METHOD_GET = "GET";
    public final static String RESOURCE_REQUEST_METHOD_PUT = "PUT";
    public final static String RESOURCE_REQUEST_METHOD_DELETE = "DELETE";
    public final static String RESOURCE_REQUEST_METHOD_POST = "POST";

    public final static String RESOURCE_ACTION_VISIT = "访问";

    public final static String BOOLEAN_NUMBER_FALSE = "0";

    public final static String BOOLEAN_NUMBER_TRUE = "1";

    /**
     * 验证码有效期：2分钟
     */
    public final static Integer LOGIN_CAPTCHA_EXPIRATION = 2;

    /**
     * 前端参数传递标识
     */
    public final static String GROUP_ID = "groupId";

    /**
     * 普通用户角色
     */
    public final static String USER_ROLE = "userRole";

    /**
     * 管理员角色
     */
    public final static String ADMIN_ROLE = "adminRole";

    /**
     * 运维角色
     */
    public final static String OPS_ROLE = "opsRole";

    /**
     * 专家角色
     */
    public final static String EXP_ROLE = "expRole";


    /**
     * 默认球心坐标
     */
    public final static double SPHERE_CENTER = 0;

}
