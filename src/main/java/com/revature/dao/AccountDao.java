package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDao implements IAccountDao{
	
	public int insert(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Account> findAll() {
		
		// Instantiate a linkedlist to store all of the objects that we retrieve
		List<Account> accList = new LinkedList<Account>();
		
		// Obtain a connection using try with resources
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			// Create a statement to execute against the DB
			Statement stmt = conn.createStatement();
			
			// Let's create our SQL query
			String sql = "SELECT * FROM accounts";
			
			// We'll return all of the data queried so we need a ResultSet obj to iterate over it
			ResultSet rs = stmt.executeQuery(sql);
			
			// Open a while loop to get all the info
			while (rs.next()) {
				
				// Gather the id of the accounts, balance, accOwnerId, and isActive
				int id = rs.getInt("id"); // Capture the value in the id column
				double balance = rs.getDouble("balance");
				int accOwnerId = rs.getInt("acc_owner");
				boolean isActive = rs.getBoolean("active");
				
				// Let's create an Account object to store all of this
				
				Account a = new Account(id, balance, accOwnerId, isActive);
				
				accList.add(a);
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("Unable to retrieve accounts due to SQL Exception");
			e.printStackTrace();
		}
		
		
		return accList;
	}

	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> findByOwner(int accOwnerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Account a) {
		// TODO Auto-generated method stub
		return false;
	}


}
