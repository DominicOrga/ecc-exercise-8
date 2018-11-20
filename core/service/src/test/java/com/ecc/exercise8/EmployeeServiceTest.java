package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

public class EmployeeServiceTest {

	private Employee employee;
	private Role role;

	private EmployeeService employeeService;

	@Before
	public void setupRole() {
		this.role = new Role("Dev", "Dev Things");
		RoleDAO roleDAO = new RoleDAO();
		roleDAO.saveRole(this.role);
	}

	@Before
	public void setupEmployee() {
		String firstName = "Dominic";
		String middleName = "Rivera";
		String lastName = "Orga";
		Name name = new Name(firstName, middleName, lastName);

		LocalDate birthdate = LocalDate.of(1993, 11, 5);
		LocalDate dateHired = LocalDate.of(2011, 4, 3);
		float gwa = 2.75f;
		boolean isEmployed = true;

		this.employee = new Employee(name, birthdate, dateHired, gwa, isEmployed);

		String streetNumber = "1234";
		String barangay = "San Isidro";
		String city = "Manila";
		Integer zipcode = 1920;
		Address address = new Address(streetNumber, barangay, city, zipcode, this.employee);
		this.employee.setAddress(address);

		Contact contact = new Contact(Contact.ContactType.email, "pilinuts@gmail.com", this.employee);
		this.employee.getContacts().add(contact);

		this.employee.getRoles().add(this.role);
	}

	@Before
	public void setupEmployeeService() {
		this.employeeService = new EmployeeService();
	}

	@Test
	public void whenEmployeeGetAsStringThenMatchExpectedFormat() {
		this.employeeService.saveEmployee(this.employee);

		this.employee = this.employeeService.getEmployee(this.employee.getId(), true, true).get();

		String employeeDetail = this.employeeService.getEmployeeDetail(this.employee.getId());		

		assertThat(employeeDetail).isEqualTo(
			String.format(
				"ID: %d \n" +
				"Name: %s \n" +
				"Birth Date: %s \n" +
				"Date Hired: %s \n" +
				"GWA: %s \n" +
				"Employement Status: %s \n" +	
				"Address: %s \n" +
				"Contact/s: %s \n" +
				"Role/s: %s\n", 
				this.employee.getId(), 
				this.employee.getName(), 
				this.employee.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
				this.employee.getDateHired().format(DateTimeFormatter.ISO_LOCAL_DATE),
				this.employee.getGWA(),
				this.employee.isEmployed() ? "Employed" : "Unemployed",
				this.employee.getAddress(),
				this.employee.getContacts().stream()
										   .map(c -> c.getType() + ": " + c.getValue())
										   .collect(Collectors.joining(", ")),
				this.employee.getRoles().stream()
										.map(Role::getCode)
										.collect(Collectors.joining(", "))
			));
	}

	@After
	public void removePersistedRole() {
		RoleDAO roleDAO = new RoleDAO();

		List<Role> roles = roleDAO.getRoles();

		for (Role role : roles) {
			if (role.equals(this.role)) {
				roleDAO.removeRole(role.getId());
				break;
			}
		}
	}

	@After
	public void removePersistedEmployee() {
		if (this.employee.getId() == null) {
			return;
		}

		EmployeeDAO employeeDAO = new EmployeeDAO();
		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());
	
		if (employee2.isPresent()) {
			employeeDAO.removeEmployee(employee2.get().getId());
		}
	}
}