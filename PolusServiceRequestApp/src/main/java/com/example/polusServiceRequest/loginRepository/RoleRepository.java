package com.example.polusServiceRequest.loginRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.polusServiceRequest.loginDomain.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	
Role findByRolename(String rolename);
	
}