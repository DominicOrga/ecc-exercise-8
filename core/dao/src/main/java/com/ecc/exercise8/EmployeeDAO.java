package com.ecc.exercise8;

import java.util.Optional;

import com.ecc.exercise8.SessionUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDAO {

	public void saveEmployee(Employee employee) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(employee);
			tx.commit();
		}
	}

	public Optional<Employee> getEmployee(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Employee employee = (Employee) session.get(Employee.class, id);
			return Optional.ofNullable(employee);
		}	
	}

	public Optional<Employee> getEmployeeJoinedContacts(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Employee employee = session.createQuery(
					"SELECT e " + 
					"FROM Employee e " +
					"LEFT JOIN FETCH e.contacts " +
					"WHERE e.id=:id", Employee.class)
				.setParameter("id", id)
				.uniqueResult();

			return Optional.ofNullable(employee);
		}
	}

	public Optional<Employee> getEmployeeJoinedRoles(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Employee employee = session.createQuery(
					"SELECT e " + 
					"FROM Employee e " +
					"LEFT JOIN FETCH e.roles " +
					"WHERE e.id=:id", Employee.class)
				.setParameter("id", id)
				.uniqueResult();

			return Optional.ofNullable(employee);
		}
	}

	public void updateEmployee(Employee employee) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.update(employee);
			tx.commit();
		}
	}

	public void removeEmployee(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();

			Employee employee = session.load(Employee.class, id);
			session.remove(employee);
			tx.commit();
		}
	}
}