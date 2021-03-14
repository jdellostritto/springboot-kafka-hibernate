package com.acme.unified.user.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The Class Address Model
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
@Entity
@Table(name = "address")
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address extends AddressBase {
	
	private final static long serialVersionUID = 1L;
	static final int MAX_LENGTH = 100;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", unique = true, nullable = false)
	public Long getAddressId() {
		return addressId;
	}

	@Column(name = "address_1", nullable = false, length = MAX_LENGTH)
	public String getAddress_1() {
		return address_1;
	}
	
	@Column(name = "address_2", nullable = true, length = MAX_LENGTH)
	public String getAddress_2() {
		return address_2;
	}

	@Column(name = "city", nullable = false, length = MAX_LENGTH)
	public String getCity() {
		return city;
	}

	@Column(name = "state", nullable = false, length = MAX_LENGTH)
	public String getState() {
		return state;
	}

	@Column(name = "zipcode", nullable = false, length = MAX_LENGTH)
	public String getZipcode() {
		return zipcode;
	}

	@Column(name = "country", nullable = false, length = MAX_LENGTH)
	public String getCountry() {
		return country;
	}

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public Address() {
		super();
	}
	

}
