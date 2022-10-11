package com.amaris.task.controller;

import java.util.List;

import com.amaris.task.model.EmployeeDto;
import com.amaris.task.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;


	public List<EmployeeDto> getEmployees() {
		return employeeService.getAllUsers();
	}
}
