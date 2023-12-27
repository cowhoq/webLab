package com.example.web.controller;

import com.example.web.model.Answer;
import com.example.web.model.Topic;
import com.example.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.web.dao.UserDao;
import com.example.web.dao.TopicDao;
import com.example.web.dao.AnswerDao;
import com.example.web.dao.MessageDao;
import com.example.web.util.HostHolder;
import com.example.web.service.TopicsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
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

    @RequestMapping(path = "/topic/{id}", method = RequestMethod.GET)
    public String displayTopic(@PathVariable String id, Model model) {

        User user = hostHolder.getUser();
        Long idUser = user.getId();

        Topic topic = topicDao.findTopicById(Long.valueOf(id));
        List<Answer> answers = answerDao.findAnswerByTopic_Id(Long.valueOf(id));

        model.addAttribute("user", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("topic", topic);
        model.addAttribute("answers", answers);
        model.addAttribute("idUser", idUser);
        model.addAttribute("userDao", userDao);
        return "topic";
    }

    //删除评论或置为有用/没用的评论
    @RequestMapping(path = "/topic/{id}", method = RequestMethod.POST)
    public View updateAnswer(@RequestParam String id_topic, @RequestParam String action, @RequestParam String id_answer,
                             @RequestParam(required = false) String state, HttpServletRequest request) {
        switch (action) {
            case "useful":
                answerDao.setUsefulForAnswer(!Boolean.valueOf(state), Long.valueOf(id_answer));
                break;
            case "delete":
                answerDao.deleteAnswerById(Long.valueOf(id_answer));
                break;
        }
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/topic/" + id_topic);
    }

    //话题评论接口
    @RequestMapping(path = "/topic", method = RequestMethod.POST)
    public View addAnswer(@RequestParam("content") String content, @RequestParam("code") String code,
                          @RequestParam("id_topic") String id_topic, @RequestParam("id_user") String id_user,
                          HttpServletRequest request) {

        topicsService.addAnswer(content, code, id_topic, id_user);

        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/topic/" + id_topic);
    }
}
