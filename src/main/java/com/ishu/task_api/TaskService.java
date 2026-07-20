package com.ishu.task_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        logger.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        logger.info("Fetching task with id: {}", id);
        Task task = taskRepository.findById(id);
        if (task == null) {
            logger.warn("Task not found with id: {}", id);
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    public Task createTask(Task task) {
        logger.info("Creating task with title: {}", task.getTitle());
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        logger.info("Updating task with id: {}", id);
        Task existing = taskRepository.findById(id);
        if (existing == null) {
            logger.warn("Attempted to update non-existent task with id: {}", id);
            throw new TaskNotFoundException(id);
        }
        updatedTask.setId(id);
        return taskRepository.save(updatedTask);
    }

    public void deleteTask(Long id) {
        logger.info("Deleting task with id: {}", id);
        Task existing = taskRepository.findById(id);
        if (existing == null) {
            logger.warn("Attempted to delete non-existent task with id: {}", id);
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}