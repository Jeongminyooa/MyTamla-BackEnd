package com.goormthon.demo.controller;


import com.goormthon.demo.common.BaseResponse;
import com.goormthon.demo.dto.SurveyRequest;
import com.goormthon.demo.service.CoastService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.goormthon.demo.common.BaseExceptionStatus.SUCCESS;

@RestController
@RequestMapping("/api/coasts")
@RequiredArgsConstructor
public class CoastController {
    private final CoastService coastService;

    @PostMapping("")
    @ApiOperation(value = "post coast result", notes = "나의 탐라 결과를 저장하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "4001", description = "request parameter 개수가 올바르지 않습니다.")
    })
    public ResponseEntity<BaseResponse> postCoastSurvey(SurveyRequest request) {
        String result = coastService.save(request);
        return ResponseEntity.ok(new BaseResponse(result));
    }
}
