package com.example.web.model;

import java.util.Date;

public class Message {
    private Long id; //站内信的id
    private Integer fromId;//站内信的发送方
    private Integer toId;//站内容的接收方
    private String content;//站内信的内容
    private Date createdDate;//站内信触发的时间
    private Integer idTopic;//站内信对应的话题
    private int hasRead;//站内信是否已读
}
