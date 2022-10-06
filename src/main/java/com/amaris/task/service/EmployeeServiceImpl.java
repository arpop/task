package com.amaris.task.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amaris.task.domain.Employee;
import com.amaris.task.model.EmployeeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	

	@Override
	public List<EmployeeDto> getAllUsers() {
		// TODO get the list of employees from the DB
		
		// return the dto list
		return Collections.emptyList();
	}
	
	private EmployeeDto entityToDto(Employee entity) {
		return EmployeeDto.builder().id(entity.getId()).name(entity.getName()).build();
	}
}
