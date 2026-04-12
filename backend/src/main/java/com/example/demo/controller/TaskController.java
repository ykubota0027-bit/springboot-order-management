package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@Controller
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam("title") String title, @RequestParam("dueDate") String dueDate) {
        System.out.println("タイトル：" + title);
        System.out.println("期限：" + dueDate);
        service.create(title, dueDate);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/new")
    public String showNewForm() {
        return "tasks/new";
    }

    @GetMapping("/tasks")
    public String showRedirect(Model model) {

        List<Task> tasks = service.selectAll();
        model.addAttribute("tasks", tasks);
        return "tasks-list";
    }

}
