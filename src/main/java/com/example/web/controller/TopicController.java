package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.web.dao.UserDao;
import com.example.web.dao.TopicDao;
import com.example.web.dao.AnswerDao;
import com.example.web.dao.MessageDao;
import com.example.web.util.HostHolder;
import com.example.web.service.TopicsService;
public class TopicController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    TopicsService topicsService;
}
