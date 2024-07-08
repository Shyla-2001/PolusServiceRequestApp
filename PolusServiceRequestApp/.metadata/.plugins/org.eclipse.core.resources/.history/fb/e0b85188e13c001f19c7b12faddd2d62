package com.example.polusServiceRequest.loginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.User;
import com.example.polusServiceRequest.loginRepository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginRepository loginRepository;


    @Override
    public User signIn(String username, String password) {
        // Implement your logic to find user by username and password in repository
        // This is a simplified example; in practice, you would perform proper authentication
        return loginRepository.findByUsernameAndPassword(username, password);
    }
    
    @Override
    public User signUp(SignUpDTO signUpDTO) {

        // Create a new user object
        User newUser = new User();
        newUser.setFirstname(signUpDTO.getFirstname());
        newUser.setLastname(signUpDTO.getLastname());
        newUser.setUsername(signUpDTO.getUsername());
        newUser.setPassword(signUpDTO.getPassword());
        newUser.setEmail(signUpDTO.getEmail());
        newUser.setCountry(signUpDTO.getCountry());
        newUser.setPhoneno(signUpDTO.getPhoneno());
        newUser.setAddress(signUpDTO.getAddress());

        // Save the new user to the users table
        return loginRepository.save(newUser);
    }
}
