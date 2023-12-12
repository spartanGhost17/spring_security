package com.learning.springsecurity.filter;

import jakarta.servlet.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesAtFilter implements Filter {
    private final Logger LOGGER = Logger.getLogger(AuthoritiesAtFilter.class.getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//will have all the authentication information
        if(null != authentication) {
            LOGGER.info("Authentication Validation is in progress dude :-)");
        }
        chain.doFilter(request, response);
    }
}
