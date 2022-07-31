package com.liteiot.common.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基类: 响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    /**
     * 响应码
     */
    private int statusCode = 200;

    /**
     * 信息
     */
    private String message;
}
