package com.ecc.exercise8;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class Name {

	@Column(nullable=false)
	private String firstName;
	@Column(nullable=false)
	private String middleName;
	@Column(nullable=false)
	private String lastName;

	public Name() {}

	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}
}