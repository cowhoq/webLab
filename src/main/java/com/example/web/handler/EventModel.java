package com.example.web.handler;

import java.util.Date;

public class EventModel {
    private EventType type; //事件的类型
    private int actorId; //事件的触发者
    private int entityType; //触发的对象
    private int entityId;
    private int entityOwnerId; //触发的对象的拥有者
    private Date createdDate;


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public EventModel(EventType type) {
        this.type = type;
    }


    public int getActorId() {
        return actorId;
    }


    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }
    public int getEntityId() {
        return entityId;
    }


    public int getEntityOwnerId() {
        return entityOwnerId;
    }


    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public EventType getType() {
        return type;
    }

}
