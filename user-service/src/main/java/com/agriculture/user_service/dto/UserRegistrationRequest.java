package com.agriculture.user_service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
	
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Number is required")
    @Pattern(
        	regexp = "^[6-9]\\d{9}$",
        	message = "Phone number must be a valid 10-digit starting with 6, 7, 8, or 9."
        )
    private String number;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long")
    private String confirmPassword;
    
    @NotBlank(message = "Role is required")
    private String role; // "farmer" or "dealer"
}
