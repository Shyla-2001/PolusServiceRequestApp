package com.example.polusServiceRequest.loginService;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.polusServiceRequest.loginDomain.User;
import com.example.polusServiceRequest.loginRepository.LoginRepository;

public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginRepository loginRepository;


    @Override
    public User signIn(String username, String password) {
        // Implement your logic to find user by username and password in repository
        // This is a simplified example; in practice, you would perform proper authentication
        return loginRepository.findByUsernameAndPassword(username, password);
    }
}
