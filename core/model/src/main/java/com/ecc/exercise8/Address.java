package com.ecc.exercise8;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private Long id;

	private String streetNumber;
	private String barangay;
	private String city;
	private Integer zipcode;

	public Address() {}

	public Address(String streetNumber, String barangay, String city, Integer zipcode) {
		this.streetNumber = streetNumber;
		this.barangay = barangay;
		this.city = city;
		this.zipcode = zipcode;
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
			   address.getZipcode().intValue() == this.zipcode.intValue();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.streetNumber.hashCode();
		result = 31 * result + this.barangay.hashCode();
		result = 31 * result + this.city.hashCode();
		result = 31 * result + this.zipcode.intValue();
		return result;
	}
}