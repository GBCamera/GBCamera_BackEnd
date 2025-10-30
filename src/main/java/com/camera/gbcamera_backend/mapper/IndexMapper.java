package com.camera.gbcamera_backend.mapper;

import com.camera.gbcamera_backend.vo.Index;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface IndexMapper {
    // 새 행 삽입 (result는 보통 null)
    int insert(@Param("index") String index, @Param("result") byte[] result);

    // 존재 여부
    int countByIndex(@Param("index") String index);

    // result 갱신
    int updateResult(@Param("index") String index, @Param("result") byte[] result);

    // 조회(필요 시)
    Index selectByIndex(@Param("index") String index);
}
