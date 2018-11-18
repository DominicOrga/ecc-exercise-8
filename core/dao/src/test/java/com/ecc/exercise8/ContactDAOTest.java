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


public class ContactDAOTest {
	private ContactDAO contactDAO;

	private Contact.ContactType type;
	private String value;
	private Employee employee;

	private Contact contact;

	@Before
	public void setupContact() {
		this.type = Contact.ContactType.landline;
		this.value = "4354321";
		this.employee = generateEmployee();

		this.contact = new Contact(this.type, this.value, this.employee);
	}

	@Before
	public void setupContactDAO() {
		this.contactDAO = new ContactDAO();
	}

	@Test
	public void whenContactSavedThenContactIsPersisted() {
		this.contact.setId(null);

		this.contactDAO.saveContact(this.contact);

		assertThat(this.contact.getId()).isNotNull();
	}

	@Test
	public void givenNewValuesWhenContactIsUpdatedAndLoadedThenContactIsPersisted() {
		this.contactDAO.saveContact(this.contact);

		this.contact.setType(Contact.ContactType.email);
		this.contact.setValue("maxima@gmail.com");

		this.contactDAO.updateContact(this.contact);

		Contact contact2 = this.contactDAO.getContact(this.contact.getId()).get();

		assertThat(contact2.getType()).isEqualTo(this.contact.getType());
		assertThat(contact2.getValue()).isEqualTo(this.contact.getValue());
	}

	@Test
	public void whenTypeIsNullThenContactIsNotPersisted() {
		this.contact.setType(null);

		Throwable thrown = catchThrowable(() -> {
			this.contactDAO.saveContact(this.contact);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void whenValueIsNullThenContactIsNotPersisted() {
		this.contact.setValue(null);

		Throwable thrown = catchThrowable(() -> {
			this.contactDAO.saveContact(this.contact);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void whenEmployeeIsNullThenContactIsNotPersisted() {
		this.contact.setEmployee(null);

		Throwable thrown = catchThrowable(() -> {
			this.contactDAO.saveContact(this.contact);
		});

		assertThat(thrown).isInstanceOf(PropertyValueException.class);
	}

	@Test
	public void whenContactIsDeletedThenContactIsNotPersisted() {
		this.contactDAO.saveContact(this.contact);
		this.contactDAO.removeContact(this.contact.getId());

		Optional<Contact> contact2 = this.contactDAO.getContact(this.contact.getId());

		assertThat(contact2.isPresent()).isFalse();
	}

	@Test
	public void whenContactIsDeletedThenEmployeeIsStillPersisted() {
		this.contactDAO.saveContact(this.contact);
		this.contactDAO.removeContact(this.contact.getId());

		EmployeeDAO employeeDAO = new EmployeeDAO();
		Optional<Employee> employee = employeeDAO.getEmployee(this.employee.getId());

		assertThat(employee.isPresent()).isTrue();
	}

	@After
	public void removePersistedEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		if (this.employee.getId() == null) {
			return;
		}

		Optional<Employee> employee2 = employeeDAO.getEmployee(employee.getId());

		if (employee2.isPresent()) {
			employeeDAO.removeEmployee(employee2.get().getId());
		}
	}

	@After
	public void removePersistedContact() {
		if (this.contact.getId() == null) {
			return;
		}

		Optional<Contact> contact2 = this.contactDAO.getContact(this.contact.getId());

		if (contact2.isPresent()) {
			this.contactDAO.removeContact(contact2.get().getId());
		}
	}

	private Employee generateEmployee() {
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

		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.saveEmployee(employee);

		return employee;
	}
}	