package com.fivepoints.spring.controllers;

import com.fivepoints.spring.entities.UserDetails;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-details")
@CrossOrigin(origins = "http://localhost:4200")
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/")
    public ResponseEntity<UserDetails> saveNewUserDetails(@RequestBody UserDetails userDetails)
    {
        UserDetails savedUserDetails =  this.userDetailsService.saveNewDetails(userDetails);
        return new ResponseEntity<>(savedUserDetails, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDetails>> getAllUsersDetails()
    {
        List<UserDetails> listUsersDetails = this.userDetailsService.getAllUserDetails();
        return new ResponseEntity<>(listUsersDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserDetailsByID(@PathVariable("id") long id)
    {
        UserDetails userDetails = this.userDetailsService.findUserDetailsByID(id);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUserDetailsByID(@PathVariable("id") long id, @RequestBody UserDetails userDetails)
    {
        String message = this.userDetailsService.updateUserDetailsByID(id, userDetails);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUserDetailsById(@PathVariable("id") long id)
    {
        String message = this.userDetailsService.deleteUserDetailsById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
