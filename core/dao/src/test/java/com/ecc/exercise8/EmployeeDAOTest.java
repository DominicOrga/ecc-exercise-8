package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.List;
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
	private Address address;
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

		String streetNumber = "123";
		String barangay = "San Isidro";
		String city = "Manila";
		Integer zipcode = 1920;
		this.address = new Address(streetNumber, barangay, city, zipcode);

		this.employee = new Employee(
			this.name, this.birthdate, this.dateHired, this.gwa, this.isEmployed, this.address);
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
	public void givenNameWhenEmployeeIsSavedAndLoadedThenNameIsPersisted() {
		employeeDAO.save(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getName().toString().equals(
				   this.employee.getName().toString()))
			.isTrue();
	}

	@Test
	public void whenNameIsNullThenEmployeeIsNotPersisted() {
		this.employee.setName(null);

		Throwable thrown = catchThrowable(() -> {
			employeeDAO.save(this.employee);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void givenBirthDateWhenEmployeeIsSavedAndLoadedThenBirthdateIsPersisted() {
		employeeDAO.save(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getBirthDate().isEqual(this.employee.getBirthDate())).isTrue();
	}

	@Test
	public void whenBirthDateIsNullThenEmployeeIsNotPersisted() {
		this.employee.setBirthDate(null);

		Throwable thrown = catchThrowable(() -> {
			employeeDAO.save(this.employee);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void givenDateHiredWhenEmployeeIsSavedAndLoadedThenDateHiredIsPersisted() {
		employeeDAO.save(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getDateHired().isEqual(this.employee.getDateHired())).isTrue();
	}

	@Test
	public void whenDateHiredIsNullThenEmployeeIsNotPersisted() {
		this.employee.setDateHired(null);

		Throwable thrown = catchThrowable(() -> {
			employeeDAO.save(this.employee);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void givenGWAWhenEmployeeIsSavedAndLoadedThenGWAIsPersisted() {
		employeeDAO.save(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getGWA()).isEqualTo(this.employee.getGWA());
	}

	@Test
	public void whenGWAIsNullThenEmployeeIsNotPersisted() {
		this.employee.setGWA(null);

		Throwable thrown = catchThrowable(() -> {
			employeeDAO.save(this.employee);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void givenEmployedStatusWhenEmployeeIsSavedAndLoadedThenEmployedStatusIsPersisted() {
		employeeDAO.save(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().isEmployed()).isEqualTo(this.employee.isEmployed());
	}

	@Test
	public void whenEmployedStatusIsNullThenEmployeeIsNotPersisted() {
		this.employee.setEmployed(null);

		Throwable thrown = catchThrowable(() -> {
			employeeDAO.save(this.employee);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void givenAnAddressWhenEmployeeIsSavedAndLoadedThenAddressIsPersisted() {
		employeeDAO.save(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee2.get().getAddress().toString()).isEqualTo(employee.getAddress().toString());
	}

	@Test
	public void whenAddressIsNullThenEmployeeIsNotPersisted() {
		this.employee.setAddress(null);

		Throwable thrown = catchThrowable(() -> {
			employeeDAO.save(this.employee);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);	
	}

	@Test
	@Ignore
	public void whenAddressIsRemovedThenAddressIsDeleted() {

	}

	@Test
	public void givenAContactWhenEmployeeIsSavedAndLoadedThenContactIsPersisted() {
		Contact contact1 = new Contact(Contact.ContactType.mobile, "22222222222", this.employee);
		this.employee.getContacts().add(contact1);
		employeeDAO.save(this.employee);

		Optional<Employee> employee2 = employeeDAO.getEmployeeJoinedContacts(this.employee.getId());
		Contact contact2 = employee2.get().getContacts().get(0);

		assertThat(contact2.getType()).isEqualTo(contact1.getType());
		assertThat(contact2.getValue()).isEqualTo(contact1.getValue());
		assertThat(contact2.getEmployee().getId()).isEqualTo(contact1.getEmployee().getId());
	}

	@Test
	@Ignore
	public void whenContactIsRemovedThenContactIsDeleted() {
		
	}

	// @After
	// public void removeEmployee() {
	// 	employeeDAO.remove(this.employee);
	// }
}