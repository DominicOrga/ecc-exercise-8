package com.ecc.exercise8;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeService {

 	private EmployeeDAO employeeDAO = new EmployeeDAO();

	public void saveEmployee(Employee employee) {
		employeeDAO.saveEmployee(employee);
	}

	public Optional<Employee> getEmployee(Long id) {
		return getEmployee(id, false, false);
	}

	public Optional<Employee> getEmployee(Long id, boolean isContactsInitialized, boolean isRolesInitialized) {
		return employeeDAO.getEmployee(id, isContactsInitialized, isRolesInitialized);
	}

	public List<Employee> getEmployees() {
		return getEmployees(false, false);
	}

	public List<Employee> getEmployees(boolean isContactsInitialized, boolean isRolesInitialized) {
		return employeeDAO.getEmployees(isContactsInitialized, isContactsInitialized);
	}

	public List<Employee> getEmployeesSorted(String column, boolean isContactsInitialized, boolean isRolesInitialized) {
		return employeeDAO.getEmployeesSorted(column, isContactsInitialized, isRolesInitialized);
	}

	public String getEmployeeDetail(Long id) {
		Optional<Employee> employee = employeeDAO.getEmployee(id, true, true); 

		if (!employee.isPresent()) {
			return null;
		}

		return getEmployeeDetail(employee.get());
	}

	public String getEmployeeDetail(Employee employee) {
		return String.format(
			"ID: %d \n" +
			"Name: %s \n" +
			"Birth Date: %s \n" +
			"Date Hired: %s \n" +
			"GWA: %s \n" +
			"Employement Status: %s \n" +	
			"Address: %s \n" +
			"Contact/s: %s \n" +
			"Role/s: %s\n", 
			employee.getId(), 
			employee.getName(), 
			employee.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
			employee.getDateHired().format(DateTimeFormatter.ISO_LOCAL_DATE),
			employee.getGWA(),
			employee.isEmployed() ? "Employed" : "Unemployed",
			employee.getAddress(),
			employee.getContacts().stream()
									   .map(c -> c.getType() + ": " + c.getValue())
									   .collect(Collectors.joining(", ")),
			employee.getRoles().stream()
							   .map(Role::getCode)
							   .collect(Collectors.joining(", ")));
	}

	public String getEmployeeDetails() {
		return getEmployees(true, true)
			   .stream()
			   .map(employee -> getEmployeeDetail(employee))
			   .collect(Collectors.joining("\n"));
	}

	public String getEmployeeDetailsSorted(String column) {
		return getEmployeesSorted(column, true, true)
			   .stream()
			   .map(employee -> getEmployeeDetail(employee))
			   .collect(Collectors.joining("\n"));
	}

	public void updateEmployee(Employee employee) {
		this.employeeDAO.updateEmployee(employee);
	}

	public void removeEmployee(Long id) {
		this.employeeDAO.removeEmployee(id);
	}
}