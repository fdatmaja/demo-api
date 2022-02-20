package com.juaracoding.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaracoding.demoapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
