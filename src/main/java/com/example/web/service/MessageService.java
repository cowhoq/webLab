package com.example.web.service;

import java.util.List;
import com.example.web.dao.MessageDao;
import com.example.web.model.Message;
public class MessageService {
    private MessageDao messageDao;

    public List<Message> getReadMessageById(Long id){
        List<Message> messages=messageDao.getMessageByToId(id);
        return messages;
    }

    public List<Message> getUnreadMessageById(Long id){
        List<Message> unReadMessages=messageDao.getUnReadMessageByToId(id);
        return unReadMessages;
    }
}
