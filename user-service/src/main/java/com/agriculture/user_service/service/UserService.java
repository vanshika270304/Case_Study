package com.agriculture.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agriculture.user_service.dto.UserRegistrationRequest;
import com.agriculture.user_service.repository.DealerRepository;
import com.agriculture.user_service.repository.FarmerRepository;
import com.agriculture.user_service.repository.UserRepository;
import com.agriculture.user_service.service.model.Dealer;
import com.agriculture.user_service.service.model.Farmer;
import com.agriculture.user_service.service.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private DealerRepository dealerRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
    
    public String registerUser(UserRegistrationRequest registrationRequest) {

        // Proceed with saving the user
        User user = new User();
        user.setName(registrationRequest.getName());
        user.setEmail(registrationRequest.getEmail());
        user.setRole(registrationRequest.getRole());
        user.setNumber(registrationRequest.getNumber());
        user.setPassword(registrationRequest.getPassword()); // Consider hashing the password
        userRepository.save(user);
        
        if ("farmer".equalsIgnoreCase(registrationRequest.getRole())) {
            Farmer farmer = new Farmer();
            farmer.setUser(user);
            farmer.setAddress("Farmer address here"); // You can add additional fields as needed
            farmerRepository.save(farmer);
        } else if ("dealer".equalsIgnoreCase(registrationRequest.getRole())) {
            Dealer dealer = new Dealer();
            dealer.setUser(user);
            dealer.setAddress("Dealer address here"); // You can add additional fields as needed
            dealerRepository.save(dealer);
        } else {
            throw new IllegalArgumentException("Invalid role. Only 'farmer' or 'dealer' are allowed.");
        }

        return "User registered successfully!";
    }
    
    public Map<String, Object> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                User loggedInUser = user.get();
                
                Map<String, Object> response = new HashMap<>();
                response.put("id", loggedInUser.getId());
                response.put("name", loggedInUser.getName());
                response.put("number", loggedInUser.getNumber());
                response.put("email", loggedInUser.getEmail());
                response.put("role", loggedInUser.getRole());

                // Fetch farmerId or dealerId and map it to roleId
                if ("farmer".equalsIgnoreCase(loggedInUser.getRole())) {
                    Farmer farmer = farmerRepository.findByUserId(loggedInUser.getId());
                    response.put("roleId", farmer.getId());
                } else if ("dealer".equalsIgnoreCase(loggedInUser.getRole())) {
                    Dealer dealer = dealerRepository.findByUserId(loggedInUser.getId());
                    response.put("roleId", dealer.getId());
                }

                return response;
            }
        }
        return null; // Invalid credentials
    }

    
    //view profile
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId); 
    }

    //update profile
    //role cannot be edited
    public User updateUserProfile(String userId, User userDetails) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setName(userDetails.getName());
            existingUser.setNumber(userDetails.getNumber());
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        }
        throw new IllegalArgumentException("User not found.");
    }
    
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll(); // Assuming you have a FarmerRepository to fetch data
    }
    
    public Map<String, String> getUserIdByFarmerId(String farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Farmer not found."));
        Map<String, String> response = new HashMap<>();
        response.put("userId", farmer.getUser().getId());
        return response;
    }
    
    public Map<String, String> getUserIdByDealerId(String dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new IllegalArgumentException("Dealer not found."));
        Map<String, String> response = new HashMap<>();
        response.put("userId", dealer.getUser().getId());
        return response;
    }



}

//&& passwordEncoder.matches(password, user.get().getPassword())