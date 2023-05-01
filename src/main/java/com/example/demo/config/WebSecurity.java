package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    AuthenticationManager authenticationManager (
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /*@Autowired
    void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                        .withUser("julian").password("julian123").roles("ADMIN")
                        .and()
                                .withUser("david").password("david123").roles("USER");
    }*/

    @Value("${app.password1}")
    String encodedPassword1;
    @Value("${app.password2}")
    String encodedPassword2;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("julian")
                .password(passwordEncoder().encode(encodedPassword1))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("david")
                .password(passwordEncoder().encode(encodedPassword2))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
