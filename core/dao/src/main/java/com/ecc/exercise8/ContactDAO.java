package com.ecc.exercise8;

import java.util.Optional;
import java.util.List;

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

	public List<Contact> getContacts() {
		try (Session session = SessionUtil.getSession()) {
			List<Contact> contacts = session.createQuery(
					"SELECT c " +
					"FROM Contacts c", Contact.class)
				.list();

			return contacts;
		}
	}

	public Optional<Contact> getContactJoinedEmployee(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Contact contact = session.createQuery(
					"SELECT c " +
					"FROM Contact c " +
					"LEFT JOIN FETCH c.employee " +
					"WHERE c.id=:id", Contact.class)
				.setParameter("id", id)
				.uniqueResult();

			return Optional.ofNullable(contact);
		}
	}

	public List<Contact> getContactsJoinedEmployee() {
		try (Session session = SessionUtil.getSession()) {
			List<Contact> contacts = session.createQuery(
					"SELECT c " + 
					"FROM Contact c " +
					"LEFT JOIN FETCH c.employees", Contact.class)
				.list();

			return contacts;
		}
	}

	public void updateContact(Contact contact) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.update(contact);
			tx.commit();
		}
	}

	public void removeContact(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();

			Contact contact = session.load(Contact.class, id);

			session.delete(contact);
			tx.commit();
		}
	}
}