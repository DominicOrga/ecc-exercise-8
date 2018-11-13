package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class EmployeeDAOTest {

	private EmployeeDAO employeeDAO;

	@Before
	public void setupEmployeeDAO() {
		employeeDAO = new EmployeeDAO();
	}

	@Test
	public void whenSaveThenPersistEmployee() {
		Employee employee = new Employee(4, 1.25f, true);

		employeeDAO.save(employee);
	}
}