package com.ishu.task_api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;          // fake repository, controlled by us, no real storage

    @InjectMocks
    private TaskService taskService;                // real TaskService, but fake repository plugged into its constructor

    @Test
    void createTask_shouldReturnSavedTask() {
        // Arrange
        Task inputTask = new Task();                                     // fake object representing "what the user submitted"
        inputTask.setTitle("Buy groceries");                             // give it a title
        inputTask.setDescription("Milk, eggs, bread");                   // give it a description
        inputTask.setCompleted(false);                                   // give it a completed status

        Task savedTask = new Task();                                     // fake object representing "what repository would return after saving"
        savedTask.setId(1L);                                             // give it an id, since a real save would assign one
        savedTask.setTitle("Buy groceries");                             // same title as input
        savedTask.setDescription("Milk, eggs, bread");                   // same description as input
        savedTask.setCompleted(false);                                   // same status as input

        when(taskRepository.save(inputTask)).thenReturn(savedTask);      // fake: "when save(inputTask) is called, return savedTask"

        // Act
        Task result = taskService.createTask(inputTask);                 // ONLY real call — runs your actual createTask() logic

        // Assert
        assertEquals(1L, result.getId());                                // checks the id came through correctly
        assertEquals("Buy groceries", result.getTitle());                // checks the title came through correctly
    }

    @Test
    void getTaskById_shouldThrowException_whenTaskNotFound() {
        // Arrange
        when(taskRepository.findById(99L)).thenReturn(null);             // fake: "no task exists with id 99"

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> {                // checks that calling getTaskById(99L) throws this exact exception
            taskService.getTaskById(99L);                                 // ONLY real call — runs your actual getTaskById() logic
        });
    }

    @Test
    void updateTask_shouldReturnUpdatedTask_whenTaskExists() {
        // Arrange
        Task existingTask = new Task();                                  // fake object representing "task already in the system"
        existingTask.setId(1L);                                          // give it id 1
        existingTask.setTitle("Old title");                              // give it an old title

        Task updatedTask = new Task();                                   // fake object representing "new data user wants to save"
        updatedTask.setTitle("New title");                               // only title set, no id yet — on purpose

        when(taskRepository.findById(1L)).thenReturn(existingTask);      // fake: "task with id 1 exists"
        when(taskRepository.save(updatedTask)).thenReturn(updatedTask);  // fake: "saving succeeds, returns same object"

        // Act
        Task result = taskService.updateTask(1L, updatedTask);           // ONLY real call — runs your actual updateTask() logic

        // Assert
        assertEquals("New title", result.getTitle());                    // checks title made it through correctly
        assertEquals(1L, result.getId());                                 // checks real code's setId(id) line actually ran
    }

}