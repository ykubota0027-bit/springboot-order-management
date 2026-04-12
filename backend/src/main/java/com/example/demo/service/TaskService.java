package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void create(String title, String dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setDueDate(dueDate);
        repository.save(task);
    }

    public List<Task> selectAll() {
        return repository.findAll();
    }
}
