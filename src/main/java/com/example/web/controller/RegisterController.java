package com.example.web.controller;

import com.example.web.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService;

}
