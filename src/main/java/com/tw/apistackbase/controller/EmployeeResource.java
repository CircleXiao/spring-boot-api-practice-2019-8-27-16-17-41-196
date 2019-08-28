package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.model.EmployeeModel;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
	List<EmployeeModel> employees = new ArrayList<EmployeeModel>();
	
	@GetMapping
	public List<EmployeeModel> getAllEmployees() {
		return employees;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<EmployeeModel> getSingleEmployee(@PathVariable int id) {
		for (EmployeeModel employeeModel : employees) {
			if (id == employeeModel.getEmployeeId()) {
				return ResponseEntity.ok(employeeModel);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/paging")
	public List<EmployeeModel> selectPagingEmployee(@RequestParam int page, @RequestParam int pageSize) {
		List<EmployeeModel> pagingEmployees = new ArrayList<EmployeeModel>();
		int totalRecords = employees.size();
		int pageSum = totalRecords % pageSize == 0 ? totalRecords/pageSize : (totalRecords/pageSize + 1);
		int startIndex = (page - 1) * pageSize;
		int endIndex = 0;
		if (page < pageSum) {
			endIndex = startIndex + pageSize - 1;
		} else {
			endIndex = totalRecords - 1;
		}
		for (int i = startIndex; i <= endIndex; i++) {
			pagingEmployees.add(employees.get(i));
		}
		return pagingEmployees;
	}
	
	@GetMapping(path = "/special")
	public List<EmployeeModel> selectSpecEmployees(@RequestParam String gender) {
		List<EmployeeModel> specEmployees = new ArrayList<EmployeeModel>();
		for (EmployeeModel employeeModel : employees) {
			if (employeeModel.getGender().equals(gender)) {
				specEmployees.add(employeeModel);
			}
		}
		return specEmployees;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmployee(@RequestBody EmployeeModel employee) {
		employees.add(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeModel> updateEmployee(@RequestBody EmployeeModel employee) {
		for (EmployeeModel employeeModel : employees) {
			if (employee.getEmployeeId() == employeeModel.getEmployeeId()) {
				employeeModel = employee;
				return ResponseEntity.ok(employeeModel);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeModel> deleteEmployee(@PathVariable int id) {
		for (EmployeeModel employeeModel : employees) {
			if (employeeModel.getEmployeeId() == id) {
				employees.remove(employeeModel);
				return ResponseEntity.ok(employeeModel);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
