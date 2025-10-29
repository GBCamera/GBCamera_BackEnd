package com.camera.gbcamera_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.camera.gbcamera_backend.mapper")
public class GbCameraBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbCameraBackEndApplication.class, args);
    }

}
