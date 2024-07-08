package com.example.polusServiceRequest.loginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.UserEntity;
import com.example.polusServiceRequest.loginDomain.RoleEntity;
import com.example.polusServiceRequest.loginDomain.UserRoleEntity;
import com.example.polusServiceRequest.loginRepository.LoginRepository;
import com.example.polusServiceRequest.loginRepository.RoleRepository;
import com.example.polusServiceRequest.loginRepository.UserRoleRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserEntity signIn(String username, String password) {
        return loginRepository.findUserByUsernameWithRoles(username);
    }

    @Override
    public UserEntity signUp(SignUpDTO signUpDTO) {
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(signUpDTO.getFirstname());
        newUser.setLastName(signUpDTO.getLastname());
        newUser.setUsername(signUpDTO.getUsername());
        newUser.setPassword(signUpDTO.getPassword());
        newUser.setEmail(signUpDTO.getEmail());
        newUser.setCountry(signUpDTO.getCountry());
        newUser.setPhoneNo(signUpDTO.getPhoneno());
        newUser.setAddress(signUpDTO.getAddress());

        UserEntity savedUser = loginRepository.save(newUser);

        RoleEntity role = roleRepository.findByRoleName("PRINCIPAL_INVESTIGATOR");
        if (role == null) {
            throw new RuntimeException("Role not found");
        }

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUser(savedUser);
        userRole.setRole(role);
        userRoleRepository.save(userRole);

        return savedUser;
    }
}
