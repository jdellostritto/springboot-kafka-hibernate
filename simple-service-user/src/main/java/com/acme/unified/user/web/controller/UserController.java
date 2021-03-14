package com.acme.unified.user.web.controller;

import java.util.List;

import javax.validation.Valid;

import com.acme.unified.user.dal.model.User;
import com.acme.unified.user.dal.model.UserBase;
import com.acme.unified.user.dal.service.AddressRepositoryImpl;
import com.acme.unified.user.dal.service.UserRepositoryImpl;
import com.acme.unified.user.web.dto.AddressDTO;
import com.acme.unified.user.web.dto.IdDTO;
import com.acme.unified.user.web.dto.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class User Controller.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
@RestController
@RequestMapping(value = "/api/1.0/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepositoryImpl uRepoImpl;
	
	@Autowired
	AddressRepositoryImpl aRepoImpl;
	
	/** 
	 * @param userDTO
	 * @return String UUID
	 */
	@RequestMapping(method=RequestMethod.POST,value = "/publish/{publish}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public IdDTO CreateUser(@PathVariable("publish") boolean publish, @RequestBody @Valid UserDTO userDTO) {
				
		Long userId = uRepoImpl.createUser(userDTO, publish);
		return new IdDTO(userId);
	}


	/** 
	 * @param userDTO
	 * @return UserBase
	 */
	@RequestMapping(method=RequestMethod.POST,value = "/address/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public IdDTO CreateUserAddress(@PathVariable("userId") Long userId, @RequestBody @Valid AddressDTO addressDTO){
		Long id;
		//If not found this will throw a 404
		User _user = uRepoImpl.findUserByUserId(userId);
		if(_user.getAddress()== null)
			id = aRepoImpl.createAddress(_user, addressDTO);
		else
			id = _user.getAddress().getAddressId();
					
		return new IdDTO(id);
    }

	/** 
	 * @param email
	 * @return User
	 */
	@GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserBase GetUser(@PathVariable("email") String email) {
		return uRepoImpl.findUserByEmailUser(email);
	}

	/** 
	 * @param country
	 * @return List<User>
	 */
	@GetMapping(value = "/country/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<User> GetUsersByAddressCountry(@PathVariable("country") String country) {
		//return uRepoImpl.findByCountry(country); 
		return uRepoImpl.findAllByCountry(country); 
		
	}
	
	
}


