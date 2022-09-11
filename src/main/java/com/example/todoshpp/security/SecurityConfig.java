//package com.example.todoshpp.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
//
///**
// *
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new StandardPasswordEncoder("53cr3t");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/tasks", "/api/**")
//                .access("hasRole('USER')")
//                .antMatchers("/", "/**").access("permitAll")
//                //end::authorizeRequests[]
//
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                //end::customLoginPage[]
//
//                // tag::enableLogout[]
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                // end::enableLogout[]
//
//                // Make H2-Console non-secured; for debug purposes
//                // tag::csrfIgnore[]
//                .and()
//                .csrf()
//                .ignoringAntMatchers("/h2-console/**")
//                // end::csrfIgnore[]
//
//                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
//                // tag::frameOptionsSameOrigin[]
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin();
//    }
////tag::configureAuthentication_inMemory[]
////  @Override
////  protected void configure(AuthenticationManagerBuilder auth)
////      throws Exception {
////
////      auth
////              .inMemoryAuthentication()
////              .withUser("a")
////              .password("a")
////              .authorities("ROLE_USER")
////              .and()
////              .withUser("1")
////              .password("1")
////              .authorities("ROLE_USER");
////  }
////end::configureAuthentication_inMemory[]
//
////
//// JDBC Authentication example
////
///*
////tag::configureAuthentication_jdbc[]
//  @Autowired
//  DataSource dataSource;
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth)
//      throws Exception {
//
//    auth
//      .jdbcAuthentication()
//        .dataSource(dataSource);
//
//  }
////end::configureAuthentication_jdbc[]
//*/
//    //----------------------------------
///*
//    //tag::configureAuthentication_jdbc_withQueries[]
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from Users " +
//                                "where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities " +
//                                "where username=?");
//
//    }
////end::configureAuthentication_jdbc_withQueries[]
//*/
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .antMatchers("/design", "/orders")
////                .access("hasRole('ROLE_USER') && " +
////                        "T(java.util.Calendar).getInstance().get("+
////                        "T(java.util.Calendar).DAY_OF_WEEK) == " +
////                        "T(java.util.Calendar).WEDNESDAY")
////                .antMatchers("/", "/**").access("permitAll")
////        ;
////    }
//
//
//}
