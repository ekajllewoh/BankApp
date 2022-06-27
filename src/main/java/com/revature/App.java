package com.revature;

import java.util.Scanner;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.service.AccountService;
import com.revature.service.UserService;

public class App {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		AccountService as = new AccountService();
		as.viewAllAccounts();
		
		run();
	}
	
	public static void run() {
System.out.println("Welcome to our bank!");
		
		UserService us = new UserService();
		
		System.out.println("Press 1 if you are an existing user.");
		
		System.out.println("Press 2 if you'd like to register.");
		
		int input = scan.nextInt();
		
		if (input == 1) {
			
			System.out.println("Please enter your username");
			
			String username = scan.next();
			
			System.out.println("Please enter your password");
			
			String password = scan.next();
			
			User loggedInUser = us.login(username, password);
			
			System.out.println("Welcome to your account: " + loggedInUser.getUsername());
			
			
			
			
		}else if (input == 2) {
			
			System.out.println("Please enter a username");
			String username = scan.next();
			
			System.out.println("Please enter a password");
			String password = scan.next();
			
			User u = new User(username, password, Role.Customer, null);
			
			
			us.register(u);
		}
	}

}
