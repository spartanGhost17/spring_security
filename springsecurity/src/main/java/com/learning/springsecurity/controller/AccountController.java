package com.learning.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account/")
public class AccountController {

    @GetMapping(value = "myAccount")
    public String getAccountDetails() {
        return "Here are the account details from DB";
    }
}
