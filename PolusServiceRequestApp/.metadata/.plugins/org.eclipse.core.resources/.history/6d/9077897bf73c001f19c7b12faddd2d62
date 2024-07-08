package com.example.polusServiceRequest.loginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.Role;
import com.example.polusServiceRequest.loginDomain.User;
import com.example.polusServiceRequest.loginDomain.UserRole;
import com.example.polusServiceRequest.loginRepository.LoginRepository;
import com.example.polusServiceRequest.loginRepository.RoleRepository;
import com.example.polusServiceRequest.loginRepository.UserRoleRepository;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User signIn(String username, String password) {
//        return loginRepository.findByUsernameAndPassword(username, password);
    	return loginRepository.findUserByUsernameWithRoles(username);
    }

    @Override
    public User signUp(SignUpDTO signUpDTO) {
        User newUser = new User();
        newUser.setFirstname(signUpDTO.getFirstname());
        newUser.setLastname(signUpDTO.getLastname());
        newUser.setUsername(signUpDTO.getUsername());
        newUser.setPassword(signUpDTO.getPassword());
        newUser.setEmail(signUpDTO.getEmail());
        newUser.setCountry(signUpDTO.getCountry());
        newUser.setPhoneno(signUpDTO.getPhoneno());
        newUser.setAddress(signUpDTO.getAddress());

        User savedUser = loginRepository.save(newUser);
       

        Role role = roleRepository.findByRolename("User");
        if (role == null) {
            throw new RuntimeException("Role not found");
        }

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(role);
        userRoleRepository.save(userRole);

        return savedUser;
    }
    
}