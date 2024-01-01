package com.example.web.controller;

import com.alibaba.fastjson.JSON;
import com.example.web.dao.*;
import com.example.web.model.Image;
import com.example.web.model.Topic;
import com.example.web.model.User;
import com.example.web.service.QiniuService;
import com.example.web.util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private QiniuService qiniuService;

    private void populateModelWithUserData(Model model, Long userId, boolean isSelf) {
        User user = userDao.getUserById(userId);
        Long numberOfTopics = topicDao.countTopicsByUser_Id(userId);
        Long numberOfAnswers = answerDao.countAnswersByUser_Id(userId);
        Long numberOfHelped = answerDao.countAnswersByUser_IdAndUseful(userId, true);
        List<String> myImgs = imageDao.getImgByUserId(userId);
        List<String> myAllImgs = imageDao.getAllImgByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(userId));
        model.addAttribute("numberOfTopics", numberOfTopics);
        model.addAttribute("numberOfAnswers", numberOfAnswers);
        model.addAttribute("numberOfHelped", numberOfHelped);
        model.addAttribute("myImgs", myImgs);
        model.addAttribute("isHasMoreImg", myAllImgs.size() > myImgs.size());
        model.addAttribute("switch", isSelf);
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String displayMyProfile(Model model) {
        User user = hostHolder.getUser();
        populateModelWithUserData(model, user.getId(), true);
        return "profile";
    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public String displayProfileById(@PathVariable Long id, Model model) {
        User otherUser = hostHolder.getUser();
        populateModelWithUserData(model, id, id.equals(otherUser.getId()));
        model.addAttribute("otherUser", userDao.getUserById(id));
        return "profile";
    }

    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public View addTask(@RequestParam("category") String category, @RequestParam("title") String title,
                        @RequestParam("content") String content, @RequestParam("code") String code,
                        @RequestParam("id_user") String id_user, HttpServletRequest request) {
        Topic topic = new Topic();
        topic.setCategory(category);
        if (Objects.equals(code, "")) {
            topic.setCode(null);
        } else {
            topic.setCode(code);
        }
        topic.setContent(content);
        topic.setTitle(title);
        topic.setCreatedDate(new Date());
        topic.setIdUser(Integer.parseInt(id_user));
        topic.setUser(userDao.getUserById(Long.parseLong(id_user)));

        topicDao.addTopic(topic);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/profile");
    }

    @RequestMapping(path="/imageWall/{id}",method=RequestMethod.GET)
    public String imageWall(@PathVariable Long id, Model model) {
        populateModelWithUserData(model, id, false);
        return "imageWall";
    }

    @RequestMapping(path="/upload", method=RequestMethod.POST)
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
        try {
            String fileUrl = qiniuService.saveImage(file);
            Image image = new Image();
            image.setImgUrl(fileUrl);
            image.setIdUser(hostHolder.getUser().getId());
            imageDao.addImg(image);

            populateModelWithUserData(model, hostHolder.getUser().getId(), true);
            return "profile";
        } catch (IOException e) {
            logger.error("Error uploading image: ", e);
            return "profile";
        }
    }

    @RequestMapping(path = "/profile/message", method = RequestMethod.GET)
    public View topicsTransform(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/message");
    }

    @RequestMapping(path = "/imageWall/message", method = RequestMethod.GET)
    public View messageTransform(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/message");
    }
}
