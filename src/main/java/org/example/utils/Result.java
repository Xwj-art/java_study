package org.example.utils;

import lombok.Data;

@Data
public class Result<T> {
    private String message;
    private Integer code;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.data = data;
        result.code = 200;
        result.message = ResultCode.SUCCESS.getMessage();
        return result;
    }

    public static <T> Result<T> fail(Integer code) {
        Result<T> result = new Result<T>();
        result.code = code;
        result.message = ResultCode.fromCode(code).getMessage();
        // TODO: 异常处理并未列举出来的code
        return result;
    }
}
