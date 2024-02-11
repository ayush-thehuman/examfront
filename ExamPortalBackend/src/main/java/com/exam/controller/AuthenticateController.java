package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtils;
import com.exam.entity.JwtRequest;
import com.exam.entity.JwtResponse;
import com.exam.entity.User;
import com.exam.serviceImpl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController 
{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	//by this method we can authenticate the user that he/she is providing the correct username and password or not.
	private void authenticate(Object username, Object password) throws Exception
	{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try
		{
			authenticationManager.authenticate(authenticationToken);
		}
		catch(DisabledException e)
		{
			throw new Exception("USER DISABLED "+e.getMessage());
		}
		catch (BadCredentialsException e) 
		{
			
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
	}
	
	//generate token
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken
	(@RequestBody JwtRequest request) throws Exception
	{
		try
		{
			authenticate(request.getUsername(), request.getPassword());
		}
		catch (UsernameNotFoundException e) 
		{
			e.printStackTrace();
			throw new Exception("User not found !");
		}
		
		/////////authenticate
		
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(request.getUsername());
		String generatedToken = this.jwtUtil.generateToken(userDetails);
		JwtResponse response = new JwtResponse();
		response.setToken(generatedToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//returns the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
	{
		return ((User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
	}
	
}
