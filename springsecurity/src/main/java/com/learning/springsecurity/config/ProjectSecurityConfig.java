package com.learning.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.ArrayList;

@Configuration
public class ProjectSecurityConfig {


    /**
     * focuses on defining custom security configuration
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean(value = "defaultSecurityFilterChain")
    //@Order(2147483642)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/api/v1/myAccount", "/balance/**", "/loans/**", "/cards/**").authenticated() //authenticated forces spring to secure those end-points
                    .requestMatchers("/notices/**", "/contact/**", "/register").permitAll();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain) http.build();
    }

    /**
     * determine password encoder
     * @return
     */
    @Bean(value = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * no encryption for password encoder
     * @Bean(value = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/


    /**
     * Create in Memory users
     * good for testing and developing without need for persistant user data store
     * @return
     */
    /*@Bean(value = "userDetailsManager")
    public InMemoryUserDetailsManager userDetailsManager() {

        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("USER");
        SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("ADMIN");

        roles.add(role1);
        roles.add(role2);

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("12345"))
                .authorities(roles.get(0))
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("12345"))
                .authorities(roles.get(1))
                .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //new InMemoryUserDetailsManager(admin, user);
        manager.createUser(admin);
        manager.createUser(user);

        return manager;
    }*/

    /*@Bean(value = "userDetailsService")
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

}
