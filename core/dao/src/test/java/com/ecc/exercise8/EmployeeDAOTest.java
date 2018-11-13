package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class EmployeeDAOTest {

	private EmployeeDAO employeeDAO;

	@Before
	public void setupEmployeeDAO() {
		employeeDAO = new EmployeeDAO();
	}

	@Test
	public void whenSaveThenIdMustExist() {		
		String firstName = "Dominic";
		String middleName = "Rivera";
		String lastName = "Orga";

		Name name = new Name(firstName, middleName, lastName);

		Employee employee = new Employee();
		employee.setName(name);
		employee.setGWA(3f);
		employee.setIsEmployed(false);

		employeeDAO.save(employee);

		assertThat(employee.getId()).isNotNull();
	}

	@Test
	public void whenEmployeeSavedAndLoadedThenMatchExpectedBirthdate() {
		LocalDate date = LocalDate.now();

		Employee employee = new Employee();
		employee.setBirthDate(date);

		employeeDAO.save(employee);

		assertThat(employee.getBirthDate()).isEqualTo(date);
	}

	@Test
	@Ignore
	public void whenEmployeeSavedAndLoadedThenMatchExpectedDateHired() {

	}


}