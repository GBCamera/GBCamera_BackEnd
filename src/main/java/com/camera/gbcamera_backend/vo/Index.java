package com.camera.gbcamera_backend.vo;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Index {
    // 예약어이므로 필드명은 자유, 매핑은 XML에서 처리
    private String index;   // picture.`index`
    private byte[] result;  // picture.`result`
}
