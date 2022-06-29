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

			AccountService as = new AccountService();
			
			System.out.println(loggedInUser.getRole() == Role.Customer);
			
			if(loggedInUser.getRole() == Role.Customer) {

			System.out.println("If you would like to open an account press 1, if you would like to view your accounts press 2. Press 0 to close.");

			int existingAccountInput = -1;

			while (existingAccountInput != 0) {

				existingAccountInput = scan.nextInt();

				if (existingAccountInput == 1) {
					int newAccId = as.createAccount(loggedInUser.getId());
					System.out.println("Created account with id " + newAccId);
					System.out.println("Please wait for administrator approval to activate your account.");
				} else if (existingAccountInput == 2) {
					System.out.println("Here are your accounts: ");
					as.viewAllAccounts(loggedInUser.getId());
					System.out.println("Select an account by its ID for a withdrawal, deposit or transfer.");
					int accountOption = scan.nextInt(); 
					
					if(as.checkAccount(loggedInUser.getId(), accountOption)) {
						
						if(as.checkActiveAccount(accountOption)) {
							
							System.out.println("Select 1 for withdrawl, 2 for deposit, 3 for transfer.");
							int moneyMover = scan.nextInt();
							
							if(moneyMover == 1) {
								System.out.println("How much do you wish to withdraw?");
								double moneyAmount = scan.nextDouble();
								
								if(moneyAmount > as.checkBalance(accountOption)) {
									as.addMoneyToAccount(accountOption, moneyMover);
								}else {
									System.out.println("Please do not overdraft.");
								}
								
								
							}else if(moneyMover == 2) {
								System.out.println("How much do you wish to deposit?");
								double moneyAmount = scan.nextDouble();
								as.withdrawMoneyFromAccount(accountOption, moneyAmount);
								
							}else if(moneyMover == 3) {
								System.out.println("How much do you wish to transfer?");
								double moneyAmount = scan.nextDouble();
								System.out.println("What account number do you wish to transfer to?");
								int recievingAcc = scan.nextInt();
								if(moneyAmount > as.checkBalance(accountOption)) {
									as.transferMoneyToAccount(accountOption, moneyAmount, recievingAcc);
								}else {
									System.out.println("Please do not tranfer more than the account has.");
								}
								
								
								
							}else {
								System.out.println("Please select a valid option.");
							}
							
							
						}else if(!as.checkActiveAccount(accountOption)){
							System.out.println("This account has not been activated yet.");
						}else {
							System.out.println("How did you even get here?");
						}
						
					}else if(!as.checkAccount(loggedInUser.getId(), accountOption)) {
						
						System.out.println("Please select an account registered to this user.");
						
					}else {
						System.out.println("How did you even get here?");
					}
					
					
				} else {
					System.out.println("I'm sorry that wasnt a valid option. Please try again.");
				}
				System.out.println("If you would like to open an account press 1, if you would like to view your accounts press 2. Press 0 to close.");
			}
			}else if(loggedInUser.getRole() == Role.Employee) {
				
				System.out.println("Press 1 to view all accounts, or press 2 to approve/deny a specific account. Press 0 to end.");
				int accViewer = scan.nextInt();
				while(accViewer != 0) {
				
				if(accViewer == 1) {
					as.viewAllAccounts();
				}else if(accViewer == 2) {
					System.out.println("Please enter the account number of the account you would like to view.");
					
					int specId = scan.nextInt();
					as.viewAllAccounts(specId);
					System.out.println("Please enter 1 to approve the account or 2 to deny the account.");
					
					int activeChecker = scan.nextInt();
					
					if(activeChecker == 1) {
						as.setAccActive(specId, true);
					}else if (activeChecker == 2) {
						as.setAccActive(specId, false);
					}else {
						System.out.println("Not a valid action for the account");
					}
					
				}else {
					System.out.println("Please enter one of the listed options.");
				}
				}
				
			}else if(loggedInUser.getRole() == Role.Admin) {
				int nextCheck = -1;
				while(nextCheck != 0)
				System.out.println("Please enter 1 to view all accounts, 2 to access a specific account. Press 0 to exit.");
				nextCheck = scan.nextInt();
				if(nextCheck == 1) {
					
					as.viewAllAccounts();
					
				}else if(nextCheck == 2) {
					
					System.out.println("Select the id of the account you wish to change.");
					int adminId = scan.nextInt();
					System.out.println("Press 1 to approve/deny, 2 to withdraw/deposit/transfer, 3 to cancel account.");
					int adminChoice = scan.nextInt();
					if(adminChoice == 1) {
						
						System.out.println("Please enter 1 to approve the account or 2 to deny the account.");
						
						int activeChecker = scan.nextInt();
						
						if(activeChecker == 1) {
							as.setAccActive(adminId, true);
						}else if (activeChecker == 2) {
							as.setAccActive(adminId, false);
						}else {
							System.out.println("Not a valid action for the account");
						}
						
					}else if(adminChoice == 2) {
						
						System.out.println("Please press 1 to withdraw, 2 to deposit or 3 to transfer.");
						int nextChoice = scan.nextInt();
						if(nextChoice == 1) {
							System.out.println("How much would you like to withdraw?");
							double adminWithdraw = scan.nextDouble();
							
							as.withdrawMoneyFromAccount(adminId, adminWithdraw);
							
						}else if(nextChoice == 2) {
							
							System.out.println("How much would you like to deposit?");
							double adminDeposit = scan.nextDouble();
							
							as.addMoneyToAccount(adminId, adminDeposit);
							
						}else if(nextChoice == 3) {
							
							System.out.println("How much would you like to transfer?");
							double adminTransfer = scan.nextDouble();
							System.out.println("Please list the account ID for the money to be transferred to.");
							int adminNextAcc = scan.nextInt();
							
							as.transferMoneyToAccount(adminId, adminNextAcc, adminNextAcc);
							
						}else {
							System.out.println("That was not a valid option for the account.");
						}
						
						
					}else if(adminChoice == 3) {
						
						System.out.println("Confirm to cancel account with ID " + adminId + ". Press 1 to confirm.");
						int adminConfirm = scan.nextInt();
						
						if(adminConfirm == 1) {
							
							as.deleteAcc(adminId);
							
							System.out.println("Account with " + adminId + " has been canceled.");
							
						}else {
							System.out.println("Request not confirmed account with ID " + adminId + " not canceled.");
						}
						
					}else {
						System.out.println("Please choose a valid option.");
					}
					
				}else {
					System.out.println("Please enter a listed option.");
				}
				
				
			}else {
				System.out.println("Unrecognized user role.");
			}

		} else if (input == 2) {

			System.out.println("Please enter a username");
			String username = scan.next();

			System.out.println("Please enter a password");
			String password = scan.next();

			User u = new User(username, password, Role.Customer, null);

			us.register(u);
		} else {
			System.out.println("I'm sorry that wasnt a valid option. Please hang up and try again.");
		}

	}

}
