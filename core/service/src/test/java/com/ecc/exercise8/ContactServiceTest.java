package com.ecc.exercise8;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class ContactServiceTest {

	private Contact contact;
	private Employee employee;
	private ContactService contactService;

	@Before
	public void setupContact() {
		this.employee = generateEmployee();
		this.contact = new Contact(Contact.ContactType.email, "alibaba@gmail.com", this.employee);
	}

	@Before
	public void setupContactService() {
		this.contactService = new ContactService();
	}

	@Test
	public void whenContactGetAsStringThenMatchExpectedFormat() {
		this.contactService.saveContact(this.contact);

		this.contact = this.contactService.getContactJoinedEmployee(this.contact.getId()).get();

		String contactDetail = this.contactService.getContactDetail(this.contact.getId());		

		assertThat(contactDetail).isEqualTo(
			String.format(
				"ID: %d \n" +
				"Type: %s \n" +
				"Value: %s \n" +
				"Employee ID: %d\n", 
				contact.getId(), 
				contact.getType(), 
				contact.getValue(),
				contact.getEmployee().getId()));
	}

	@After
	public void removePersistedContact() {
		if (this.contact.getId() == null) {
			return;
		}

		Optional<Contact> contact2 = this.contactService.getContact(this.contact.getId());

		if (contact2.isPresent()) {
			this.contactService.removeContact(contact2.get().getId());
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