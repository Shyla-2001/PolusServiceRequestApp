package com.example.polusServiceRequest.loginService;

import com.example.polusServiceRequest.LoginDTO.SignInResponseDTO;
import com.example.polusServiceRequest.LoginDTO.RoleDTO;
import com.example.polusServiceRequest.LoginDTO.SignUpDTO;
import com.example.polusServiceRequest.loginDomain.UserEntity;
import com.example.polusServiceRequest.loginDomain.RoleEntity;
import com.example.polusServiceRequest.loginDomain.UserRoleEntity;
import com.example.polusServiceRequest.loginRepository.LoginRepository;
import com.example.polusServiceRequest.loginRepository.RoleRepository;
import com.example.polusServiceRequest.loginRepository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public SignInResponseDTO signIn(String username, String password) {
		UserEntity user = loginRepository.findUserByUsernameWithRoles(username);
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

			return responseDTO; // Return user details
		} else {
			throw new RuntimeException("Authentication failed"); // Throw an exception for authentication failure
		}
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
