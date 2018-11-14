package com.ecc.exercise8;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Contact {

	public enum ContactType {
		landline, mobile, email
	}

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private ContactType type;
	
	@Column(nullable=false)
	private String value;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Employee employee;

	public Contact() {}

	public Contact(ContactType type, String value, Employee employee) {
		this.type = type;
		this.value = value;
		this.employee = employee;
	}

	public ContactType getType() {
		return this.type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}