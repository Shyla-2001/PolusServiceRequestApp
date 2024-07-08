package com.example.polusServiceRequest.loginRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.polusServiceRequest.loginDomain.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{

}