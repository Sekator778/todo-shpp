package com.example.todoshpp.service;

import com.example.todoshpp.controller.TaskController;
import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskServiceJPA.class)
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @MockBean
    private TaskRepository repository;
    TaskEntity task1 = new TaskEntity(1, "desc1", Status.PLANNED);
    TaskEntity task2 = new TaskEntity(2, "desc2", Status.PLANNED);

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;


    @Test
    void patch() {
        when(repository.findById(1)).thenReturn(Optional.of(task1));
        assertEquals(repository.findById(1).get().getStatus(),
                Status.PLANNED);
//        TaskEntity patch = repository.patch(Status.WORK_IN_PROGRESS, 1);
//        System.out.println("=========== " + patch);
//        assertEquals(patch.getStatus(), Status.WORK_IN_PROGRESS);
//        assertEquals(service.findOne(1).get().getStatus(),
        //              Status.WORK_IN_PROGRESS);
    }
}
