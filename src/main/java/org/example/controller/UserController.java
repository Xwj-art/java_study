package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.dto.LoginDTO;
import org.example.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public Result<Void> login(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {
        Long userId = (Long) request.getAttribute("userId");
        String role = (String) request.getAttribute("role");
        // TODO: 登陆判断是否有这个账号
        return Result.success();
    }
}
