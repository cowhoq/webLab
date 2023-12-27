package com.example.web.service;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.web.dao.UserDao;
import com.example.web.model.User;;
@Service
public class RegisterService {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    public String reg(String username, String password, String introduction) {
        User user = new User();
        if (userDao.getUserByUsername(username) == null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setPassword(passwordEncoder.encode(password));
            if (Objects.equals(introduction, "")) {
                user.setIntroduction(null);
            } else {
                user.setIntroduction(introduction);
            }
            user.setCreatedDate(new Date());
            userDao.addUser(user);
            return "/login";
        } else {
            return "/register";
        }
    }

}
