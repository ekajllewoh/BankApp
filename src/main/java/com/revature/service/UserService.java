package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;
import com.revature.dao.IUserDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.Account;
import com.revature.models.User;

public class UserService {

	public IUserDao udao = new UserDao();
	public IAccountDao adao = new AccountDao();
	Logger logger = Logger.getLogger(UserService.class);

	public User register(User u) {

		System.out.println("Registering user....");

		// Let's make sure the registering user has an id of 0 before trying to register
		// This is just an additional layer of data validation

		if (u.getId() != 0) {
			throw new RegisterUserFailedException("User not valid to register because Id was not 0");
		}

		// If the id is 0, we can call the dao to create a new object

		int generatedId = udao.insert(u);

		// Let's do some checking before finishing

		if (generatedId != -1 && generatedId != u.getId()) {
			u.setId(generatedId);
		} else {
			throw new RegisterUserFailedException("User's Id was either -1 or did not change after insertion");
		}

		System.out.println("Successfully registered user with the Id of " + u.getId());
		
		logger.info("Created user account with Id " + u.getId());

		return u;
	}

	public User login(String username, String password) {

		// We now need to call upon our userDAO to get us some information about the
		// user with this specific username

		User returnedUser = udao.findByUsername(username);

		// Check to see if returned password matches the entered password

		if (returnedUser.getPassword().equals(password)) {

			System.out.println("Successfully Logged in!");

			return returnedUser;
		}

		// Otherwise the password is not equal
		return null;
	}
	
	

}
