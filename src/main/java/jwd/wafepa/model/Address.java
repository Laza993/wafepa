package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblAddress")
public class Address {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "street", length = 30, nullable = false)
	private String street;
	@Column(name = "number", length = 10)
	private String number;
	
	public Address(Long id, String street, String number) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
	}
	public Address() {
		super();
	}
	public Address(String street, String number) {
		super();
		this.street = street;
		this.number = number;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
