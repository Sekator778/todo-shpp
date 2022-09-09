package com.example.todoshpp.config;

import com.example.todoshpp.model.RoleEntity;
import com.example.todoshpp.model.UserEntity;
import com.example.todoshpp.model.attribut.Role;
import com.example.todoshpp.repository.RoleRepository;
import com.example.todoshpp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, RoleRepository roleRepository) { // user userRepository for ease of testing with a built-in user
        Logger log = LoggerFactory.getLogger(CommandLineRunner.class);
        return args -> {
            RoleEntity role_admin = new RoleEntity();
            role_admin.setRole(Role.ADMIN);
            RoleEntity role_user = new RoleEntity();
            role_user.setRole(Role.USER);
            roleRepository.save(role_admin);
            roleRepository.save(role_user);
            UserEntity admin = UserEntity.of("admin", role_admin);
            userRepository.save(admin);
            log.info("#####  database for test initialize ######");
        };
    }

}
