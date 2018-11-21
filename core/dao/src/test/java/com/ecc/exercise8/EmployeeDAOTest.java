package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.After;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

public class EmployeeDAOTest {

	private EmployeeDAO employeeDAO;

	private Name name;
	private LocalDate birthdate;
	private LocalDate dateHired;
	private float gwa;
	private boolean isEmployed;
	private Address address;

	private Employee employee;
	private List<Role> roleCollector = new ArrayList<>();

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

		this.employee = new Employee(this.name, this.birthdate, this.dateHired, this.gwa, this.isEmployed);

		String streetNumber = "123";
		String barangay = "San Isidro";
		String city = "Manila";
		Integer zipcode = 1920;
		this.address = new Address(streetNumber, barangay, city, zipcode, this.employee);

		this.employee.setAddress(this.address);
	}

	@Before
	public void setupEmployeeDAO() {
		employeeDAO = new EmployeeDAO();
	}

	@Test
	public void whenEmployeeIsPersistedThenEmployeeWillHaveAnId() {		
		this.employee.setId(null);

		employeeDAO.saveEmployee(this.employee);

		assertThat(employee.getId()).isNotNull();
	}

	@Test 
	public void givenNameWhenEmployeeIsSavedAndLoadedThenNameIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getName()).isEqualTo(this.employee.getName());
	}

	@Test
	public void givenNameWhenEmployeIsUpdatedAndLoadedThenNameIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		Name newName = new Name("Ariana", "Rivera", "Grande");
		this.employee.setName(newName);
		employeeDAO.updateEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getName()).isEqualTo(newName);
	}

	@Test
	public void givenBirthDateWhenEmployeeIsSavedAndLoadedThenBirthdateIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getBirthDate().isEqual(this.employee.getBirthDate())).isTrue();
	}

	@Test
	public void givenBirthDateWhenEmployeeIsUpdatedAndLoadedThenBirthdateIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		LocalDate newBirthDate = LocalDate.of(1922, 01, 02);
		this.employee.setBirthDate(newBirthDate);
		employeeDAO.updateEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getBirthDate().isEqual(newBirthDate)).isTrue();
	}

	@Test
	public void givenDateHiredWhenEmployeeIsSavedAndLoadedThenDateHiredIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getDateHired().isEqual(this.employee.getDateHired())).isTrue();
	}

	@Test
	public void givenDateHiredWhenEmployeeIsUpdatedAndLoadedThenDateHiredIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		LocalDate newDateHired = LocalDate.of(1935, 3, 02);
		this.employee.setDateHired(newDateHired);
		employeeDAO.updateEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getDateHired().isEqual(newDateHired)).isTrue();
	}

	@Test
	public void givenGWAWhenEmployeeIsSavedAndLoadedThenGWAIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getGWA()).isEqualTo(this.employee.getGWA());
	}

	@Test
	public void givenGWAWhenEmployeeIsUpdatedAndLoadedThenGWAIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		float newGWA = 1.5f;
		this.employee.setGWA(newGWA);
		employeeDAO.updateEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getGWA()).isEqualTo(newGWA);
	}

	@Test
	public void givenEmployedStatusWhenEmployeeIsSavedAndLoadedThenEmployedStatusIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().isEmployed()).isEqualTo(this.employee.isEmployed());
	}

	@Test
	public void givenEmployedStatusWhenEmployeeIsUpdatedAndLoadedThenEmployedStatusIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		this.employee.setEmployed(false);
		employeeDAO.updateEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().isEmployed()).isFalse();
	}

	@Test
	public void givenAnAddressWhenEmployeeIsSavedAndLoadedThenAddressIsPersisted() {
		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getAddress().toString()).isEqualTo(employee.getAddress().toString());
	}

	@Test
	public void givenAContactWhenEmployeeIsSavedAndLoadedThenContactIsPersisted() {
		Contact contact1 = new Contact(Contact.ContactType.mobile, "22222222222", this.employee);
		this.employee.getContacts().add(contact1);
		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId(), true, false);
		Contact contact2 = employee2.get().getContacts().iterator().next();

		assertThat(contact2).isEqualTo(contact1);
	}

	@Test
	public void givenNContactsWhenEmployeeSavedAndLoadedThenContactListSizeIsN() {
		Contact contact1 = new Contact(Contact.ContactType.mobile, "22222222222", this.employee);
		Contact contact2 = new Contact(Contact.ContactType.email, "pikapika@gmail.com", this.employee);
		Contact contact3 = new Contact(Contact.ContactType.landline, "4324323", this.employee);

		this.employee.getContacts().add(contact1);
		this.employee.getContacts().add(contact2);
		this.employee.getContacts().add(contact3);

		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId(), true, false);

		assertThat(employee2.get().getContacts().size()).isEqualTo(3);
	}

	@Test
	public void whenDuplicateContactsAreAddedThenOnlyOneIsAdded() {
		Contact contact1 = new Contact(Contact.ContactType.mobile, "22222222222", this.employee);

		this.employee.getContacts().add(contact1);
		this.employee.getContacts().add(contact1);

		employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId(), true, false);

		assertThat(employee2.get().getContacts().size()).isEqualTo(1);
	}

	@Test
	public void whenEmployeeContactIsRemovedThenContactIsDeleted() {
		Contact contact1 = new Contact(Contact.ContactType.mobile, "22222222222", this.employee);
		this.employee.getContacts().add(contact1);
		employeeDAO.saveEmployee(this.employee);

		// contact1.setEmployee(this.employee);
		this.employee.getContacts().remove(contact1);

		employeeDAO.updateEmployee(this.employee);

		Employee employee2 = employeeDAO.getEmployee(this.employee.getId(), true, false).get();
		assertThat(employee2.getContacts().size()).isEqualTo(0);
	}

	public void givenARoleWhenEmployeeIsSavedAndLoadedThenRoleIsPersisted() {
		Role role = new Role("Dev", "Dev Things");
		RoleDAO roleDAO = new RoleDAO();
		roleDAO.saveRole(role);

		this.roleCollector.add(role);

		this.employee.getRoles().add(role);
		this.employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId(), false, true);

		Role role2 = employee2.get().getRoles().iterator().next();

		assertThat(role2).isEqualTo(role);
	}

	@Test
	public void givenNRolesWhenEmployeeSavedAndLoadedThenRoleListSizeIsN() {
		Role role1 = new Role("Dev", "Dev Things");
		Role role2 = new Role("QA", "QA Stuffs");
		Role role3 = new Role("BA", "BA Things and Stuffs");

		this.roleCollector.add(role1);
		this.roleCollector.add(role2);
		this.roleCollector.add(role3);

		RoleDAO roleDAO = new RoleDAO();
		roleDAO.saveRole(role1);
		roleDAO.saveRole(role2);
		roleDAO.saveRole(role3);

		this.employee.getRoles().add(role1);
		this.employee.getRoles().add(role2);
		this.employee.getRoles().add(role3);

		this.employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId(), false, true);

		assertThat(employee2.get().getRoles().size()).isEqualTo(3);
	}

	@Test
	public void whenDuplicateRolesAreAddedThenOnlyOneIsAdded() {
		Role role1 = new Role("Dev", "Dev Things");
		RoleDAO roleDAO = new RoleDAO();
		roleDAO.saveRole(role1);

		this.roleCollector.add(role1);

		this.employee.getRoles().add(role1);
		this.employee.getRoles().add(role1);

		this.employeeDAO.saveEmployee(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId(), false, true);

		assertThat(employee2.get().getRoles().size()).isEqualTo(1);
	}

	@Test
	public void whenEmployeeIsDeletedThenPersistDeletion() {
		employeeDAO.saveEmployee(this.employee);

		employeeDAO.removeEmployee(this.employee.getId());

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.isPresent()).isFalse();
	}

	@After
	public void removePersistedRoles() {
		RoleDAO roleDAO = new RoleDAO();

		for (Role role : this.roleCollector) {
			if (role.getId() == null) {
				continue;
			}

			Optional<Role> role2 = roleDAO.getRole(role.getId());

			if (role2.isPresent()) {
				roleDAO.removeRole(role2.get().getId());
			}
		}

		this.roleCollector.clear();
	}

	@After
	public void removePersistedEmployee() {
		if (this.employee.getId() == null) {
			return;
		}

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		if (employee2.isPresent()) {
			employeeDAO.removeEmployee(employee2.get().getId());
		}
	}
}