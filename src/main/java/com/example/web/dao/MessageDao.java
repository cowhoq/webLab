package com.example.web.dao;

import com.example.web.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {
    String TABLE_NAME = "message";
    String INSERT_FIELDS = "from_id,to_id,content,created_date,id_topic,has_read";
    String SELECT_FIELDS = "id,from_id,to_id,content,created_date,id_topic,has_read";

    //增加一条消息
    int addMessage(Message message);
    //根据用户的id[to_id]获得消息列表
    List<Message> getMessageByToId(@Param("toId") Long toId);

    //根据用户的id[to_id]计算消息数量
    int countMessageByToId(@Param("toId") Long toId);

    //改变已读的状态
    void changeReadStatement(@Param("id") Long id);

    List<Message> getUnReadMessageByToId(Long id);
}
