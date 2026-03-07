package org.example.utils;

import org.example.exception.BusinessException;

public class Result<T> {
    private T data;
    private String message;
    private Integer code;

    // 增删改和查是两种业务
    // 前者只需返回状态码表是否成功
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.message = ResultCode.SUCCESS.getMessage();
        result.code = ResultCode.SUCCESS.getCode();
        return result;
    }

    // 后者需要返回所查到的数据
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.data = data;
        result.message = ResultCode.SUCCESS.getMessage();
        result.code = ResultCode.SUCCESS.getCode();
        return result;
    }
    // 接下来是fail的重载，fail不返回数据，只返回msg、code
    // fail能灵活接受数据
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<T>();
        result.message = message;
        result.code = code;
        return result;
    }
    // fail能够处理灵活的异常
    public static <T> Result<T> fail(BusinessException e) {
        return fail(e.getCode(), e.getMessage());
    }

    // fail能够处理状态码
    public static <T> Result<T> fail(ResultCode resultCode) {
        return  fail(resultCode.getCode(), resultCode.getMessage());
    }
}