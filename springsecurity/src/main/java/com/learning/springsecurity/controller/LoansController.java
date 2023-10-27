package com.learning.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/loans/")
public class LoansController {

    @GetMapping(value = "myLoans")
    public String getLoansDetails() {
        return "Here is are the loans details coming from DB";
    }
}
