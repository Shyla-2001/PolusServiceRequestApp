package com.example.polusServiceRequest.loginService;

import com.example.polusServiceRequest.LoginDTO.SignInResponseDTO;
import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.UserEntity;

public interface LoginService {

	SignInResponseDTO signIn(String username, String password);

	boolean signUp(SignUpDTO signUpDTO);

}