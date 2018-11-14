package com.ecc.exercise8;

import com.ecc.exercise8.SessionUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDAO {

	public void saveRole(Role role) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(role);
			tx.commit();
		}
	}
}