package com.ecc.exercise8;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = AddressContract.TABLE_NAME)
public class Address {
	
	@Id
	@GeneratedValue
	@Column(name = AddressContract.COLUMN_ID)
	private Long id;

	@Column(name = AddressContract.COLUMN_STREET_NUMBER)
	@NotBlank
	private String streetNumber;

	@Column(name = AddressContract.COLUMN_BARANGAY)
	@NotBlank
	private String barangay;

	@Column(name = AddressContract.COLUMN_CITY)
	@NotBlank
	private String city;

	@Column(name = AddressContract.COLUMN_ZIPCODE)
	@NotNull
	private Integer zipcode;

	@OneToOne
	@JoinColumn(name = AddressContract.COLUMN_EMPLOYEE_ID)
	@NotNull
	private Employee employee;

	public Address() {}

	public Address(String streetNumber, String barangay, String city, Integer zipcode, Employee employee) {
		this.streetNumber = streetNumber;
		this.barangay = barangay;
		this.city = city;
		this.zipcode = zipcode;
		this.employee = employee;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getBarangay() {
		return this.barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%d", this.streetNumber, this.barangay, this.city, this.zipcode.intValue());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Address)) {
			return false;
		}

		Address address = (Address) o;

		return address.getStreetNumber().equals(this.streetNumber) &&
			   address.getBarangay().equals(this.barangay) &&
			   address.getCity().equals(this.city) &&
			   address.getZipcode().intValue() == this.zipcode.intValue() &&
			   address.getEmployee().getId().intValue() == this.employee.getId().intValue();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.streetNumber.hashCode();
		result = 31 * result + this.barangay.hashCode();
		result = 31 * result + this.city.hashCode();
		result = 31 * result + this.zipcode.intValue();
		result = 31 * result + this.employee.hashCode();
		return result;
	}
}