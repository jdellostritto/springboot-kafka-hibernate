package com.acme.unified.user.web.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import javax.validation.constraints.NotNull;
    
import lombok.Getter;
import lombok.Setter;
    
/**
 * The Class Address DTO.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits 
 */
@Getter
@Setter
public class AddressDTO{
    @NotNull    
    private String address_1;
       
    @NotNull
    private String address_2;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zipcode;

    @NotNull
    private String country;
        
    public AddressDTO(String address_1, String address_2, String city, String state, String zipcode, String country) {
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
     }

         
}    

