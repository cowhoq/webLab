package com.example.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.example.web.model.Image;
@Mapper
public interface ImageDao {
    String TABLE_NAME = "image";
    String INSERT_FIELDS = "imgUrl,id_user";
    String SELECT_FIELDS = "id,imgUrl,id_user";

    //插入一张照片
    int addImg(Image img);

    //根据userid获得照片列表
    List<String> getImgByUserId(@Param("userId") Long userId);

    //根据userid获得全部照片
    List<String> getAllImgByUserId(@Param("userId") Long userId);
}
