package com.learning.springsecurity.controller;


import com.learning.springsecurity.model.AccountTransactions;
import com.learning.springsecurity.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/balance/")
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;
    @GetMapping(value = "myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);

        if (accountTransactions != null) {
            return accountTransactions;
        } else {
            return null;
        }
    }

}
