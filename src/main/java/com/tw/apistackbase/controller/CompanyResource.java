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

import com.tw.apistackbase.model.CompanyModel;
import com.tw.apistackbase.model.EmployeeModel;

@RestController
@RequestMapping("/companies")
public class CompanyResource {
	
	List<CompanyModel> companies = new ArrayList<CompanyModel>();
	
	@GetMapping
	public List<CompanyModel> getAllCompanies() {
		return companies;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyModel> getSingleCompany(@PathVariable String id) {
		for (CompanyModel companyModel : companies) {
			if (id.equals(companyModel.getCompanyID())) {
				return ResponseEntity.ok(companyModel);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}/employees")
	public ResponseEntity<List<EmployeeModel>> getAllEmployeesFromOneCompany(@PathVariable String id) {
		
		for (CompanyModel companyModel : companies) {
			if (id.equals(companyModel.getCompanyID())) {
				return ResponseEntity.ok(companyModel.getEmployees());
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/paging")
	public List<CompanyModel> selectPagingCompany(@RequestParam int page, @RequestParam int pageSize) {
		List<CompanyModel> pagingCompanies = new ArrayList<CompanyModel>();
		int totalRecords = companies.size();
		int pageSum = totalRecords % pageSize == 0 ? totalRecords/pageSize : (totalRecords/pageSize + 1);
		int startIndex = (page - 1) * pageSize;
		int endIndex = 0;
		if (page < pageSum) {
			endIndex = startIndex + pageSize - 1;
		} else {
			endIndex = totalRecords - 1;
		}
		for (int i = startIndex; i <= endIndex; i++) {
			pagingCompanies.add(companies.get(i));
		}
		return pagingCompanies;	
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CompanyModel createCompany(@RequestBody CompanyModel company) {
		companies.add(company);
		return company;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyModel> updateCompany(@RequestBody CompanyModel company) {
		for (CompanyModel companyModel : companies) {
			if (company.getCompanyID().equals(companyModel.getCompanyID())) {
				companyModel = company;
				return ResponseEntity.ok(company);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CompanyModel> deleteCompany(@PathVariable String id) {
		for (CompanyModel companyModel : companies) {
			if (id.equals(companyModel.getCompanyID())) {
				companies.remove(companyModel);
				return ResponseEntity.ok(companyModel);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
