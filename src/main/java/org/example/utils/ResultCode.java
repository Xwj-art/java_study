package org.example.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    PARAM_ERROR(400, "参数错误"), // 增加这个，用于业务逻辑失败
    SERVER_ERROR(500, "系统繁忙");

    private Integer code;
    private String message;
}
