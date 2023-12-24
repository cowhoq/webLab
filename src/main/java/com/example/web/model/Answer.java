package com.example.web.model;

import java.util.Date;

public class Answer {
    private Long id;//评论的answerId
    private String content;//评论的内容
    private boolean useful;//评论是否有用
    private Date createdDate;//评论的创建时间
    private String code;//评论附加的代码
    private Integer idTopic;//评论对应的话题的topicId
    private Integer idUser;//该话题的用户的userId
}
