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
	
	public void viewAllAccounts(int id) {

//		System.out.println("Fetching accounts...");

		logger.info("Fetching Accounts...");

		// Lets call upon our DAO to get all of our accounts

		List<Account> accList = adao.findByOwner(id);

		for (Account a : accList) {
			System.out.println(a);
		}

	}
	
	public void addMoneyToAccount(int id, double money) {
		
		if(money >= 0) {
		Account a = adao.findById(id);
		
		logger.info("Depositing $" + money + " to account " + id);
		
		money = money + a.getBalance();
		
		a.setBalance(money);
		
		adao.update(a);
		}else {
			System.out.println("You can't deposit a negative amount.");
		}
		
	}
	
	public void withdrawMoneyFromAccount(int id, double money) {
		
		if(money >= 0) {
		Account a = adao.findById(id);
		
		logger.info("Withdrawing $" + money + " to account " + id);
		
		money = money - a.getBalance();
		
		a.setBalance(money);
		
		adao.update(a);
		}else {
			System.out.println("You can't withdraw a negative amount.");
		}
		
	}
	
	public void transferMoneyToAccount(int id, double money, int id2) {
		
		if(money >= 0) {
		Account a = adao.findById(id);
		
		logger.info("Sending $" + money + " from account " + id + " to account " + id2);
		
		money = money - a.getBalance();
		
		a.setBalance(money);
		
		adao.update(a);
		}else {
			System.out.println("You can't transfer a negative amount.");
		}
		
	}
	
	
	public int createAccount(int id) {
		
		Account a = new Account();
		
		a.setAccOwner(id);
		
		adao.insert(a);
		
		logger.info("Creating account for " + id);
		
		System.out.println("New account created.");
		
		return adao.findById(id).getId();
		
	}
	
	public boolean checkAccount(int id, int id2) {
		
		Account a = adao.findById(id2);
		
		return id == a.getAccOwner();
		
		
	}
	
	public boolean checkActiveAccount(int id) {
		
		Account a = adao.findById(id);
		
		return a.isActive();
		
	}
	
	public double checkBalance(int id) {
		
		Account a = adao.findById(id);
		
		return a.getBalance();
		
	}
	
	public void setAccActive(int id, boolean activity) {
		
		Account a = adao.findById(id);
		
		a.setActive(activity);
		
		adao.update(a);
		
		logger.info("Changed active status of account " + id);
		
	}
	
	public void deleteAcc(int id) {
		
		Account a = adao.findById(id);
		
		adao.delete(a);
		
		logger.info("Deleted account " + id);
		
	}
	

}
