package com.example.polusServiceRequest.LoginDTO;

public class SignInDTO {

	private String userName;
	private String password;

	// Getters and setters for all fields

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SignInDTO() {
	}

	public SignInDTO(String username, String password) {
		this.userName = username;
		this.password = password;
	}

}
