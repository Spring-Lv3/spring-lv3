package com.sparta.adminserver.config.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ApiResponses(value = {

        @ApiResponse(responseCode = "200", description = "API 호출 성공"),
//
//        @ApiResponse(
//                responseCode = NotFound.CODE,
//                description = "존재하지 않는 API",
//                content = @Content(schema = @Schema(implementation = NotFoundResponse.class))),
//
//        @ApiResponse(
//                responseCode = RequestNotValid.CODE,
//                description = "유효성 검증 실패",
//                content = @Content(schema = @Schema(implementation = RequestNotValidResponse.class))),
//
//        @ApiResponse(
//                responseCode = RequestMethodNotSupport.CODE,
//                description = "잘못된 Method 요청",
//                content = @Content(schema = @Schema(implementation = RequestMethodNotSupportResponse.class))),
//
//        @ApiResponse(
//                responseCode = Unauthorized.CODE,
//                description = "인증 실패",
//                content = @Content(schema = @Schema(implementation = UnauthorizedResponse.class))),
//
//        @ApiResponse(
//                responseCode = ExpiredToken.CODE,
//                description = "토큰 유효기간 만료",
//                content = @Content(schema = @Schema(implementation = ExpiredTokenResponse.class))),
//
//        @ApiResponse(
//                responseCode = Forbidden.CODE,
//                description = "인가 실패(권한 없음)",
//                content = @Content(schema = @Schema(implementation = ForbiddenResponse.class))),
//
//        @ApiResponse(
//                responseCode = RegisterFailed.CODE,
//                description = "데이터 등록 실패",
//                content = @Content(schema = @Schema(implementation = RegisterFailedResponse.class))),

})

public @interface ApiDocument {
}
