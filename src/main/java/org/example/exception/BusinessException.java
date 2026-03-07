package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private String message;
    private Integer code;
}