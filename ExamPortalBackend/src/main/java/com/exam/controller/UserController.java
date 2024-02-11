package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.GlobalException.UserFoundException;
import com.exam.GlobalException.UserNotFoundException;
import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//creating user
	@PostMapping("/normal")
	public ResponseEntity<User> createNormalUser(@RequestBody User user) throws UserFoundException
	{
		user.setProfile("default.png");
		
		//Encoding password with BCryptPasswordEncoder
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		User createUser = userService.createUser(user, roles);
		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/admin")
	public ResponseEntity<User> createAdminUser(@RequestBody User user) throws UserFoundException
	{
		user.setProfile("text.png");
		
		//Encoding password with BCryptPasswordEncoder
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleName("ADMIN");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		User createUser = userService.createUser(user, roles);
		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "Welcome to backend api of Examportal";
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) throws UserNotFoundException
	{
		User userById = userService.getUserByUserName(username);
		return new ResponseEntity<User>(userById, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer userId)throws UserNotFoundException
	{
		userService.deleteUserByUserName(userId);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
}
