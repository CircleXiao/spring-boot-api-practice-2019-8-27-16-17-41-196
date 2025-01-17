package com.tw.apistackbase.model;

public class EmployeeModel {
	private int employeeId;
	private String name;
	private int age;
	private String gender;
	private int salary;
	
	public EmployeeModel() {
		
	}

	public EmployeeModel(int employeeId, String name, int age, String gender, int salary) {
		this.employeeId = employeeId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}
