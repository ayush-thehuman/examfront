package com.exam.service;

import java.util.Set;

import com.exam.GlobalException.UserFoundException;
import com.exam.GlobalException.UserNotFoundException;
import com.exam.entity.User;
import com.exam.entity.UserRole;

public interface UserService 
{
	//createUser
	public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException;

	//getUserByUserName
	public User getUserByUserName(String username) throws UserNotFoundException;

	//deleteUserByUserName
	public void deleteUserByUserName(Integer userId)throws UserNotFoundException;
	
	//update
	
}
