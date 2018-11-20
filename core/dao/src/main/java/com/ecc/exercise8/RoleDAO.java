package com.ecc.exercise8;

import com.ecc.exercise8.SessionUtil;

import java.util.Optional;
import java.util.List;
import java.util.Set;

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

	public Optional<Role> getRole(Long id) {
		return getRole(id, false);
	}

	public Optional<Role> getRole(Long id, boolean isEmployeeInitialized) {
		try (Session session = SessionUtil.getSession()) {

			if (isEmployeeInitialized) {
				Role role = session.createQuery(
					"SELECT r " +
					"FROM Role r " +
					"LEFT JOIN FETCH r.employees " +
					"WHERE r.id=:id", Role.class)
				.setParameter("id", id)
				.uniqueResult();

				return Optional.ofNullable(role);
			}
			
			Role role = (Role) session.get(Role.class, id);
			return Optional.ofNullable(role);
		}
	}

	public List<Role> getRoles() {
		return getRoles(false);
	}

	public List<Role> getRoles(boolean isEmployeeInitialized) {
		try (Session session = SessionUtil.getSession()) {
			if (isEmployeeInitialized) {
				List<Role> roles = session.createQuery(
					"SELECT DISTINCT r " + 
					"FROM Role r " +
					"LEFT JOIN FETCH r.employees " +
					"ORDER BY r.id", Role.class)
				.list();

				return roles;
			}

			List<Role> roles = session.createQuery(
					"SELECT DISTINCT r " +
					"FROM Role r " +
					"ORDER BY r.id", Role.class)
				.list();

			return roles;
		}
	}

	public void updateRole(Role role) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.update(role);
			tx.commit();
		}
	}

	public void removeRole(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();

			Role role = (Role) session.load(Role.class, id);

			Set<Employee> employees = role.getEmployees();

			for (Employee employee : employees) {
				employee.getRoles().remove(role);
			}

			session.delete(role);
			tx.commit();
		}
	}
}