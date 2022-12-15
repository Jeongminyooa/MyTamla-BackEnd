package com.goormthon.demo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseExceptionStatus {
    // 성공
    SUCCESS(HttpStatus.OK, 200, "요청 성공"),

    // 실패
    NOT_MATCH_LENGTH(HttpStatus.BAD_REQUEST, 4001, "답변 문항 개수가 올바르지 않습니다."),
    NOT_SUPPORT_FILE(HttpStatus.BAD_REQUEST, 4001, "지원하지 않는 파일 형식입니다"),
    FILE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, 4002, "파일 업로드 실패"),
    FILE_DOWNLOAD_FAIL(HttpStatus.BAD_REQUEST, 4003, "파일 다운로드 실패");

    private HttpStatus httpStatus;
    private final int code;
    private final String message;

    private BaseExceptionStatus(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
