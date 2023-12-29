package com.example.web.async;

import java.util.List;

public interface EventHandler {
    //事件处理
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}
