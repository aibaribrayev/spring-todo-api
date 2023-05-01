package com.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    private LocalDateTime dueDate;
    private String status = "Pending";
    private boolean isCompleted = false;
    private int ownerId;

    public Task(Integer id, String description, User user, LocalDateTime dueDate, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.setStatus();
        this.ownerId = user.getId();
    }

    protected Task() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setUser(User user) {
        this.user = user;
        this.ownerId = user.getId();
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        // check if the due date is in the future and the task is not completed
        setStatus();
    }

    public boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
        setStatus();
    }

    public String getStatus() {
        return status;
    }

    public String setStatus() {
        if (isCompleted) {
            this.status = "Completed";
        } else {
            // check if the due date is in the past
            LocalDateTime now = LocalDateTime.now();
            if (dueDate != null && dueDate.isBefore(now)) {
                this.status = "Timed out";
            } else {
                this.status = "Pending";
            }
        }
        return status;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}
