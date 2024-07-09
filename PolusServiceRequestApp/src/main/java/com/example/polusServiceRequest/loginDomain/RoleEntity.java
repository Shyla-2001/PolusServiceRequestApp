package com.example.polusServiceRequest.loginDomain;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private Long roleId;

	@Column(name = "rolename", nullable = false, unique = true)
	private String roleName;

	@Column(name = "roledescription", nullable = false)
	private String roleDescription;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserRoleEntity> userRoles = new HashSet<>();

	// Constructors

	public RoleEntity() {
	}

	public RoleEntity(Long roleId, String roleName, String roleDescription, Set<UserRoleEntity> userRoles) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.userRoles = userRoles;
	}

}
