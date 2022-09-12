package com.example.todoshpp.config;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DevelopmentConfig {
    @Bean
    public CommandLineRunner tasksLoader(TaskRepository repository) {
        return args -> {
            if (repository.findById(1).isEmpty()) {
                TaskEntity taskOne = new TaskEntity();
                taskOne.setDescription("learn Java every day");
                TaskEntity taskTwo = new TaskEntity();
                taskTwo.setDescription("learn English every day");
                taskOne.setStatus(Status.PLANNED);
                taskOne.setDone("undone");
                taskTwo.setStatus(Status.PLANNED);
                taskTwo.setDone("undone");
                log.info("load default tasks");
                repository.save(taskOne);
                repository.save(taskTwo);
            }
        };
    }
}
