package com.agriculture.user_service.controller;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agriculture.user_service.dto.UserRegistrationRequest;
import com.agriculture.user_service.service.UserService;
import com.agriculture.user_service.service.model.Farmer;
import com.agriculture.user_service.service.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Testing api
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
        try {
            // Pass both password and confirmPassword to the service for validation
        	Map<String, String> response = new HashMap<>();
            response.put("message", userService.registerUser(registrationRequest));
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Map<String, Object> response = userService.loginUser(email, password);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/profile/{id}")
    public ResponseEntity<?> updateUserProfile(
            @PathVariable String id, 
            @Valid @RequestBody User userDetails
    ) {
        try {
            return ResponseEntity.ok(userService.updateUserProfile(id, userDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/farmers")
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        List<Farmer> farmers = userService.getAllFarmers();
        if (farmers.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if there are no farmers
        }
        return ResponseEntity.ok(farmers); // Return 200 OK with farmers data
    }
    
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<Map<String, String>> getUserIdByFarmerId(@PathVariable String farmerId) {
        Map<String, String> response = userService.getUserIdByFarmerId(farmerId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<Map<String, String>> getUserIdByDealerId(@PathVariable String dealerId) {
        Map<String, String> response = userService.getUserIdByDealerId(dealerId);
        return ResponseEntity.ok(response);
    }

}
