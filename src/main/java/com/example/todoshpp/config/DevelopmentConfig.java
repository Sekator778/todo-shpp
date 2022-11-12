package com.example.todoshpp.config;

import com.example.todoshpp.metrics.ToDoMetricInterceptor;
import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.model.inheritance.Developer;
import com.example.todoshpp.model.inheritance.Manager;
import com.example.todoshpp.repository.DeveloperRepository;
import com.example.todoshpp.repository.ManagerRepository;
import com.example.todoshpp.repository.TaskRepository;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

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

    @Bean
    public CommandLineRunner devsLoader(DeveloperRepository developerRepository) {
        return args -> {
            if (developerRepository.findById(1L).isEmpty()) {
                Developer developer = Developer.builder()
                        .name("Alex")
                        .city("Lisbon")
                        .phone("777")
                        .repository("https://github.com/Sekator778")
                        .build();
                Developer developer2 = Developer.builder()
                        .name("Olivia")
                        .city("Tokio")
                        .phone("333")
                        .repository("github.com/Sekator778")
                        .build();
                log.info("load default developer");
                developerRepository.save(developer);
                developerRepository.save(developer2);
            }
        };
    }

    @Bean
    public CommandLineRunner managersLoader(ManagerRepository managerRepository) {
        return args -> {
            if (managerRepository.findById(1L).isEmpty()) {
                Manager manager = Manager.builder()
                        .name("Sofia")
                        .city("Paris")
                        .phone("111")
                        .projectName("Java 18: Vector API")
                        .build();
                managerRepository.save(manager);
                log.info("load default manager");
                managerRepository.save(manager);
            }
        };
    }

    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry registry) {
        return new MappedInterceptor(new String[]{"/**"}, new ToDoMetricInterceptor(registry));
    }
}
