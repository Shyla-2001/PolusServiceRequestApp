package com.example.polusServiceRequest.loginDomain;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
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

	// Getters and setters for all fields

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

}
