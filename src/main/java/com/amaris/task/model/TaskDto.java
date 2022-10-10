package com.amaris.task.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskDto {
	private Long id;
	private String description;
	private LocalDate dueDate;
	private LocalDate creationDate;
	private LocalDate lastUpdate;
	private Long assignee;
}
