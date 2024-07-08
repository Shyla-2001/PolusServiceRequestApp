package com.example.polusServiceRequest.loginService;

import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.UserEntity;

public interface LoginService {

	UserEntity signIn(String username, String password);
	UserEntity signUp(SignUpDTO signUpDTO);
	
}