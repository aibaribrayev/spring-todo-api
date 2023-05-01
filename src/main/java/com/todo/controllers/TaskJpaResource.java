package com.todo.controllers;

import com.todo.jpa.TaskRepository;
import com.todo.jpa.UserRepository;
import com.todo.models.Task;
import com.todo.errors.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class TaskJpaResource {
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/jpa/tasks")
    public List<Task> retrieveAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/jpa/task/{id}")
    public ResponseEntity<Object> updateTaskStatus(@PathVariable int id, @RequestBody Task updatedTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if(optionalTask.isEmpty())
            throw new TaskNotFoundException("id:"+id);
        Task savedTask = optionalTask.get();
        savedTask.setDescription(updatedTask.getDescription());
        savedTask.setDueDate(updatedTask.getDueDate());
        savedTask.setCompleted(updatedTask.getCompleted());

        Task updated = taskRepository.save(savedTask); // save the updated Task object to the database

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/task/{id}")
    public void deleteTask(@PathVariable int id) {
        taskRepository.deleteById(id);
    }

}
