package com.tw.apistackbase.model;

import java.util.List;

public class CompanyModel {
	private String companyID;
	private int employeesNumber;
	private List<EmployeeModel> employees;
	
	public CompanyModel() {
		
	}

	public CompanyModel(String companyID, int employeesNumber, List<EmployeeModel> employees) {
		this.companyID = companyID;
		this.employeesNumber = employeesNumber;
		this.employees = employees;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public int getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(int employeesNumber) {
		this.employeesNumber = employeesNumber;
	}

	public List<EmployeeModel> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeModel> employees) {
		this.employees = employees;
	}
}
