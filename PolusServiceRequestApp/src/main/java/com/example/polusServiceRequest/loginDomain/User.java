package com.example.polusServiceRequest.loginDomain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    @Column(name = "userid")
	    private Long userid;
	    @Column(name = "firstname", nullable = false)
	    private String firstname;
	    @Column(name = "lastname")
	    private String lastname;
	    @Column(name = "username", nullable = false)
	    private String username;
	    @Column(name = "password", nullable = false)
	    private String password;
	    @Column(name = "email", nullable = false, unique = true)
	    private String email;
	    @Column(name = "country")
	    private String country;
	    @Column(name = "phoneno")
	    private String phoneno;
	    @Column(name = "address")
	    private String address;
	    
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<UserRole> roles;
	    
	 // Default constructor (required by JPA)
	    public User() {
	    }

	    // Constructor for login/sign in process
	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }
	    
	    
		public User(Long userid, String firstname, String lastname, String username, String password, String email,
				String role, String country, String phoneno, String address) {
			super();
			this.userid = userid;
			this.firstname = firstname;
			this.lastname = lastname;
			this.username = username;
			this.password = password;
			this.email = email;
			this.country = country;
			this.phoneno = phoneno;
			this.address = address;
		}
		public Long getUserid() {
			return userid;
		}
		public void setUserid(Long userid) {
			this.userid = userid;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getPhoneno() {
			return phoneno;
		}
		public void setPhoneno(String phoneno) {
			this.phoneno = phoneno;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	    
	    
}
