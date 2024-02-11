package com.exam.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.GlobalException.UserFoundException;
import com.exam.GlobalException.UserNotFoundException;
import com.exam.Repository.RoleRepository;
import com.exam.Repository.UserRepository;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {

		User local = this.userRepo.findByUsername(user.getUsername());
		if (local != null) {
			System.out.println("User Already Exists!!");
			throw new UserFoundException();
		} else {
			// userCreate
			for (UserRole ur : userRoles) {
				roleRepo.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepo.save(user);
		}
		return local;
	}

	// getUserByUserName
	@Override
	public User getUserByUserName(String username) throws UserNotFoundException {
		User localUser = userRepo.findUserByUsername(username);
		if (localUser == null) {
			// System.out.println("User Not Exists!!");
			throw new UserNotFoundException("User With this username is not present " + username);
		} else {
			return localUser;
		}

	}

	@Override
	public void deleteUserByUserName(Integer userId) throws UserNotFoundException {

		userRepo.deleteById(userId);
	}

}
