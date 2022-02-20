package com.juaracoding.demoapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.demoapi.model.Employee;
import com.juaracoding.demoapi.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	private final EmployeeRepository employeeRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository){
		this.employeeRepository = employeeRepository;
	}
	
	@CrossOrigin
	@GetMapping("/employee")
	List<Employee> all(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employee")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return employeeRepository.save(newEmployee);
	}
	
	@GetMapping("/employee/{id}")
	Optional<Employee> getById(@PathVariable Long id){
		return employeeRepository.findById(id);
	}
	
	@DeleteMapping("/employee/{id}")
	void deleteById(@PathVariable Long id){
		employeeRepository.deleteById(id);
	}
	
	@PutMapping("/employee/{id}")
	Employee updateById(@PathVariable Long id, @RequestBody Employee Employee){
//		Employee emp = employeeRepository.getOne(id);
//		emp.setFirstName(Employee.getFirstName());
//		emp.setLastName(Employee.getLastName());
//		return employeeRepository.save(emp);
		
//		Optional<Employee> empId = employeeRepository.findById(id);
//		if(empId.isPresent()) {
//			Employee emp = empId.get();
//			emp.setFirstName(Employee.getFirstName());
//			emp.setLastName(Employee.getLastName());
//			return employeeRepository.save(emp);
//		}else {
//			return null;
//		}
		
		return employeeRepository.findById(id)
				.map(emp -> {
					emp.setFirstName(Employee.getFirstName());
					emp.setLastName(Employee.getLastName());
					return employeeRepository.save(emp);
				})
				.orElseGet(() -> {
					Employee.setId(id);
					return employeeRepository.save(Employee);
				});
	}
	
	
}
