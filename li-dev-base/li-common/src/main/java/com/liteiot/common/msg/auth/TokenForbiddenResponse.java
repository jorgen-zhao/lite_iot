package com.liteiot.common.msg.auth;

import com.liteiot.common.constant.RestCodeConstants;
import com.liteiot.common.msg.BaseResponse;

/**
 * Token禁止响应
 */
public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, message);
    }
}
