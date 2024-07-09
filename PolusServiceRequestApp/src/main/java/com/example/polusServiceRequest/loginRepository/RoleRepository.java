package com.example.polusServiceRequest.loginRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.polusServiceRequest.loginDomain.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	/**
	 * Finds a role entity by role name.
	 *
	 * @param roleName the name of the role
	 * @return the role entity matching the provided role name
	 */
	RoleEntity findByRoleName(String roleName);
	
}
