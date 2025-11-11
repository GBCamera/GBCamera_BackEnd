package com.camera.gbcamera_backend.controller;


import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.camera.gbcamera_backend.dto.IndexDto;
import com.camera.gbcamera_backend.service.IndexService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173", "capacitor://localhost", "file://"})

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
            @RequestHeader("x-index") String index, // ✅ Header에서 index 추출
            @Valid @RequestBody IndexDto.UpdateResultRequest request) {
        byte[] blob = Base64.getDecoder().decode(request.getBase64());
        indexService.updateResult(index, blob);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/find")
    public ResponseEntity<IndexDto.FindResponse> findResult(@RequestBody IndexDto.FindRequest request) {
        var index = request.getIndex();
        var found = indexService.findResult(index);

        if (found == null || found.getResult() == null) {
            return ResponseEntity.notFound().build();
        }

        // byte[] → Base64 문자열 변환
        String base64 = Base64.getEncoder().encodeToString(found.getResult());

        return ResponseEntity.ok(new IndexDto.FindResponse(base64));
    }
}
