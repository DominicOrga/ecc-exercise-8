package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import org.hibernate.PropertyValueException;

public class EmployeeDAOTest {

	private EmployeeDAO employeeDAO;

	private Name name;
	private LocalDate birthdate;
	private LocalDate dateHired;
	private float gwa;
	private boolean isEmployed;
	private Employee employee;

	@Before
	public void setupEmployee() {
		String firstName = "Dominic";
		String middleName = "Rivera";
		String lastName = "Orga";
		this.name = new Name(firstName, middleName, lastName);

		this.birthdate = LocalDate.of(1993, 11, 5);
		this.dateHired = LocalDate.of(2011, 4, 3);
		this.gwa = 2.75f;
		this.isEmployed = true;

		this.employee = new Employee(
			this.name, this.birthdate, this.dateHired, this.gwa, this.isEmployed);
	}

	@Before
	public void setupEmployeeDAO() {
		employeeDAO = new EmployeeDAO();
	}

	@Test
	public void whenEmployeeSavedThenEmployeeIsPersisted() {		
		this.employee.setId(null);

		employeeDAO.save(this.employee);

		assertThat(employee.getId()).isNotNull();
	}

	@Test
	public void whenNameIsNullThenEmployeeIsNotPersisted() {
		this.employee.setId(null);
		this.employee.setName(null);

		Throwable thrown = catchThrowable(() -> {
			employeeDAO.save(this.employee);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}
}