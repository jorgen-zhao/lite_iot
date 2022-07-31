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
public class PicResp {

    private String msg;

    private int code;

    private Data data;

    // 内部类
    public static class Data {

        private String picUrl;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
