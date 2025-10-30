package com.camera.gbcamera_backend.controller;


import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.camera.gbcamera_backend.dto.IndexDto;
import com.camera.gbcamera_backend.service.IndexService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@Validated
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    /**
     * POST /index
     * - 고유 랜덤 문자열 생성
     * - DB에 (index, result=null) 저장
     * - 생성된 index 반환
     */
    @PostMapping("/index")
    public ResponseEntity<IndexDto.Response> createIndex() {
        String id = indexService.createUniqueIndex();
        return ResponseEntity.ok(new IndexDto.Response(id));
    }

    /**
     * PUT /index/{index}/result
     * - Base64 문자열을 받아 BLOB으로 디코딩 후 저장
     * - 프론트가 blob을 바로 JSON으로 보내기 어려우면 multipart로 전환 가능
     */
    @PutMapping("/index/result")
    public ResponseEntity<Void> updateResult(
            @RequestHeader("X-Index") String index, // ✅ Header에서 index 추출
            @Valid @RequestBody IndexDto.UpdateResultRequest request) {
        byte[] blob = Base64.getDecoder().decode(request.getBase64());
        indexService.updateResult(index, blob);
        return ResponseEntity.noContent().build();
    }
}
