package com.acme.unified.user.web.dto;

import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class User DTO.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits 
 */
@Getter
@Setter
public class UserDTO{

	@NotNull
	private String firstName;
    @NotNull
	private String lastName;
    @NotNull
	private String email;
    @NotNull
    private String password;
    	
	public UserDTO(@NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	 }
      
}