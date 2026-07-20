package com.ishu.task_api;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idCounter.incrementAndGet());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public void deleteById(Long id) {
        tasks.remove(id);
    }
}