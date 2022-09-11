package com.example.todoshpp.service;

import com.example.todoshpp.controller.TaskController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringTestConfig.class)
public class TaskServiceIntegrationTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskController taskController;
    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();
    @BeforeEach
    public void init() {
//        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

}
