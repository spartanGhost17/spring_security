package com.learning.springsecurity.controller;

import com.learning.springsecurity.model.Accounts;
import com.learning.springsecurity.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account/")
public class AccountController {

    @Autowired
    private AccountsRepository accountsRepository;
    @GetMapping(value = "myAccount")
    public Accounts getAccountDetails(@RequestParam int id) {

        Accounts accounts = accountsRepository.findByCustomerId(id);
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }
}
