package com.liteiot.common.handler;

import com.liteiot.common.bmsenum.StatusCode;
import com.liteiot.common.constant.CommonConstants;
import com.liteiot.common.exception.BaseException;
import com.liteiot.common.exception.auth.ClientTokenException;
import com.liteiot.common.exception.auth.UserInvalidException;
import com.liteiot.common.exception.auth.UserTokenException;
import com.liteiot.common.msg.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 */
@ControllerAdvice("com.liteiot")
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ClientTokenException.class)
    public BaseResponse clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(UserTokenException.class)
    public BaseResponse userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(401);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(UserInvalidException.class)
    public BaseResponse userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(StatusCode.SUCCESS);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(StatusCode.SUCCESS);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(StatusCode.SUCCESS);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(CommonConstants.EX_OTHER_CODE, ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResponse bodyValidExceptionHandler(HttpServletResponse response, MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()).toString();
        response.setStatus(StatusCode.SUCCESS);
        logger.error(message);
        return new BaseResponse(CommonConstants.EX_OTHER_CODE, message);
    }

    @ExceptionHandler({BindException.class})
    public BaseResponse validExceptionHandler(HttpServletResponse response, BindException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()).toString();
        response.setStatus(StatusCode.SUCCESS);
        logger.error(message);
        return new BaseResponse(CommonConstants.EX_OTHER_CODE, message);
    }

}
