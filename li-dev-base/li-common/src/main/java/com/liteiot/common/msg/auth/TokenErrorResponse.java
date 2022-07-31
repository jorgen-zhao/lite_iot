package com.liteiot.common.msg.auth;

import com.liteiot.common.constant.RestCodeConstants;
import com.liteiot.common.msg.BaseResponse;

/**
 * Token错误响应
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
