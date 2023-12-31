package com.example.web.controller;

import com.example.web.model.Answer;
import com.example.web.model.Message;
import com.example.web.model.Topic;
import com.example.web.model.User;
import com.example.web.service.MessageService;
import com.example.web.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import com.example.web.dao.AnswerDao;
import com.example.web.dao.MessageDao;
import com.example.web.dao.UserDao;
import com.example.web.dao.TopicDao;


// 站内信的实现：通过异步队列来实现，当发起话题的发起者认为你的评论有用时、和评论你的帖子时会触发异步队列
// *站内信的内容主要为：XXX评论了你的话题、XXX认为你的评论很有用！...
@Controller
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    MessageService messageService;

    @RequestMapping(path="/message",method= RequestMethod.GET)
    public String message(Model model) {
        User user=hostHolder.getUser();
        Long toId=user.getId();

        List<Message> messages=messageService.getReadMessageById(toId);
        List<Message> unReadMessages=messageService.getUnreadMessageById(toId);

        model.addAttribute("user",user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("messages", messages);
        model.addAttribute("unReadMessages", unReadMessages);
        model.addAttribute("userDao", userDao);
        model.addAttribute("messageDao", messageDao);
        return "message";
    }

    @RequestMapping(path = "/topicM/{id}_{messageId}", method = RequestMethod.GET)
    public String displayTopic(@PathVariable String id, @PathVariable String messageId, Model model) {

        User user = hostHolder.getUser();
        Long idUser = user.getId();

        Topic topic = topicDao.findTopicById(Long.valueOf(id));
        List<Answer> answers = answerDao.findAnswerByTopic_Id(Long.valueOf(id));
        messageDao.changeReadStatement(Long.valueOf(messageId));

        model.addAttribute("user", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("topic", topic);
        model.addAttribute("answers", answers);
        model.addAttribute("idUser", idUser);
        model.addAttribute("userDao", userDao);
        return "topic";
    }

    @RequestMapping(path = "/topicM/message", method = RequestMethod.GET)
    public View topicsTransform(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/message");
    }

}
