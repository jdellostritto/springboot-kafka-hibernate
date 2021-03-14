package com.acme.unified.user.dal.model;

import java.io.Serializable;
import java.util.List;


/**
 * The User Base Class.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
public class UserBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Long userId;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	protected List<Address> addresses;

	public UserBase() {
		super();
	}
	
	/*********************** 
	 * @return Long
	 */
	//public Long getUserId() {
	//	return userId;
	//}
	/** 
	 * @param firstName
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
		
	/***********************
	 * @return String
	 */
	//public String getFirstName() {
	//	return firstName;
	//}
	/** 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/*********************** 
	 * @return String
	 */
	//public String getLastName() {
	//	return lastName;
	//}
	/** 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/*********************** 
	 * @return String
	 */
	//public String getEmail() {
	//	return email;
	//}
	/** 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
		
	/*********************** 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*********************** 
	 * @return List<Address>
	 */
	//public List<Address> getAdresses() {
	//	return this.addresses;
	//}
	/** 
	 * @param addresses
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	


}