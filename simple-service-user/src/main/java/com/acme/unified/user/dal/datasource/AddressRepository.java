package com.acme.unified.user.dal.datasource;

import com.acme.unified.user.dal.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Address Repository. Use to override and/or customize JPA functions.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
public interface AddressRepository extends JpaRepository<Address, String> {
		
    
}
