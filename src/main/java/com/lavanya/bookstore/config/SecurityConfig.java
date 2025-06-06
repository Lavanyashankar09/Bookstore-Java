package com.lavanya.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
////        UserDetails userDetailsOne = User.withUsername("User1")
////                .password("Pass1")
////                .build();
////        UserDetails userDetailsTwo = User.withUsername("User2")
////                .password("Pass2")
////                .build();
////        UserDetails admin = User.withUsername("Admin")
////                .password("Admin1")
////                .build();
//
//        UserDetails userDetailsOne = User.withUsername("User1")
//                .password(passwordEncoder().encode("Pass1"))
//                .roles("USER").build();
//        UserDetails userDetailsTwo = User.withUsername("User2")
//                .password(passwordEncoder().encode("Pass2"))
//                .roles("USER").build();
//        UserDetails admin = User.withUsername("Admin")
//                .password(passwordEncoder().encode("Admin1"))
//                .roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(userDetailsOne,
//                userDetailsTwo, admin);
    //    }



    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrfCustomizer -> csrfCustomizer.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/bookstore/welcome","/user-info/register").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

}