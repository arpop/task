package com.amaris.task.controller;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaris.task.model.TaskDto;
import com.amaris.task.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("task")
@AllArgsConstructor
public class TaskController {
	private final TaskService taskService;

	@GetMapping(path = "/unassigned")
	public List<TaskDto> getUnassignedTasks() {
		return taskService.getUnassignedTasks();
	}
	
	@GetMapping(path = "/assigned")
	public List<TaskDto> getAssignedTasks() {
		return taskService.getAssignedTasks();
	}
	
	@GetMapping
	public List<TaskDto> getAllTasks() {
		return taskService.getAllTasks();
	}
	
	@PutMapping(path = "/{taskId}", consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public TaskDto updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDto taskDto) {
		if (!taskId.equals(taskDto.getId())) {
			throw new BadRequestException("Id mismatch");
		}
		return taskService.updateTask(taskDto);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON})
	public Long createTask(@RequestBody TaskDto taskDto) {
		return taskService.create(taskDto);
	}
	
	@DeleteMapping(path = "/{taskId}")
	public void delete(@PathVariable("taskId") Long taskId) {
		taskService.delete(taskId);
	}
}
