package com.ecc.exercise8;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

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
		return getEmployee(id, false, false);
	}

	public Optional<Employee> getEmployee(Long id, boolean isContactsInitialized, boolean isRolesInitialized) {
		try (Session session = SessionUtil.getSession()) {

			StringBuilder queryBuilder = new StringBuilder();

			queryBuilder.append("SELECT e ");
			queryBuilder.append("FROM Employee e ");

			if (isContactsInitialized) {
				queryBuilder.append("LEFT JOIN FETCH e.contacts ");
			}

			if (isRolesInitialized) {
				queryBuilder.append("LEFT JOIN FETCH e.roles ");
			}

			queryBuilder.append("WHERE e.id=:id");

			if (isContactsInitialized || isRolesInitialized) {
				Employee employee = session.createQuery(
					queryBuilder.toString(), Employee.class)
				.setParameter("id", id)
				.uniqueResult();

				return Optional.ofNullable(employee);
			}

			Employee employee = (Employee) session.get(Employee.class, id);
			return Optional.ofNullable(employee);
		}	
	}

	public List<Employee> getEmployees() {
		return getEmployees(false, false);
	}

	public List<Employee> getEmployees(boolean isContactsInitialized, boolean isRolesInitialized) {
		try (Session session = SessionUtil.getSession()) {

			StringBuilder queryBuilder = new StringBuilder();

			queryBuilder.append("SELECT DISTINCT e ");
			queryBuilder.append("FROM Employee e ");

			if (isContactsInitialized) {
				queryBuilder.append("LEFT JOIN FETCH e.contacts ");
			}

			if (isRolesInitialized) {
				queryBuilder.append("LEFT JOIN FETCH e.roles ");
			}

			queryBuilder.append("ORDER BY e.id");

			List<Employee> employees = session.createQuery(
					queryBuilder.toString(), Employee.class)
				.list();

			return employees;
		}
	}

	public List<Employee> getEmployeesSorted(String column, boolean isContactsInitialized, 
		boolean isRolesInitialized) {
		
		if (column.equals(EmployeeContract.COLUMN_GWA)) {
			List<Employee> employees = getEmployees(isContactsInitialized, isRolesInitialized);

			return employees.stream()
							.sorted(Comparator.comparing(Employee::getGWA))
							.collect(Collectors.toList());
		}

		try (Session session = SessionUtil.getSession()) {
			StringBuilder queryBuilder = new StringBuilder();

			queryBuilder.append("SELECT DISTINCT e ");
			queryBuilder.append("FROM Employee e ");

			if (isContactsInitialized) {
				queryBuilder.append("LEFT JOIN FETCH e.contacts ");
			}

			if (isRolesInitialized) {
				queryBuilder.append("LEFT JOIN FETCH e.roles ");
			}

			switch (column) {
				case EmployeeContract.COLUMN_DATE_HIRED:
					queryBuilder.append("ORDER BY e.dateHired ");
					break;
				case NameContract.COLUMN_LAST_NAME:
					queryBuilder.append("ORDER BY e.name.lastName ");
					break;
			}

			List<Employee> employees = session.createQuery(
					queryBuilder.toString(), Employee.class)
				.list();

			return employees;
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