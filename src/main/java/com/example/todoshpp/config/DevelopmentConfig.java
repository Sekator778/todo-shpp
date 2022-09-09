package com.example.todoshpp.config;

import com.example.todoshpp.model.Person;
import com.example.todoshpp.repository.PersonRepository;
import com.example.todoshpp.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(PersonRepository personRepository, RoleRepository roleRepository) { // user personRepository for ease of testing with a built-in user
        Logger log = LoggerFactory.getLogger(CommandLineRunner.class);
        return args -> {
            //String id, String email, String name, String password, String role, boolean enabled, LocalDate birthday, LocalDateTime created, LocalDateTime modified
            Person user = new Person("w234567865ytg567", "mail@yahoo.com", "admin", "admin",  "ADMIN", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
            Person user2 = new Person("1234567890123456", "example@yahoo.com", "admin", "admin",  "ADMIN", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
            Person admin = new Person("qwer567890oi7654", "pete@yahoo.com", "admin", "admin",  "ADMIN", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
            Person admin2 = new Person("123er45678u76543", "olga@yahoo.com", "admin", "admin",  "USER", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
            personRepository.save(user);
            personRepository.save(user2);
            personRepository.save(admin);
            personRepository.save(admin2);
            log.info("#####  database for test initialize ######");
        };
    }

}
