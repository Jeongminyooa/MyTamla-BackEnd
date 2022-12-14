package com.goormthon.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import static com.goormthon.demo.common.BaseExceptionStatus.SUCCESS;

@Data
@JsonPropertyOrder({"code", "message", "data"})
public class BaseResponse<T> {
    private int code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // data가 있는 경우
    public BaseResponse(T data) {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.data = data;
    }

    // data가 없는 경우
    public BaseResponse() {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
    }
}
