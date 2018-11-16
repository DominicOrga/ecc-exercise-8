package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Collectors;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.Before;

public class RoleServiceTest {

	private RoleService roleService;

	@Before
	public void setup() {
		this.roleService = new RoleService();
	}

	@Test
	public void whenRoleGetAsStringThenMatchExpectedFormat() {
		Role role = new Role("Dev", "Dev Things");

		this.roleService.saveRole(role);

		Employee employee = generateEmployeeWithRole(role);		

		role = this.roleService.getRoleJoinedEmployees(role.getId()).get();

		String roleDetail = this.roleService.getRoleDetail(role.getId());		

		assertThat(roleDetail).isEqualTo(
			String.format(
				"ID: %d \n" +
				"Code: %s \n" +
				"Description: %s \n" +
				"Employee ID/s: %s", 
				role.getId(), 
				role.getCode(), 
				role.getDescription(),
				role.getEmployees().stream().map(e -> e.getId().toString()).collect(Collectors.joining(", "))));
	}

	public Employee generateEmployeeWithRole(Role role) {
		String firstName = "Dominic";
		String middleName = "Rivera";
		String lastName = "Orga";
		Name name = new Name(firstName, middleName, lastName);
		LocalDate birthdate = LocalDate.of(1993, 11, 5);
		LocalDate dateHired = LocalDate.of(2011, 4, 3);
		float gwa = 2.75f;
		boolean isEmployed = true;

		Employee employee = new Employee(name, birthdate, dateHired, gwa, isEmployed);

		String streetNumber = "113";
		String barangay = "San Isidro";
		String city = "Manila";
		Integer zipcode = 1920;
		Address address = new Address(streetNumber, barangay, city, zipcode, employee);

		employee.setAddress(address);

		employee.getRoles().add(role);

		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.saveEmployee(employee);

		return employee;
	}
}