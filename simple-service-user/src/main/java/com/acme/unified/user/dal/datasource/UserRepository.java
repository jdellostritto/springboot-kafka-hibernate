package com.acme.unified.user.dal.datasource;

import java.util.List;
import java.util.Optional;

import com.acme.unified.user.dal.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The User Repository. Used to override and/or customize JPA functions.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
public interface UserRepository extends JpaRepository<User, String> {
   
    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findByUserId(Long userId);
     
    @Query("SELECT u FROM Address a, User u WHERE a.country = :country")
    List<User> findByCountry(@Param("country") String country);
    
}
