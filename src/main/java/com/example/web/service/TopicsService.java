package com.example.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.web.dao.UserDao;
import com.example.web.dao.TopicDao;
import com.example.web.dao.AnswerDao;
import com.example.web.dao.MessageDao;
import com.example.web.util.HostHolder;
import com.example.web.service.TopicsService;
import com.example.web.handler.EventProducer;
public class TopicsService {
    @Autowired
    private TopicDao topicDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    HostHolder hostHolder;
}
