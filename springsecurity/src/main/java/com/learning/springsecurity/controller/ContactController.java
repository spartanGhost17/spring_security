package com.learning.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contact/")
public class ContactController {

    @GetMapping(value = "contact")
    public String saveContactInquiryDetails() {
        return "Here is are the saved contact inquiry details coming from DB";
    }
}
