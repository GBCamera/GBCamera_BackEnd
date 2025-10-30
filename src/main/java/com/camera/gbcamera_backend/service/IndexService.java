package com.camera.gbcamera_backend.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camera.gbcamera_backend.mapper.IndexMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final IndexMapper indexMapper;
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * POST /index 호출 시:
     * 1) 고유 랜덤 문자열 생성
     * 2) DB에 (index, result=null) insert
     * 3) 생성된 index 반환
     */
    @Transactional
    public String createUniqueIndex() {
        // 유니크 충돌까지 고려한 재시도 루프
        for (int attempt = 0; attempt < 10; attempt++) {
            String candidate = generateRandomId(22); // 22~32 권장
            if (indexMapper.countByIndex(candidate) == 0) {
                try {
                    indexMapper.insert(candidate, null);
                    return candidate;
                } catch (DuplicateKeyException e) {
                    // 동시성으로 인한 레이스(유니크 충돌) 시 재시도
                }
            }
        }
        throw new IllegalStateException("고유 index 생성에 실패했습니다. 잠시 후 다시 시도해주세요.");
    }

    /**
     * 프론트가 나중에 result(BLOB)를 업로드하면 index로 갱신
     */
    @Transactional
    public void updateResult(String index, byte[] blob) {
        int updated = indexMapper.updateResult(index, blob);
        if (updated == 0) {
            throw new IllegalArgumentException("존재하지 않는 index 입니다: " + index);
        }
    }

    // URL-safe Base64 일부 + 랜덤 바이트 → 영숫자 대체
    private String generateRandomId(int length) {
        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        // URL-safe Base64 → 영숫자/언더스코어/하이픈 포함. 길이 보정 위해 자르기.
        String base = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        // 너무 긴 경우 잘라내기
        return base.length() > length ? base.substring(0, length) : base;
    }
}
