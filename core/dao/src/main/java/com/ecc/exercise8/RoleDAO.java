package com.ecc.exercise8;

import com.ecc.exercise8.SessionUtil;

import java.util.Optional;

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
			session.delete(role);
			tx.commit();
		}
	}
}