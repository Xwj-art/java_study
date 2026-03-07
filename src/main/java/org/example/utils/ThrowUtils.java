package org.example.utils;

import org.example.exception.BusinessException;

public class ThrowUtils {
    // 针对于通用的
    public static void throwIf(Boolean condition, RuntimeException e) {
        if (condition) {
            throw e;
        }
    }

    // 针对于业务
    public static void throwIf(Boolean condition, Integer code, String message) {
        throwIf(condition, new BusinessException(message, code));
    }
}