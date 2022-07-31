package com.liteiot.common.constant;

/**
 * Redis常用key
 */
public class RedisKeyConstant {

    /**
     * 用户权限
     */
    public static final String REDIS_KEY_USER_PERMISSION = "admin:permissions:%s";

    /**
     * 所有权限
     */
    public static final String REDIS_KEY_ALL_PERMISSION = "admin:permissions";

    /**
     * 图形码
     */
    public static final String REDIS_KEY_CAPTCHA = "admin:captcha:%s";

    /**
     * token
     */
    public static final String REDIS_KEY_TOKEN = "admin:token";

    /**
     * 通用配置项
     */
    public static final String REDIS_COMMON_CONFIG = "admin:config";

    /**
     * 设备报警阈值配置项
     */
    public static final String REDIS_ALARM_THRESHOLD = "admin:alarm_threshold:%s";

    /**
     * 设备默认角度缓存项
     */
    public static final String REDIS_DEVICE_DEFAULT_ANGLE = "admin:angle:%s";

    /**
     * 用户 - groupIds 获取用户所在组织与其下子级组织
     */
    public static final String REDIS_USER_GROUP_IDS = "admin:user_groupIds";

    /**
     * 设备指令
     */
    public static final String REDIS_DEVICE_COMMAND = "admin:device_command:%s";

    /**
     * 系统消息总和
     */
    public static final String SYSTEM_RECEIVE_MSG_COUNT = "admin:system_receive_msg_count";
}
