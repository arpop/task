package com.amaris.task.service;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Service;

import com.amaris.task.domain.EmployeesReporsitory;
import com.amaris.task.domain.Task;
import com.amaris.task.domain.TaskRepository;
import com.amaris.task.model.TaskDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;
	private final EmployeesReporsitory employeesReporsitory;

	@Override
	public List<TaskDto> getAllTasks() {
		return taskRepository.findAll().stream().map(this::entityToDto).toList();
	}

	@Override
	public List<TaskDto> getAssignedTasks() {
		return taskRepository.getAllAssigned().stream().map(this::entityToDto).toList();
	}

	@Override
	public List<TaskDto> getUnassignedTasks() {
		var tasks = taskRepository.getAllUnassigned();
		return tasks.stream().map(this::entityToDto).toList();
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto) {
		if (Objects.isNull(taskDto)) {
			throw new BadRequestException("Missing task to update");
		}
		var savedTask = taskRepository.save(this.dtoToEntity(taskDto));
		return entityToDto(savedTask);
	}

	@Override
	public Long create(TaskDto taskDto) {
		var task = taskRepository.save(this.dtoToEntity(taskDto));
		return task.getId();
	}

	@Override
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}

	private TaskDto entityToDto(Task entity) {
		return TaskDto.builder().id(entity.getId())
				.assignee(Objects.nonNull(entity.getAssignee()) ? entity.getAssignee().getId() : null)
				.creationDate(entity.getCreationDate())
				.lastUpdate(entity.getLastUpdate())
				.dueDate(entity.getDueDate())
				.description(entity.getDescription()).build();
	}

	private Task dtoToEntity(TaskDto dto) {
		return Task.builder()
				.id(dto.getId())
				.assignee(Objects.nonNull(dto.getAssignee()) ? employeesReporsitory.findById(dto.getAssignee()).orElseThrow() : null)
				.dueDate(dto.getDueDate())
				.description(dto.getDescription())
				.creationDate(dto.getCreationDate())
				.lastUpdate(dto.getLastUpdate())
				.build();
	}

}
