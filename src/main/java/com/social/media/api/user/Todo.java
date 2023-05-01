package com.social.media.api.user;

import com.social.media.api.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


    private LocalDateTime dueDate;
    private String status;

    public Todo(Integer id, String description, User user, LocalDateTime createdDate, LocalDateTime dueDate, String status) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.dueDate = dueDate;
        this.status = status;
    }

    protected Todo() {}

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

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
