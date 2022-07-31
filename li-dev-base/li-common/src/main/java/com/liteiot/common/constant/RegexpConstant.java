package com.liteiot.common.constant;

public interface RegexpConstant {

    /**
     * 必填手机号、
     */
    String PHONE_REQUIRED = "^1[3-9]\\d{9}$";

    /**
     * 非必填手机号
     */
    String PHONE_NOT_REQUIRED = "((^{1})|(^1[3-9]\\d{9}))$";

    /**
     * url
     */
    String URL = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
}
