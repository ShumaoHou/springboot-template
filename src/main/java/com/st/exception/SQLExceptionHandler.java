package com.st.exception;

import com.st.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author Shumao
 * @description
 * @date 2019/12/9 17:16
 */
@ControllerAdvice
@Slf4j
public class SQLExceptionHandler {

    /**
     * 唯一约束错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Result SQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e) {
        Result result = new Result();
        result.fail(e.getMessage());
        return result;
    }

}
