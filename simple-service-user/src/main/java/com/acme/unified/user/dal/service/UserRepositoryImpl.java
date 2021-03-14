package com.acme.unified.user.dal.service;

import java.util.List;

import com.acme.unified.user.dal.datasource.UserRepository;
import com.acme.unified.user.dal.model.User;
import com.acme.unified.user.kafka.KafkaProducer;
import com.acme.unified.user.web.dto.UserDTO;
import com.acme.unified.user.web.error.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The User Repository Implementation.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
@Service
public class UserRepositoryImpl {

    @Autowired
    UserRepository uRepo;
    
    @Autowired
    AddressRepositoryImpl aRepoImpl;
    
    @Autowired
	private KafkaProducer kafkaProducer;

    /** 
     * @param userDTO
     * @return User
     */
    public Long createUser(final UserDTO userDTO, boolean publish){
 
        User _userIn = new User(userDTO.getFirstName(),
                             userDTO.getLastName(),
                             userDTO.getEmail(),
                             userDTO.getPassword());
        
        try {
            User userSaved = uRepo.saveAndFlush(_userIn);
            if (publish){
                kafkaProducer.send("create-user", "UserCreated with ID: "+ Long.toString(userSaved.getUserId()) );
            }
            return userSaved.getUserId(); 
        }  
        catch (RuntimeException e) {
            throw e;
        }
        
    }

    
    /** 
     * @param userId
     * @return User
     */
    public User findUserByUserId(final long userId){
        User uFound = uRepo.findByUserId(userId)
                           .orElseThrow(() -> new EntityNotFoundException(Long.toString(userId)));
          uRepo.flush();
          return uFound;
    }

    /** 
     * @param email
     * @return User
     */
    public User findUserByEmailUser(final String email){
        User uFound = uRepo.findByEmail(email)
                           .orElseThrow(() -> new EntityNotFoundException(email));
        uRepo.flush();
        return uFound;
    }
    
    /** 
     * @param country
     * @return List<User>
     */
    public List<User> findByCountry(String country){
        try{
          List<User> l_user =  uRepo.findByCountry(country);
          uRepo.flush();
          return l_user;
        }
        catch (RuntimeException e) {
            throw e;
        }
    }

}
