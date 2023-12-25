package com.example.web.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.example.web.model.Topic;
public interface TopicDao {
    String TABLE_NAME = "topic";
    String INSERT_FIELDS = "title,content,category,created_date,code,id_user";
    String SELECT_FIELDS = "id,title,content,category,created_date,code,id_user";
    //根据id删除一条话题记录
    int addTopic(Topic topic);
    //统计用户发的话题数量
    Long countTopicsByUser_Id(@Param("userId") Long userId);
   //根据id查找话题
    Topic findTopicById(@Param("id") Long id);
    //获取目录category下的所有话题
    List<Topic> findTopicsByCategoryOrderByCreatedDateDesc(@Param("category") String category);

//获取用户发布的所有话题记录
    List<Topic> findTopicsByUser_IdOrderByCreatedDateDesc(@Param("id") Long id);

//获得所有话题
    List<Topic> findAll();
//根据topic的id获得用户id
    int getId_userById(@Param("id") Long id);
}
