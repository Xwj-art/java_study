package org.example.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Around("execution(* org.example.controller..*.*(..)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        // 请求路径和IP
        // 首先拿到attributes，再拿到里面的request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("【请求开始】URL: {}, IP: {}, 方法: {}, 参数: {}", url, ip, methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("【请求结束】耗时: {}ms, 返回结果: {}", (endTime - startTime), result);

        return result;
    }
}
