package org.example.utils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HttpServletBean;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        ThrowUtils.throwIf(token == null || token.isEmpty(), 400, "token为空");
        try {
            Long userId = jwtUtils.getUserId(token);
            String role = jwtUtils.getUserRole(token);

            request.setAttribute("userId", userId);
            request.setAttribute("role", role);

            return true;
        } catch (Exception e) {
            throw new BusinessException("token无效", 404);
        }
    }
}
