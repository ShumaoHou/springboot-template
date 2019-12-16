package com.st.exception;

import com.st.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Shumao
 * @description
 * @date 2019/12/6 15:35
 */
@ControllerAdvice
@Slf4j
public class ShiroExceptionHandler {
    /**
     * 授权失败，就是说没有该权限
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public Result handleShiroException(Exception ex) {
        Result result = new Result();
        result.fail(ex.getMessage());
        return result;
    }

    @ExceptionHandler
    @ResponseBody
    public Result AuthorizationExceptionHandler(AuthorizationException e) {
        Result result = new Result();
        result.fail(e.getMessage());
        return result;
    }
}
