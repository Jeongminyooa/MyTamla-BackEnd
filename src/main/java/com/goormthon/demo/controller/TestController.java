package com.goormthon.demo.controller;

import com.goormthon.demo.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.goormthon.demo.common.BaseExceptionStatus.SUCCESS;

@Tag(name = "tests", description = "테스트 API")
@RestController
@RequestMapping("/api/tests")
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
})
public class TestController {

    @Operation(summary = "get hello", description = "api 테스트 가져오기")
    @GetMapping("/hello")
    public ResponseEntity<BaseResponse> greeting() {
        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }
}
