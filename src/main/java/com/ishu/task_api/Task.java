package com.ishu.task_api;

import jakarta.validation.constraints.NotBlank;

public class Task {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;
    private boolean completed;

    public Task() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}