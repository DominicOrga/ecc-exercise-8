package com.ecc.exercise8;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ContactDAO {

	public void saveContact(Contact contact) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(contact);
			tx.commit();
		}
	}	

	public Optional<Contact> getContact(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Contact contact = (Contact) session.get(Contact.class, id);
			return Optional.ofNullable(contact);
		}
	}

	public void updateContact(Contact contact) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.update(contact);
			tx.commit();
		}
	}
}