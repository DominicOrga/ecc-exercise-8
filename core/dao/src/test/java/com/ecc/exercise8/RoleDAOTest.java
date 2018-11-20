package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

public class RoleDAOTest {

	private RoleDAO roleDAO;

	private String code;
	private String description;
	
	private Role role;
	private List<Employee> employeeCollector = new ArrayList<>();

	@Before
	public void setupRole() {
		this.code = "Dev";
		this.description = "Dev Things";

		this.role = new Role(this.code, this.description);
	}

	@Before
	public void setupRoleDAO() {
		this.roleDAO = new RoleDAO();
	}

	@Test
	public void whenRoleSavedThenRoleIsPersisted() {
		this.role.setId(null);

		roleDAO.saveRole(this.role);

		assertThat(this.role.getId()).isNotNull();
	}

	@Test
	public void givenCodeWhenRoleIsSavedAndLoadedThenCodeIsPersisted() {
		roleDAO.saveRole(this.role);

		Role role2 = roleDAO.getRole(this.role.getId()).get();

		assertThat(role2.getCode()).isEqualTo(this.code);
	}

	@Test
	public void givenCodeWhenRoleIsUpdatedAndLoadedTheCodeIsPersisted() {
		roleDAO.saveRole(this.role);

		this.role.setCode("QA");
		roleDAO.updateRole(this.role);

		Role role2 = roleDAO.getRole(this.role.getId()).get();

		assertThat(role2.getCode()).isEqualTo("QA");
	}

	@Test
	public void givenDescriptionWhenRoleIsSavedAndLoadedThenDescriptionIsPersisted() {
		roleDAO.saveRole(this.role);

		Role role2 = roleDAO.getRole(this.role.getId()).get();

		assertThat(role2.getDescription()).isEqualTo(this.description);
	}

	@Test
	public void givenDescriptionWhenRoleIsUpdatedAndLoadedThenDescriptionIsPersisted() {
		roleDAO.saveRole(this.role);

		this.role.setDescription("QA Stuffs");
		roleDAO.updateRole(this.role);

		Role role2 = roleDAO.getRole(this.role.getId()).get();

		assertThat(role2.getDescription()).isEqualTo("QA Stuffs");
	}

	@Test
	public void whenRoleIsDeletedThenRoleIsNotPersisted() {
		roleDAO.saveRole(this.role);
		roleDAO.removeRole(this.role.getId());

		Optional<Role> role2 = roleDAO.getRole(this.role.getId());

		assertThat(role2.isPresent()).isFalse();
	}
	
	@Test
	public void whenRoleIsDeletedThenCascadeOnEmployees() {
		roleDAO.saveRole(this.role);

		Employee employee = generateEmployeeWithRole(this.role);

		roleDAO.removeRole(this.role.getId());

		Optional<Role> role2 = roleDAO.getRole(this.role.getId());
		assertThat(role2.isPresent()).isFalse();
	}

	@Test
	public void whenRoleIsUpdatedThenCascadeOnEmployees() {
		roleDAO.saveRole(this.role);

		Employee employee = generateEmployeeWithRole(this.role);

		this.role.setCode("HR");
		roleDAO.updateRole(this.role);

		EmployeeDAO employeeDAO = new EmployeeDAO();
		employee = employeeDAO.getEmployee(employee.getId(), false, true).get();

		assertThat(employee.getRoles().iterator().next().getCode()).isEqualTo("HR");
	}

	@After
	public void removePersistedEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		for (Employee employee : this.employeeCollector) {
			if (employee.getId() == null) {
				continue;
			}

			Optional<Employee> employee2 = employeeDAO.getEmployee(employee.getId());

			if (employee2.isPresent()) {
				employeeDAO.removeEmployee(employee2.get().getId());
			}
		}

		this.employeeCollector.clear();
	}

	@After
	public void removePersistedRole() {
		if (this.role.getId() == null) {
			return;
		}

		Optional<Role> role2 = roleDAO.getRole(this.role.getId());

		if (role2.isPresent()) {
			roleDAO.removeRole(role2.get().getId());
		}
	}

	private Employee generateEmployeeWithRole(Role role) {
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

		employee.getRoles().add(this.role);

		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.saveEmployee(employee);

		this.employeeCollector.add(employee);

		return employee;
	}
}