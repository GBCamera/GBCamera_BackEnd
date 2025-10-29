package com.camera.gbcamera_backend.controller;

import com.camera.gbcamera_backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 예: GET /api/login?id=owner&password=owner
    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String id,
            @RequestParam String password
    ) {
        return ResponseEntity.ok(loginService.login(id, password));
    }
}
