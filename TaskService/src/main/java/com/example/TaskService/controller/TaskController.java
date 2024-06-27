package com.example.TaskService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskService.model.Task;
import com.example.TaskService.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/saveTask")
	public ResponseEntity<?> saveTask(@RequestBody Task task) {
		Task saved = taskService.saveTask(task);
		return ResponseEntity.ok(saved);
	}

	@PostMapping("/deleteTask")
	public ResponseEntity<?> deleteTask(@RequestBody Task task) {
		String response = taskService.deleteTask(task);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/completeTask")
	public ResponseEntity<?> completeTask(@RequestBody Task task) {
		String response = taskService.completeTask(task);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/allTask/{username}")
	public List<Task> getTaskByUsername(@PathVariable String username) {
		return taskService.findAllUserTask(username);
	}
}
