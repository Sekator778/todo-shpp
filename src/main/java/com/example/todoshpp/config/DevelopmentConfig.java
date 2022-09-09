//package com.example.todoshpp.config;
//
//import com.example.todoshpp.model.Person;
//import com.example.todoshpp.repository.PersonRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Configuration
//public class DevelopmentConfig {
//
//    @Bean
//    public CommandLineRunner dataLoader(PersonRepository personRepository) { // user personRepository for ease of testing with a built-in user
//        Logger log = LoggerFactory.getLogger(CommandLineRunner.class);
//        return args -> {
//            String idOne = "oneId";
//            String idTwo = "twoId";
//            //String id, String email, String name, String password, String role, boolean enabled, LocalDate birthday, LocalDateTime created, LocalDateTime modified
//            if (personRepository.findPersonByName("admin").isEmpty()) {
//                Person user = new Person(idOne, "mail@yahoo.com", "admin", "admin", "ADMIN", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//                Person user2 = new Person(idTwo, "example@yahoo.com", "user", "user", "USER", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
////                Person admin = new Person("qwer567890oi7654", "pete@yahoo.com", "admin", "admin", "ADMIN", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
////                Person admin2 = new Person("123er45678u76543", "olga@yahoo.com", "admin", "admin", "USER", true, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
//                personRepository.save(user);
//                personRepository.save(user2);
////                personRepository.save(admin);
////                personRepository.save(admin2);
//            }
//            log.info("#####  database for test initialize ######");
//        };
//    }
//
//}
