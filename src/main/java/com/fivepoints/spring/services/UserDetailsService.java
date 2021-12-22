package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.UserDetails;
import com.fivepoints.spring.exceptions.ResourceNotFoundException;
import com.fivepoints.spring.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetails saveNewDetails(UserDetails userDetails)
    {
        return this.userDetailsRepository.save(userDetails);
    }

    public List<UserDetails> getAllUserDetails()
    {
        return this.userDetailsRepository.findAll();
    }

    public UserDetails findUserDetailsByID(long id)
    {
        Optional<UserDetails> userDetailsData = this.userDetailsRepository.findById(id);
        // Return statement if user exist or null
        return userDetailsData.orElseThrow(() -> new ResourceNotFoundException("User details not found"));

    }

    public String updateUserDetailsByID(long id, UserDetails userDetails)
    {
        Optional<UserDetails> userDetailsData = this.userDetailsRepository.findById(id);
        if (userDetailsData.isPresent()) {
            UserDetails existingUserDetails = userDetailsData.orElse(null);
            existingUserDetails.setAge(userDetails.getAge());
            existingUserDetails.setPhoneNumber(userDetails.getPhoneNumber());
            existingUserDetails.setBirthDate(userDetails.getBirthDate());
            existingUserDetails.setGithubProfileLink(userDetails.getGithubProfileLink());
            existingUserDetails.setLinkedinProfileLink(userDetails.getLinkedinProfileLink());
            // save existingUser in the database
            this.userDetailsRepository.save(existingUserDetails);
            // return statement
            return "User details updated successfully!";
        } else {
            throw new ResourceNotFoundException("User details not found");
        }
    }

    public String deleteUserDetailsById(long id)
    {
        Optional<UserDetails> userDetailsData = this.userDetailsRepository.findById(id);
        if (userDetailsData.isPresent()) {
            this.userDetailsRepository.deleteById(id);
            return "User details deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User details not found");
        }
    }
}

