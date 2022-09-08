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
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                RoleEntity role_admin = new RoleEntity();
                role_admin.setRole(Role.ADMIN);
                roleRepository.save(role_admin);
                UserEntity admin = UserEntity.of("admin", role_admin);
                userRepository.save(admin);
                log.info("#####  database for test initialize ######");
            }
        };
    }

}
