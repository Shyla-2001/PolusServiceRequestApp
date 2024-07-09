package com.example.polusServiceRequest.loginController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * @return ResponseEntity containing either user details or authentication
	 *         failure message
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> signIn(@RequestBody SignInDTO signin) {
		try {
			SignInResponseDTO responseDTO = loginService.signIn(signin.getUserName(), signin.getPassword());
			return ResponseEntity.ok().body(responseDTO);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during sign-in.");
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
		Map<String, String> response = new HashMap<>();
		try {
			boolean registered = loginService.signUp(signUpDTO);
			if (registered) {
				response.put("message", "User registration successful");
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} else {
				response.put("message", "User registration failed");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during sign-up.");
		}
	}
}
