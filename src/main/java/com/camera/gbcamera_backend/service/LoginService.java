package com.camera.gbcamera_backend.service;

import com.camera.gbcamera_backend.vo.User;
import com.camera.gbcamera_backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserMapper userMapper;

    public String login(String id, String password){
        User user = userMapper.findByIdAndPassword(id, password);
        return (user != null) ? "login" : "no";
    }
}
