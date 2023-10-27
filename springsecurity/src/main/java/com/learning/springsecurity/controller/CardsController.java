package com.learning.springsecurity.controller;


import com.learning.springsecurity.model.Cards;
import com.learning.springsecurity.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cards/")
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping(value = "myCards")
    public List<Cards> getCardsDetails(@RequestParam int id) {
        List<Cards> cards = cardsRepository.findByCustomerId(id);
        if (cards != null) {
            return cards;
        } else {
            return null;
        }
    }
}
