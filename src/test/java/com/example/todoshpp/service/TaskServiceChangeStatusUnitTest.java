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
public class TaskServiceChangeStatusUnitTest {
    @Mock
    private TaskRepository repository;
    @InjectMocks
    TaskServiceJPA service;

    TaskEntity task = new TaskEntity(1, "desc1", Status.PLANNED);
    TaskEntity cancelledTask = new TaskEntity(1, "desc1", Status.CANCELLED);


    @Test
    void whenChangeFromPlannedToWorkInProgressThenStatusChange() {
        when(service.findOne(1)).thenReturn(Optional.of(task));
        TaskEntity taskReturn = new TaskEntity(1, "desc1", Status.WORK_IN_PROGRESS);

        when(repository.save(task)).thenReturn(taskReturn);

        assertEquals(service.findOne(1).get().getStatus(), Status.PLANNED);

        TaskEntity patch = service.patch(Status.WORK_IN_PROGRESS, 1);

        assertEquals(patch.getStatus(), Status.WORK_IN_PROGRESS);
        assertEquals(service.findOne(1).get().getStatus(),
                Status.WORK_IN_PROGRESS);
    }

    @Test
    void whenChangeFromPlannedToCancelledThenStatusChange() {
        when(service.findOne(1)).thenReturn(Optional.of(task));
        TaskEntity taskReturn = new TaskEntity(1, "desc1", Status.CANCELLED);
        when(repository.save(task)).thenReturn(taskReturn);

        assertEquals(service.findOne(1).get().getStatus(), Status.PLANNED);

        TaskEntity patch = service.patch(Status.CANCELLED, 1);

        assertEquals(patch.getStatus(), Status.CANCELLED);

        assertEquals(service.findOne(1).get().getStatus(), Status.CANCELLED);
    }

    @Test
    void whenChangeFromPlannedToDoneThenStatusChange() {
        when(service.findOne(1)).thenReturn(Optional.of(task));
        TaskEntity taskReturn = new TaskEntity(1, "desc1", Status.DONE);
        when(repository.save(task)).thenReturn(taskReturn);
        assertEquals(service.findOne(1).get().getStatus(), Status.PLANNED);

        TaskEntity patch = service.patch(Status.DONE, 1);

        assertEquals(patch.getStatus(), Status.DONE);

        assertEquals(service.findOne(1).get().getStatus(), Status.DONE);
    }

    @Test
    void whenChangeFromCancelledToWORK_IN_PROGRESSStatusThenStatusCancelled() {
        when(service.findOne(1)).thenReturn(Optional.of(cancelledTask));
        when(repository.save(cancelledTask)).thenReturn(cancelledTask);

        assertEquals(service.findOne(1).get().getStatus(), Status.CANCELLED);
        TaskEntity patch = service.patch(Status.WORK_IN_PROGRESS, 1);

        assertEquals(patch.getStatus(), Status.CANCELLED);

        assertEquals(service.findOne(1).get().getStatus(), Status.CANCELLED);
    }
    @Test
    void whenChangeFromCancelledToDONEStatusThenStatusCancelled() {
        when(service.findOne(1)).thenReturn(Optional.of(cancelledTask));
        when(repository.save(cancelledTask)).thenReturn(cancelledTask);

        assertEquals(service.findOne(1).get().getStatus(), Status.CANCELLED);

        TaskEntity patch = service.patch(Status.DONE, 1);

        assertEquals(patch.getStatus(), Status.CANCELLED);

        assertEquals(service.findOne(1).get().getStatus(), Status.CANCELLED);
    }
    @Test
    void whenChangeFromCancelledToPLANNEDStatusThenStatusCancelled() {
        when(service.findOne(1)).thenReturn(Optional.of(cancelledTask));
        when(repository.save(cancelledTask)).thenReturn(cancelledTask);

        assertEquals(service.findOne(1).get().getStatus(), Status.CANCELLED);

        TaskEntity patch = service.patch(Status.PLANNED, 1);

        assertEquals(patch.getStatus(), Status.CANCELLED);

        assertEquals(service.findOne(1).get().getStatus(), Status.CANCELLED);
    }
    @Test
    void whenChangeStatusToThePreviousOneThenStatusRemainsAsItWasFromWORK_IN_PROGRESSToPlanned() {
        TaskEntity taskEntity = new TaskEntity(1, "description", Status.WORK_IN_PROGRESS);

        when(service.findOne(1)).thenReturn(Optional.of(taskEntity));
        when(repository.save(taskEntity)).thenReturn(taskEntity);

        assertEquals(service.findOne(1).get().getStatus(), Status.WORK_IN_PROGRESS);

        TaskEntity patch = service.patch(Status.PLANNED, 1);

        assertEquals(patch.getStatus(), Status.WORK_IN_PROGRESS);

        assertEquals(service.findOne(1).get().getStatus(), Status.WORK_IN_PROGRESS);
    }
    @Test
    void whenChangeStatusToThePreviousOneThenStatusRemainsAsItWasFromDoneToPlanned() {
        TaskEntity taskEntity = new TaskEntity(1, "description", Status.DONE);
        when(service.findOne(1)).thenReturn(Optional.of(taskEntity));
        when(repository.save(taskEntity)).thenReturn(taskEntity);

        assertEquals(service.findOne(1).get().getStatus(), Status.DONE);

        TaskEntity patch = service.patch(Status.PLANNED, 1);

        assertEquals(patch.getStatus(), Status.DONE);

        assertEquals(service.findOne(1).get().getStatus(), Status.DONE);
    }
    @Test
    void whenChangeStatusToThePreviousOneThenStatusRemainsAsItWasFromDoneToWORK_IN_PROGRESS() {
        TaskEntity taskEntity = new TaskEntity(1, "description", Status.DONE);

        when(service.findOne(1)).thenReturn(Optional.of(taskEntity));
        when(repository.save(taskEntity)).thenReturn(taskEntity);

        assertEquals(service.findOne(1).get().getStatus(), Status.DONE);

        TaskEntity patch = service.patch(Status.WORK_IN_PROGRESS, 1);

        assertEquals(patch.getStatus(), Status.DONE);

        assertEquals(service.findOne(1).get().getStatus(), Status.DONE);
    }
}