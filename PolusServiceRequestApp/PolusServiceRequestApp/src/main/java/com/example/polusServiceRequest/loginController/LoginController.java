package com.example.polusServiceRequest.loginController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polusServiceRequest.LoginDTO.RoleDTO;
import com.example.polusServiceRequest.LoginDTO.SignInDTO;
import com.example.polusServiceRequest.LoginDTO.SignInResponseDTO;
import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.UserEntity;
import com.example.polusServiceRequest.loginService.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Endpoint for user sign-in.
     * 
     * @param signin Sign-in request data containing username and password
     * @return ResponseEntity containing either user details or authentication failure message
     */
    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody SignInDTO signin) {
        String username = signin.getUsername();
        String password = signin.getPassword();

        // Attempt to authenticate user using provided credentials
        UserEntity user = loginService.signIn(username, password);

        if (user != null) {
            // Create response DTO with user details excluding sensitive information
            SignInResponseDTO responseDTO = new SignInResponseDTO();
            responseDTO.setUserid(user.getUserId());
            responseDTO.setFirstname(user.getFirstName());
            responseDTO.setLastname(user.getLastName());
            responseDTO.setUsername(user.getUsername());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setCountry(user.getCountry());
            responseDTO.setPhoneno(user.getPhoneNo());
            responseDTO.setAddress(user.getAddress());

            // Map user roles to RoleDTOs for response
            List<RoleDTO> roleDTOs = user.getRoles().stream().map(userRole -> {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setRoleId(userRole.getRole().getRoleId());
                roleDTO.setRoleName(userRole.getRole().getRoleName());
                roleDTO.setRoleDescription(userRole.getRole().getRoleDescription());
                return roleDTO;
            }).collect(Collectors.toList());

            responseDTO.setRoles(roleDTOs);

            return ResponseEntity.ok().body(responseDTO); // Return user details
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed"); // Return authentication failure message
        }
    }
    
    /**
     * Endpoint for user sign-up.
     * 
     * @param signUpDTO Sign-up request data containing user details
     * @return ResponseEntity indicating success or failure of user registration
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            // Attempt to register a new user
            UserEntity newUser = loginService.signUp(signUpDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registration successful");
        } catch (Exception e) {
            // Return error message if registration fails
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed: " + e.getMessage());
        }
    }
    
    /**
     * Simple endpoint for testing purposes.
     * 
     * @return String "Hello Abin" as a greeting message
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Abin";
    }
}
