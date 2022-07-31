package com.liteiot.common.msg;

import com.liteiot.common.bmsenum.StatusCode;
import lombok.Getter;

/**
 * REST响应体
 */

public class ObjectRestResponse<T> extends BaseResponse {

    @Getter
    T data;

    @Deprecated
    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }


    @Deprecated
    public ObjectRestResponse setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ObjectRestResponse<T> build(T data,int statusCode,String message) {
        ObjectRestResponse<T> restResponse = new ObjectRestResponse<>();
        restResponse.setData(data);
        restResponse.setStatusCode(statusCode);
        restResponse.setMessage(message);
        return restResponse;
    }

    public static <T> ObjectRestResponse<T> successData(T data) {
        ObjectRestResponse<T> restResponse = build(data, StatusCode.SUCCESS, null);
        return restResponse;
    }

    public static ObjectRestResponse<String> successMessage(String message) {
        ObjectRestResponse<String> restResponse = build(null, StatusCode.SUCCESS, message);
        return restResponse;
    }

    public static <T> ObjectRestResponse<T> errorData(T data) {
        ObjectRestResponse<T> restResponse = build(data, StatusCode.ERROR, null);
        return restResponse;
    }

    public static ObjectRestResponse<String> errorMessage(String message) {
        ObjectRestResponse<String> restResponse = build(null, StatusCode.ERROR, message);
        return restResponse;
    }


}
