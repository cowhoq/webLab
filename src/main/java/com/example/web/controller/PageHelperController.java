package com.example.web.controller;

import com.example.web.dao.AnswerDao;
import com.example.web.dao.MessageDao;
import com.example.web.dao.TopicDao;
import com.example.web.dao.UserDao;
import com.example.web.model.PageBean;
import com.example.web.model.Topic;
import com.example.web.model.User;
import com.example.web.service.PageService;
import com.example.web.util.ForumUtil;
import com.example.web.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Controller
public class PageHelperController {

    @Autowired
    public PageService pageService;

    //	@RequestMapping(path="/topics/{category}/{currentPage}",method=RequestMethod.GET)
//	@ResponseBody
    public String pageHelper(@PathVariable String category, @PathVariable int currentPage) {
        PageBean<Topic> pageTopic=pageService.findItemByPage(category, currentPage, 5);
        List<Topic> topics=pageTopic.getItems();
        return ForumUtil.getJSONString(1, (Map<String, Object>) topics);
    }
}
