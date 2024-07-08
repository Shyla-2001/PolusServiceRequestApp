package com.example.polusServiceRequest.loginController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polusServiceRequest.LoginDTO.SignInDTO;
import com.example.polusServiceRequest.LoginDTO.SignInResponseDTO;
import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.User;
import com.example.polusServiceRequest.loginService.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody SignInDTO signin) {
        String username = signin.getUsername();
        String password = signin.getPassword();

        User user = loginService.signIn(username, password);

//        if (user != null) {
//        	user.setPassword(null);
//            return ResponseEntity.ok().body(user);
        if (user != null) {
            // Prepare response DTO or map to a custom response object
            SignInResponseDTO responseDTO = new SignInResponseDTO();
            responseDTO.setUserid(user.getUserid());
            responseDTO.setFirstname(user.getFirstname());
            responseDTO.setLastname(user.getLastname());
            responseDTO.setUsername(user.getUsername());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setCountry(user.getCountry());
            responseDTO.setPhoneno(user.getPhoneno());
            responseDTO.setAddress(user.getAddress());
            List<String> roleNames = user.getRoles().stream()
                    .map(userRole -> userRole.getRole().getRolename())
                    .collect(Collectors.toList());
            responseDTO.setRoles(roleNames);

            return ResponseEntity.ok().body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
    
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            User newUser = loginService.signUp(signUpDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registration successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed: " + e.getMessage());
        }
    }
}