package com.acme.unified.user.dal.model;

import java.io.Serializable;

/**
 * The Address Base Class.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
public class AddressBase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected Long addressId;
	protected String address_1;
	protected String address_2;
	protected String city;
	protected String state;
	protected String zipcode;
	protected String country;
	protected User user;
	
	public AddressBase() {
		super();
	}
	
    /********************************* 
     * @return Long
     */
    public Long getAddressId() {
		return addressId;
	}
	/** 
     * @param addressId
     */
    public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	    
    /********************************* 
     * @return String
     */
    public String getAddress_1() {
		return address_1;
	}
	/** 
     * @param address_1
     */
    public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	
    
    /********************************* 
     * @return Long
     */
    public String getAddress_2() {
		return address_2;
	}
	/** 
     * @param address_2
     */
    public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}


	/********************************* 
     * @return String
     */
	public String getCity() {
		return city;
	}
	/** 
     * @param city
     */
    public void setCity(String city) {
		this.city = city;
	}
	

    /********************************* 
     * @return String
     */
	public String getState() {
		return state;
	}
	/** 
     * @param state
     */
    public void setState(String state) {
		this.state = state;
	}
	       
    /********************************* 
     * @return String
     */
	public String getZipcode() {
		return zipcode;
	}
	
    /** 
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
    /********************************* 
     * @return String
     */
	public String getCountry() {
		return country;
	}
	/** 
     * @param country
     */
    public void setCountry(String country) {
		this.country = country;
	}
	
    /********************************* 
     * @return User Model
     */
    public User getUser() {
		return user;
	}
	/** 
     * @param user
     */
    public void setUser(User user) {
		this.user = user;
	}


}
