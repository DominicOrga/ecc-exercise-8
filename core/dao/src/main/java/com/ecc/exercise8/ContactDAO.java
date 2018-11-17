package com.ecc.exercise8;

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
}