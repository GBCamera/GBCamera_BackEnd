package com.camera.gbcamera_backend.mapper;

import com.camera.gbcamera_backend.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByIdAndPassword(@Param("id") String id, @Param("password") String password);
}
