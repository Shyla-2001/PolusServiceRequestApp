package com.example.polusServiceRequest.loginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polusServiceRequest.LoginDTO.SignInDTO;
import com.example.polusServiceRequest.loginDomain.User;
import com.example.polusServiceRequest.loginService.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

//	private final LoginService loginService;
	@Autowired
	private LoginService loginService;
	

//    @Autowired
//    public LoginController(LoginService loginService) {
//        this.loginService = loginService;
//    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody SignInDTO signin) {
        String username = signin.getUsername();
        String password = signin.getPassword();

        User user = loginService.signIn(username, password);

        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
