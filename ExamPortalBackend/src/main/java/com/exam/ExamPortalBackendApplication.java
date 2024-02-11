package com.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.service.UserService;

@SpringBootApplication
public class ExamPortalBackendApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamPortalBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("starting code");
		
//		  User u = new User(); u.setFirstName("Ayush"); u.setLastName("Sahu");
//		  u.setUsername("sahu786"); u.setPassword(this.bCryptPasswordEncoder.encode("sahu")); u.setEmail("sahu786@gmail.com");
//		  u.setProfile("default.png");
//		  
//		  Role r1 = new Role();  
//		  r1.setRoleName("ADMIN");
//		  
//		  Set<UserRole> user = new HashSet<>(); UserRole userRole = new UserRole();
//		  userRole.setUser(u); userRole.setRole(r1); user.add(userRole);
//		  
//		  User user1 = this.userService.createUser(u, user);
//		  System.out.println(user1.getUsername());
		 
	}

}
