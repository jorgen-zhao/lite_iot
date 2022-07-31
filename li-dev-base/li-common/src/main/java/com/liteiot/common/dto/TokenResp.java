package com.liteiot.common.dto;

import lombok.Data;

/**
 * Class:  TokenResp
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/21 14:16
 * Desc:   TokenResp
 */
@Data
public class TokenResp {

    private String msg;

    private int code;

    private Data data;

    // 内部类
    public static class Data {

        private String accessToken;
        private long expireTime;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
