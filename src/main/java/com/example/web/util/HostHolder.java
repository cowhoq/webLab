package com.example.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.example.web.dao.UserDao;
import com.example.web.model.User;
public class HostHolder {
    private User user;

    @Autowired
    UserDao userDao;


    public User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user=userDao.getUserByUsername(((UserDetails) principal).getUsername());
        return user;
    }
}
