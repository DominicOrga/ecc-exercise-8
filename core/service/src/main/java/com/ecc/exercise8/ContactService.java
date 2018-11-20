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

	public String getContactDetail(Long id) {
		Optional<Contact> contact = contactDAO.getContact(id); 

		if (!contact.isPresent()) {
			return null;
		}

		return getContactDetail(contact.get());
	}

	public String getContactDetail(Contact contact) {
		return String.format("ID: %d \n" +
			"Type: %s \n" +
			"Value: %s \n" +
			"Employee ID: %d\n",
			contact.getId(), 
			contact.getType(), 
			contact.getValue(), 
			contact.getEmployee().getId());
	}

	public String getContactDetails() {
		return getContacts()
			   .stream()
			   .map(contact -> getContactDetail(contact))
			   .collect(Collectors.joining("\n"));
	} 

	public void updateContact(Contact contact) {
		this.contactDAO.updateContact(contact);
	}

	public void removeContact(Long id) {
		this.contactDAO.removeContact(id);
	}
}