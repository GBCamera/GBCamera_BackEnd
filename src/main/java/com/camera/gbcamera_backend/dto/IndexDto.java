package com.camera.gbcamera_backend.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class IndexDto {

    // index 생성 응답 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String index;
    }

    // result 업데이트 요청 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateResultRequest {
        @NotBlank
        private String base64; // 프론트에서 보낸 Base64 문자열
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindRequest {
        private String index;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindResponse {
        private String base64;
    }
}
