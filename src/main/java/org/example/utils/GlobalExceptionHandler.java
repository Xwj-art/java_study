package org.example.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j // 类中自动生成log对象
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 报错时通常没有data返回，所以用<Void>，Void表示空对象
    @ExceptionHandler(value = Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("controller发生错误: ", e);
        return Result.fail(ResultCode.SERVER_ERROR.getCode());
    }
}
