package com.example.polusServiceRequest.loginRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.polusServiceRequest.loginDomain.UserEntity;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {

	/**
	 * Finds a user entity by username and password.
	 *
	 * @param username the username of the user
	 * @param password the password of the user
	 * @return the user entity matching the provided username and password
	 */
	UserEntity findByUsernameAndPassword(String username, String password);

	/**
	 * Finds a user entity by username, including the user's roles.
	 *
	 * @param username the username of the user
	 * @return the user entity with roles matching the provided username
	 */
	@Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.role WHERE u.username = :username")
	UserEntity findUserByUsernameWithRoles(@Param("username") String username);
}
