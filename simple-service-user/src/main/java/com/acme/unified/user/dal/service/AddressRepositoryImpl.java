package com.acme.unified.user.dal.service;

import java.util.List;

import com.acme.unified.user.dal.datasource.AddressRepository;
import com.acme.unified.user.dal.datasource.UserRepository;
import com.acme.unified.user.dal.model.Address;
import com.acme.unified.user.dal.model.User;
import com.acme.unified.user.web.dto.AddressDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Address Repo Implementation.
 *
 * @author Jim.DelloStritto
 * @project simple-service-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
@Service
public class AddressRepositoryImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressRepositoryImpl.class);

    @Autowired
    AddressRepository aRepo;
    
    @Autowired
    UserRepository uRepo;

    
    /** 
     * @param user
     * @param addressDTO
     * @return User
     */
    public User createAddress(User user, AddressDTO addressDTO){
 
        LOGGER.info("ACME_ADDRESS: createAddress IN: {}", user.toString() );
        
        Address _addr = new Address();
        _addr.setAddress_1(addressDTO.getAddress_1());
        _addr.setAddress_2(addressDTO.getAddress_2());
        _addr.setCity(addressDTO.getCity());
        _addr.setState(addressDTO.getState());
        _addr.setCountry(addressDTO.getCountry());
        _addr.setZipcode(addressDTO.getZipcode());
        _addr.setAddress_1(addressDTO.getAddress_1());
        _addr.setUser(user);
        Address aSaved = aRepo.save(_addr);
                
        LOGGER.info("ACME_ADDRESS: OUT:\n {}", aSaved.toString() );
        return uRepo.findByUserId(user.getUserId()).get();               
    }

    
    /** 
     * @param country
     * @return List<User>
     */
    public List<User> findByCountry(String country){
        List<User> l_user =  aRepo.findByCountry(country);
        uRepo.flush();
        return l_user;
           
    }
        
    
}
