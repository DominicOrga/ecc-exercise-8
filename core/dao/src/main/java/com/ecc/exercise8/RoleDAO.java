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
		try (Session session = SessionUtil.getSession()) {
			Role role = (Role) session.get(Role.class, id);
			return Optional.ofNullable(role);
		}
	}

	public Optional<Role> getRoleJoinedEmployees(Long id) {
		try (Session session = SessionUtil.getSession()) {
			Role role = session.createQuery(
					"SELECT r " +
					"FROM Role r " +
					"LEFT JOIN FETCH r.employees " +
					"WHERE r.id=:id", Role.class)
				.setParameter("id", id)
				.uniqueResult();

			return Optional.ofNullable(role);
		}
	}

	public List<Role> getRolesJoinedEmployees() {
		try (Session session = SessionUtil.getSession()) {
			List<Role> roles = session.createQuery(
					"SELECT r " + 
					"FROM Role r " +
					"LEFT JOIN FETCH r.employees", Role.class)
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

	public void removeRole(Role role) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();

			role = (Role) session.load(Role.class, role.getId());

			Set<Employee> employees = role.getEmployees();

			for (Employee employee : employees) {
				employee.getRoles().remove(role);
			}

			session.delete(role);
			tx.commit();
		}
	}
}