package com.agriculture.user_service.controller;

import jakarta.validation.Valid;

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
            return ResponseEntity.ok(userService.registerUser(registrationRequest));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password must be provided");
        }
        
        Optional<User> user = userService.loginUser(email, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
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

    
}
