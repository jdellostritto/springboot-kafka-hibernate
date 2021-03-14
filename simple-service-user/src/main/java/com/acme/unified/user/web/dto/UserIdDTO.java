package com.acme.unified.user.web.dto;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class User ID DTO.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits 
 */
@Getter
@Setter
public class UserIdDTO{

	private Long userId;
    	
	public UserIdDTO(@NotNull Long userId) {
		this.userId = userId;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	 }
      
}
