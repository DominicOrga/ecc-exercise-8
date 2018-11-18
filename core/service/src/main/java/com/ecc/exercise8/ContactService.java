package com.ecc.exercise8;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ContactService {

 	private ContactDAO contactDAO = new ContactDAO();

	public void saveContact(Contact contact) {
		contactDAO.saveContact(contact);
	}

	public Optional<Contact> getContact(Long id) {
		return contactDAO.getContact(id);
	}

	public List<Contact> getContacts() {
		return contactDAO.getContacts();
	}

	public List<Contact> getContactsJoinedEmployee() {
		return contactDAO.getContactsJoinedEmployee();
	}

	public Optional<Contact> getContactJoinedEmployee(Long id) {
		return contactDAO.getContactJoinedEmployee(id);
	}

	public String getContactDetail(Long id) {
		Optional<Contact> contact = contactDAO.getContactJoinedEmployee(id); 

		if (!contact.isPresent()) {
			return null;
		}

		return String.format("ID: %d \n" +
						"Type: %s \n" +
						"Value: %s \n" +
						"Employee ID: %d\n",
						contact.get().getId(), 
						contact.get().getType(), 
						contact.get().getValue(), 
						contact.get().getEmployee().getId());
	}

	public String getContactDetails() {
		return getContactsJoinedEmployee()
			   .stream()
			   .map(contact -> getContactDetail(contact.getId()))
			   .collect(Collectors.joining("\n"));
	} 

	public void updateContact(Contact contact) {
		this.contactDAO.updateContact(contact);
	}

	public void removeContact(Long id) {
		this.contactDAO.removeContact(id);
	}
}