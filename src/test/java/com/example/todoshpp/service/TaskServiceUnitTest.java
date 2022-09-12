package com.example.todoshpp.service;

import com.example.todoshpp.model.TaskEntity;
import com.example.todoshpp.model.attribut.Status;
import com.example.todoshpp.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTest {
    @Mock
    private TaskRepository repository;
    @InjectMocks
    TaskServiceImpl service;

    TaskEntity task1 = new TaskEntity(1, "desc1", Status.PLANNED);


    @Test
    void patch() {
        when(service.findOne(1)).thenReturn(Optional.of(task1));

        assertEquals(service.findOne(1).get().getStatus(),
                Status.PLANNED);

        TaskEntity patch = service.patch(Status.WORK_IN_PROGRESS, 1);
        assertEquals(patch.getStatus(), Status.WORK_IN_PROGRESS);
        assertEquals(service.findOne(1).get().getStatus(),
                      Status.WORK_IN_PROGRESS);
    }
}
