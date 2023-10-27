package com.learning.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cards/")
public class CardsController {

    @GetMapping(value = "myCards")
    public String getCardsDetails() {
        return "Here is are the cards details coming from DB";
    }
}
