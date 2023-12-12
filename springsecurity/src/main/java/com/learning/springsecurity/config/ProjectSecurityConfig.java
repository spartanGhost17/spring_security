package com.learning.springsecurity.config;

import com.learning.springsecurity.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {


    /**
     * determine password encoder
     *
     * @return
     */
    @Bean(value = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * focuses on defining custom security configuration
     * with disabled csrf and no cors
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean(value = "defaultSecurityFilterChain")
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        requestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        //requireExplicitSave(false) gives spring all the responsability of generating and storing JSessionId in context
        //http.securityContext().requireExplicitSave(false)
                //tell spring how to manage session using defined session management below
                //basically tells spring to create a JSessionId after initial login is completed and persist that id
                //UI will leverage that for all subsequent value
        //        .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) // this is only if the backend will generate and validate its own JSession ID

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//STATELESS: Spring Security will never create an HttpSession and it will never use it to obtain the SecurityContext
                .cors(corsCutomizer -> corsCutomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));//actual prod domain
                        configuration.setAllowedMethods(Collections.singletonList("*"));//Arrays.asList("GET", "POST", "PUT", "DELETE")
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));//Arrays.asList("Authorization", "Content-Type")
                        configuration.setExposedHeaders(Arrays.asList("Authorization"));
                        configuration.setMaxAge(3600L);
                        return configuration;
                    }
                })).csrf((csrf) -> csrf.csrfTokenRequestHandler(requestAttributeHandler).ignoringRequestMatchers("/contact/**", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) //enforced for public post methods to prevent csrf attack only if method is not GET
                        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class) //execute CsrfCookieFilter after BasicAuthenticationFilter when login is complete and persist csrf token
                        .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                        .addFilterAt(new AuthoritiesAtFilter(), BasicAuthenticationFilter.class)
                        .addFilterAfter(new AuthoritesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                        .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)//generate JWT Token after basic au
                        .addFilterBefore(new JWTTokenValidationBeforeFilter(), BasicAuthenticationFilter.class)//validate JWT Token before default validation ( basic authentication )
                .authorizeHttpRequests((requests) -> {
                        requests
                                /*.requestMatchers("/api/v1/myAccount").hasAuthority("VIEWACCOUNT")
                                .requestMatchers("/balance/**").hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE")
                                .requestMatchers("/loans/**").hasAuthority("VIEWLOANS")
                                .requestMatchers("/cards/**").hasAuthority("VIEWCARDS")*/
                                .requestMatchers("/api/v1/myAccount").hasRole("USER")
                                .requestMatchers("/cards/**").hasRole("USER")
                                .requestMatchers("/balance/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/loans/**").hasRole("USER")

                                .requestMatchers("/user").authenticated() //authenticated forces spring to secure those end-points
                                .requestMatchers("/notices/**","/register", "/contact/**").permitAll();

                });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain) http.build();
    }


    /**
     * focuses on defining custom security configuration
     * with disabled csrf and no cors
     * @param http
     * @return
     * @throws Exception
     */
    //@Bean(value = "defaultSecurityFilterChain")
    //@Order(2147483642)
    /*SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/api/v1/myAccount", "/balance/**", "/loans/**", "/cards/**", "/user").authenticated() //authenticated forces spring to secure those end-points
                    .requestMatchers("/notices/**", "/contact/**", "/register").permitAll();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain) http.build();
    }*/


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
