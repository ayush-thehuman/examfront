package com.exam.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserRoleId;
	
	//user
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	//Role
	@ManyToOne
	private Role role;

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public UserRole(int userRoleId, User user, Role role) {
		super();
		UserRoleId = userRoleId;
		this.user = user;
		this.role = role;
	}



	public int getUserRoleId() {
		return UserRoleId;
	}



	public void setUserRoleId(int userRoleId) {
		UserRoleId = userRoleId;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserRole [UserRoleId=" + UserRoleId + ", user=" + user + ", role=" + role + "]";
	}

	
}
