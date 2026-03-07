package org.example.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.BusinessException;
import org.example.utils.Result;
import org.example.utils.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 首先拦截处理自定义业务异常，这种异常是我们预先设定好的
    // 因为发生了异常，只会返回错误信息，不会返回数据，设成Void空对象
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("发生错误：{}", e.getMessage());
        return Result.fail(e);
    }
    // 接下来处理其他错误
    @ExceptionHandler(Exception.class)
    public Result<Void> handleOtherException(Exception e) {
        log.error("系统崩溃：".concat(e.getMessage()), e);
        // 这种后端内部错误，不能暴露给用户，全都按照ERROR处理
        return Result.fail(ResultCode.SERVER_ERROR);
    }
}