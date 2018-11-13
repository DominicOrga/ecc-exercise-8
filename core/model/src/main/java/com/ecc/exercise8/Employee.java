package com.ecc.exercise8;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private Name name;

	private Float gwa;
	private Boolean isEmployed;
	private LocalDate birthDate;
	private LocalDate dateHired;

	public Employee() {}

	public Employee(Name name, Float gwa, Boolean isEmployed) {
		this.name = name;
		this.gwa = gwa;
		this.isEmployed = isEmployed;
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

	public void setIsEmployed(Boolean isEmployed) {
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
}