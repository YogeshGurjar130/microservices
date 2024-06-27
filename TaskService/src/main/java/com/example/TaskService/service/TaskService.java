package com.example.TaskService.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskService.model.Task;
import com.example.TaskService.repository.TaskRepository;

@Service
public class TaskService {

	Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepository;

	public Task saveTask(Task task) {
		try {
			Task savedTask = taskRepository.save(task);
			return savedTask;
		} catch (Exception e) {
			logger.info("Exception in saveTask " + e.getLocalizedMessage());
		}
		return null;
	}

	public String deleteTask(Task task) {
		String response = null;
		try {
			Task dbTask = taskRepository.findById(task.getTaskId()).orElse(null);
			if (dbTask != null) {
				taskRepository.deleteById(task.getTaskId());
				response = "Deleted Successfully";
			} else {
				logger.info("Task in null from DB");
				response = "Task not found";
			}
		} catch (Exception e) {
			logger.info("Exception in deleteTask " + e.getLocalizedMessage());
		}

		return response;
	}

	public String completeTask(Task task) {
		String response = null;
		try {
			Task dbTask = taskRepository.findById(task.getTaskId()).orElse(null);
			if (dbTask != null) {
				dbTask.setStatus("completed");
				taskRepository.save(dbTask);
				response = "Updated successfully";
			} else {
				logger.info("Task in null from DB");
				response = "Task not found";
			}
		} catch (Exception e) {
			logger.info("Exception in completeTask " + e.getLocalizedMessage());
		}
		return response;
	}

	public List<Task> findAllUserTask(String username) {
		List<Task> taskList = null;
		try {
			taskList = taskRepository.findAllByUsername(username);
		} catch (Exception e) {
			logger.info("Exception in findAllUserTask " + e.getLocalizedMessage());
		}
		return taskList;
	}
}
