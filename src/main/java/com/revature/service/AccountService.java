package com.revature.service;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;
import com.revature.models.Account;

public class AccountService {

	private IAccountDao adao = new AccountDao();

	// Lets make a logger here
	Logger logger = Logger.getLogger(AccountService.class);

	private static Scanner scan = new Scanner(System.in);

	public void viewAllAccounts() {

//		System.out.println("Fetching accounts...");

		logger.info("Fetching Accounts...");

		// Lets call upon our DAO to get all of our accounts

		List<Account> accList = adao.findAll();

		for (Account a : accList) {
			System.out.println(a);
		}

	}

}
