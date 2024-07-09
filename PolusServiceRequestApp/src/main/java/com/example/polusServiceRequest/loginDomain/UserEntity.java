package com.example.polusServiceRequest.loginDomain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long userId;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "country")
	private String country;

	@Column(name = "phoneno")
	private String phoneNo;

	@Column(name = "address")
	private String address;

	@LastModifiedDate
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<UserRoleEntity> roles;

	// Constructors

	public UserEntity() {
	}

	public UserEntity(Long userId, String firstName, String lastName, String username, String password, String email,
			String country, String phoneNo, String address, Date createdDate, List<UserRoleEntity> roles) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.country = country;
		this.phoneNo = phoneNo;
		this.address = address;
		this.createdDate = createdDate;
		this.roles = roles;
	}

}
