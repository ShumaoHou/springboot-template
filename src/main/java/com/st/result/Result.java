package com.st.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Shumao
 * @description 结果类
 * @date 2019/8/21 10:44
 */
@Data
public class Result implements Serializable {

    public static final Integer SUCCESS = 20000;

    public static final String SUCCESS_MSG = "SUCCESS";

    public static final Integer FAIL = 50000;

    public static final String FAIL_MSG = "FAIL";

    public static final Integer PASSWORD_FAIL = 50002;

    public static final Integer LOGOUT_FAIL = 50004;

    public static final Integer PERMISSION_FAIL = 50006;

    public static final Integer TOKEN_ILLEGAL = 50008;

    public static final Integer OTHER_CLIENTS = 50012;

    public static final Integer TOKEN_EXPIRED = 50014;

    protected Integer code = SUCCESS;
    protected String message = SUCCESS_MSG;
    protected String token = null;

    public void fail() {
        setCode(FAIL);
        setMessage(FAIL_MSG);
    }

    public void fail(String failMsg) {
        setCode(FAIL);
        setMessage(failMsg);
    }
}
