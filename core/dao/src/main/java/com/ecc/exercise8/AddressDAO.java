package com.ecc.exercise8;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddressDAO {

	public void saveAddress(Address address) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(address);
			tx.commit();
		}
	}
}