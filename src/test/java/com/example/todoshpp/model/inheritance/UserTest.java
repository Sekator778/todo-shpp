package com.example.todoshpp.model.inheritance;

import com.example.todoshpp.repository.DeveloperRepository;
import com.example.todoshpp.repository.ManagerRepository;
import com.example.todoshpp.repository.UserFatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by user on 12 лис, 2022
 */
@SpringBootTest
class UserTest {
    @Autowired
    private UserFatherRepository userFatherRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Test
    void checkInheritance() {

        Developer developer = Developer.builder()
                .name("Alex")
                .city("Lisbon")
                .phone("777")
                .repository("https://github.com/Sekator778")
                .build();
        developerRepository.save(developer);

        Manager manager = Manager.builder()
                .name("Sofia")
                .city("Paris")
                .phone("111")
                .projectName("Java 18: Vector API")
                .build();
        managerRepository.save(manager);

        Developer developerByRepository = developerRepository.findFirstByRepository("https://github.com/Sekator778");
        assertThat(developerByRepository.getName()).isEqualTo("Alex");



    }
}
