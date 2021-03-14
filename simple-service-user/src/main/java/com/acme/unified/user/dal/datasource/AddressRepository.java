package com.acme.unified.user.dal.datasource;

import java.util.List;

import com.acme.unified.user.dal.model.Address;
import com.acme.unified.user.dal.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
		
    @Query("SELECT u FROM Address a, User u WHERE a.country =:country")
	List<User> findByCountry(@Param("country") String country);
}
