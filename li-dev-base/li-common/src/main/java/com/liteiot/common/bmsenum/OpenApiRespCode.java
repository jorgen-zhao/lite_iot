package com.liteiot.common.bmsenum;

import com.liteiot.common.exception.BaseException;
import lombok.Getter;

/**
 * Class:  OpenApiRespCode
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/27 11:30
 * Desc:   OpenApiRespCode
 */
@Getter
public enum OpenApiRespCode {

    PARAM_ERROR(5001, "参数有误"),

    USER_NOT_EXIST(5002, "用户不存在"),

    USER_PASS_ERROR(5003, "用户名密码不匹配"),

    RECORD_NOT_EXIST(5004, "记录不存在"),

    MAX_LIMIT_ERROR(5005, "单页不能超过1000条"),

    START_END_TIME_ERROR(5006, "起止时间不能超过一年");

    private final int code;

    private final String msg;


    OpenApiRespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OpenApiRespCode getByCode(int code) {
        for (OpenApiRespCode typeEnum : OpenApiRespCode.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
            }
        }
        throw new BaseException("该类型非法");
    }
}
