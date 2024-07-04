package com.example.polusServiceRequest.loginRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polusServiceRequest.loginDomain.User;

@Repository
public interface LoginRepository extends JpaRepository<User,Long>  {

	User findByUsernameAndPassword(String username, String password);
}
