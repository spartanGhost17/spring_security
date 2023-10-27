package com.learning.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/balance/")
public class BalanceController {

    @GetMapping(value = "myBalance")
    public String getBalanceDetails() {
        return "Here is are the balance details coming from DB";
    }

}
