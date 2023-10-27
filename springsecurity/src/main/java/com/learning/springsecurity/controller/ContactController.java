package com.learning.springsecurity.controller;

import com.learning.springsecurity.model.Contact;
import com.learning.springsecurity.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Random;

@RestController
@RequestMapping(value = "/contact/")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping(value = "contact")
    public Contact saveContactInquiryDetails(@RequestParam Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR"+ranNum;
    }
}
