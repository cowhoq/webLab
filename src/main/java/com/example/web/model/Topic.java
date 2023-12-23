package com.example.web.model;

import java.util.Date;

public class Topic {
    private Long id; //话题的topicId
    private String category;//话题所属的目录
    private String code;//话题附加的代码
    private String content;//话题的内容
    private Date createdDate; //话题的创建时间
    private String title;//话题的标题
    private Integer idUser;//创建话题的用户的userId

    private User user;
}
