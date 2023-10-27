package com.learning.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notices/")
public class NoticesController {

    @GetMapping(value = "myNotices")
    public String getNotices() {
        return "Here is are the notices details coming from DB";
    }
}
