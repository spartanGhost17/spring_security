package com.learning.springsecurity.controller;

import com.learning.springsecurity.model.Customer;
import com.learning.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

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
            String hashPwd = passwordEncoder.encode(customer.getPwd());//hash password with Bcrypt before saving to db
            customer.setPwd(hashPwd);
            customer.setCreate_date(new Date(System.currentTimeMillis()));
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0)
                response = new ResponseEntity<String>("Succefully created register user: "+savedCustomer.getEmail(), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<>("An exception occured during the registration \n"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/user")
    //@RequestMapping("user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }
}
