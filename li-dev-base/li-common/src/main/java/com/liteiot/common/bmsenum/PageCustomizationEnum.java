package com.liteiot.common.bmsenum;

import com.liteiot.common.exception.BaseException;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * 页面定制化
 */
@Getter
public enum PageCustomizationEnum {

//    WEB_PATH(1, "web_path","客户定制化路径"),

    LOGIN_TOP_LOGO(1, "login-top.png","登录页图标"),

    TOP_NAVIGATION_DEFAULT_ICON(2, "all.png","顶部导航栏默认图标"),

    TOP_NAVIGATION_PUT_AWAY_ICON(3, "icon-only.png","顶部导航栏收起图标"),

    BROWSER_TAB_ICON(4, "icon.ico","浏览器标签页图标"),

    BROWSER_TAB_NAME(50, "appTitle","浏览器标签页名称");

    private final int type;

    private final String name;

    private final String description;

    PageCustomizationEnum(int type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public static PageCustomizationEnum getByType(int type) {
        for (PageCustomizationEnum typeEnum : PageCustomizationEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum;
            }
        }
        throw new BaseException("该类型非法");
    }

    public static Set<PageCustomizationEnum> getGreaterOrEqualByType(int type) {
        Set<PageCustomizationEnum> result = new HashSet<>();
        for (PageCustomizationEnum typeEnum : PageCustomizationEnum.values()) {
            if (typeEnum.getType() >= type) {
                result.add(typeEnum);
            }
        }
        return result;
    }
}
