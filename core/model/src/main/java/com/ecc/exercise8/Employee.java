package com.ecc.exercise8;

import java.util.Set;
import java.util.HashSet;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;

@Entity
@Table(name = EmployeeContract.TABLE_NAME)
public class Employee {
	@Id
	@GeneratedValue
	@Column(name = EmployeeContract.COLUMN_ID)
	private Long id;
	
	@Embedded
	private Name name;

	@Column(name = EmployeeContract.COLUMN_BIRTH_DATE, nullable = false)
	private LocalDate birthDate;

	@Column(name = EmployeeContract.COLUMN_DATE_HIRED, nullable = false)
	private LocalDate dateHired;

	@Column(name = EmployeeContract.COLUMN_GWA, nullable = false)
	private Float gwa;

	@Column(name = EmployeeContract.COLUMN_IS_EMPLOYED, nullable = false)
	private Boolean isEmployed;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = EmployeeContract.COLUMN_ADDRESS_ID, nullable = false)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "employee")
	private Set<Contact> contacts = new HashSet<>();

	@ManyToMany
	@JoinTable(
		name = EmployeeRoleContract.TABLE_NAME,
		joinColumns = { @JoinColumn(name = EmployeeRoleContract.COLUMN_EMPLOYEE_ID) },
		inverseJoinColumns = { @JoinColumn(name = EmployeeRoleContract.COLUMN_ROLE_ID) }
	)
	private Set<Role> roles = new HashSet<>();

	public Employee() {}

	public Employee(Name name, LocalDate birthDate, LocalDate dateHired, Float gwa, Boolean isEmployed, Address address) {
		this.name = name;
		this.birthDate = birthDate;
		this.dateHired = dateHired;
		this.gwa = gwa;
		this.isEmployed = isEmployed;
		this.address = address;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Name getName() {
		return this.name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Float getGWA() {
		return this.gwa;
	}

	public void setGWA(Float gwa) {
		this.gwa = gwa;
	}

	public Boolean isEmployed() {
		return this.isEmployed;
	}

	public void setEmployed(Boolean isEmployed) {
		this.isEmployed = isEmployed;
	}

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getDateHired() {
		return this.dateHired;
	}

	public void setDateHired(LocalDate dateHired) {
		this.dateHired = dateHired;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}