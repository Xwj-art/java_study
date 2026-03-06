package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    SERVER_ERROR(500, "系统繁忙"),
    SUCCESS(200, "操作成功");

    private final Integer code;
    private final String message;

    public static ResultCode fromCode(Integer code) {
        for (ResultCode resultCode :ResultCode.values()) {
            if (resultCode.code.equals(code)) {
                return resultCode;
            }
        }
        return null;
    }
}
