package com.camera.gbcamera_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // 개발 중엔 패턴으로 넓게 허용 (Electron file:// 대응)
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // 커스텀 헤더와 Content-Type 허용
                        .allowedHeaders("x-index", "Content-Type", "Authorization", "Accept", "Origin")
                        // 필요 시 노출할 헤더
                        .exposedHeaders("Location")
                        // 쿠키를 안 쓰면 false로 두는 게 단순합니다.
                        .allowCredentials(false)
                        // 프리플라이트 캐시 시간
                        .maxAge(3600);
            }
        };
    }
}
