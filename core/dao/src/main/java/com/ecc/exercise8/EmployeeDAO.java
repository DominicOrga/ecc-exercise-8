package com.ecc.exercise8;

import com.ecc.exercise8.SessionUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDAO {

	public void save(Employee employee) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(employee);
			tx.commit();
		}
	}	
}