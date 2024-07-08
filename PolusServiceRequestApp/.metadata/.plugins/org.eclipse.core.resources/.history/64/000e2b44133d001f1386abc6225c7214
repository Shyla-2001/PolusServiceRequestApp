package com.example.polusServiceRequest.loginRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.polusServiceRequest.loginDomain.UserEntity;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.role WHERE u.username = :username")
    UserEntity findUserByUsernameWithRoles(@Param("username") String username);
}
