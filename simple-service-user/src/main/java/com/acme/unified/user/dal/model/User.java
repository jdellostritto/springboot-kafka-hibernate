package com.acme.unified.user.dal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * The User Model.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends UserBase {
	
	private static final long serialVersionUID = 1L;
	static final int MAX_LENGTH = 100;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "first_name", nullable = false, length = MAX_LENGTH)
	public String getFirstName() {
		return firstName;
	}
	@Column(name = "last_name", nullable = false, length = MAX_LENGTH)
	public String getLastName() {
		return lastName;
	}
	@Column(name = "email", unique = true, nullable = false, length = MAX_LENGTH)
	public String getEmail() {
		return email;
	}
	@Column(name = "password", nullable = false, length = MAX_LENGTH)
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		return password;
	}
	//@JsonIgnore
	@JsonManagedReference
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Address getAddress() {
		return this.address;
	}

	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	
}
