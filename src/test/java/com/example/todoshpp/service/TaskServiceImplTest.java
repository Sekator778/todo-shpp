package com.example.todoshpp.service;

import com.example.todoshpp.controller.TaskController;
import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskServiceImplTest {
    @MockBean
    private TaskServiceImpl service;
    TaskEntity task1 = new TaskEntity(1, "desc1", Status.PLANNED);
    TaskEntity task2 = new TaskEntity(2, "desc2", Status.PLANNED);

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void createRecord_success() throws Exception {
        TaskEntity task1 = new TaskEntity(3, "desc3", Status.PLANNED);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(task1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    void findAll() throws Exception {
        List<TaskEntity> tasks = Arrays.asList(
                task1, task2
        );
        when(service.findAll()).thenReturn(tasks);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Is.is("desc1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Is.is("desc2")));
    }

    @Test
    void findOne() throws Exception {
        when(service.findOne(1)).thenReturn(Optional.of(task1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("desc1")));
    }

    @Test
    void saveOrUpdate() throws Exception {
        TaskEntity updatedRecord = new TaskEntity(1, "", Status.PLANNED);

        Mockito.when(service.findOne(updatedRecord.getId())).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("Length between 3 - 30")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    // TODO fix its
    @Test
    void patch() throws Exception {
//        when(service.findOne(1)).thenReturn(Optional.of(task1));

//        assertEquals(service.findOne(1).get().getStatus(),                Status.PLANNED);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.patch("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(Status.WORK_IN_PROGRESS));
//                .content(this.mapper.writeValueAsString("1"));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("Length between 3 - 30")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void deleteTaskEntity() {
    }
}