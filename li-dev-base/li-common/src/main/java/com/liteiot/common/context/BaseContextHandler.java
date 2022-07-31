package com.liteiot.common.context;


import com.liteiot.common.constant.CommonConstants;
import com.liteiot.common.util.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局会话上下文
 */
public class BaseContextHandler {

    /**
     * 全局会话控制容器
     * thread -- map: {key: token, value: }
     */
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    private static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    /**
     * 获取当前用户Id
     * @return
     */
    public static String getUserID() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    /**
     * 获取当前accountName
     * @return
     */
    public static String getUsername() {
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }


    /**
     * 获取当前用户名
     * @return
     */
    public static String getName() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_NAME);
        return StringHelper.getObjectValue(value);
    }

    /**
     * 获取当前token
     * @return
     */
    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringHelper.getObjectValue(value);
    }

    /**
     * 存储当前token
     * @param token
     */
    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    /**
     * 设置当前用户名
     * @param name
     */
    public static void setName(String name) {
        set(CommonConstants.CONTEXT_KEY_USER_NAME, name);
    }

    /**
     * 设置用户Id
     * @param userID
     */
    public static void setUserID(String userID) {
        set(CommonConstants.CONTEXT_KEY_USER_ID, userID);
    }

    /**
     * 设置用户accountName
     * @param username
     */
    public static void setUsername(String username) {
        set(CommonConstants.CONTEXT_KEY_USERNAME, username);
    }


    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
