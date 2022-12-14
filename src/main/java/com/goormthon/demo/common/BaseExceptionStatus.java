package com.goormthon.demo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseExceptionStatus {
    // 성공
    SUCCESS(HttpStatus.OK, 200, "요청 성공"),

    // 실패
    NOT_MATCH_LENGTH(HttpStatus.BAD_REQUEST, 4001, "답변 문항 개수가 올바르지 않습니다.");

    private HttpStatus httpStatus;
    private final int code;
    private final String message;

    private BaseExceptionStatus(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
