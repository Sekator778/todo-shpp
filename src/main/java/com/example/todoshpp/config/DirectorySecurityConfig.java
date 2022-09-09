//package com.example.todoshpp.config;
//
//import com.example.todoshpp.repository.PersonRepository;
//import com.example.todoshpp.security.DirectoryUserDetailsService;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PersonRepository personRepository;
//
//    public DirectorySecurityConfig(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**").hasRole("ADMIN")
//                .antMatchers("/**").hasRole("USER")
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new DirectoryUserDetailsService(this.personRepository));
//    }
//
//}