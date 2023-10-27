package com.learning.springsecurity.config;

import com.learning.springsecurity.model.Customer;
import com.learning.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * implement custom user authentication logic
 * and leverage spring DaoAuthenticationProvider implementation
 */
//@Service
public class EazyBankUserDetails {//implements UserDetailsService {

    /*@Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Customer> customer = customerRepository.findByEmail(username);
        UserDetails userDetails;// = User.builder().build();

        if (customer.size() <= 0) {
            throw new UsernameNotFoundException("User details not found for user " + username);
        } else {
            userName = customer.get(0).getEmail();
            password = customer.get(0).getPwd();
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
            userDetails = User.builder().
                    username(userName)
                    .password(password)
                    .authorities(authorities)
                    .build();
        }
        return userDetails;
    }*/
}
