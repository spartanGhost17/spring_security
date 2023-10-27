package com.learning.springsecurity.controller;

import com.learning.springsecurity.model.Customer;
import com.learning.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer savedCustomer = null;
        ResponseEntity response = null;

        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd); //hash password with Bcrypt before saving to db
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0)
                response = new ResponseEntity<String>("Succefully created register user: "+savedCustomer.getEmail(), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<>("An exception occured during the registration \n"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
