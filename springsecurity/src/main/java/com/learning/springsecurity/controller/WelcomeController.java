package com.learning.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/")
//@RequiredArgsConstructor
public class WelcomeController {

    @GetMapping(value = "login")
    public String welcome() {
        return "Hello this works" ;
    }

    @GetMapping(value = "myAccount")
    public String getAccountDetails() {
        return "returning user information";
    }
}
