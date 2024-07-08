package com.example.polusServiceRequest.loginRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.polusServiceRequest.loginDomain.Role;
import com.example.polusServiceRequest.loginDomain.User;

@Repository
public interface LoginRepository extends JpaRepository<User,Long>  {

	User findByUsernameAndPassword(String username, String password);
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    User findUserByUsernameWithRoles(@Param("username") String username);
}