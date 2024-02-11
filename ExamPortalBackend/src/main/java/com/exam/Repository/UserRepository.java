package com.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	//Manually Created Method by Programmer
	User findByUsername(String username);

	User findUserByUsername(String username);

}
