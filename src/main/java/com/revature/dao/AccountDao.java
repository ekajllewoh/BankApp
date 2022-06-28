package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDao implements IAccountDao {

	public int insert(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Account> findAll() {

		// Instantiate a linkedlist to store all of the objects that we retrieve
		List<Account> accList = new LinkedList<Account>();

		// Obtain a connection using try with resources

		try (Connection conn = ConnectionUtil.getConnection()) {

			// Create a statement to execute against the DB
			Statement stmt = conn.createStatement();

			// Let's create our SQL query
			String sql = "SELECT * FROM accounts";

			// We'll return all of the data queried so we need a ResultSet obj to iterate
			// over it
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
		Account a = new Account();
		
		// Try with Resources to connect and work with database
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM account WHERE username = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, username);
			
			ResultSet rs;
			
			if ((rs = stmt.executeQuery()) != null) {
				
				// Move the cursor forward
				rs.next();
				
				int id = rs.getInt("id");
				String returnedUsername = rs.getString("username");
				String password = rs.getString("pwd");
				Role role = Role.valueOf(rs.getString("user_role"));
				
				a.setId(id);
				a.setUsername(returnedUsername);
				a.setPassword(password);
				a.setRole(role);
				
			} 
		} catch (SQLException e) {
			System.out.println("SQL Exception Thrown - can't retrieve user from DB");
			e.printStackTrace();
		}
		
		
		return u;
	}

	public List<Account> findByOwner(int accOwnerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(Account a) {

		int accountId = a.getId();
		int accountName = a.getAccOwner();
		double accountBalance = a.getBalance();
		boolean activity = a.isActive();

		// Try with Resources to connect and work with database

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE * SET acc_owner = ?, active = ?, balance = ? WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, accountName);
			stmt.setBoolean(2, activity);
			stmt.setDouble(3, accountBalance);
			stmt.setInt(4, accountId);

			stmt.executeQuery(sql);

//		ResultSet rs;
//		
//		if ((rs = stmt.executeQuery()) != null) {
//			
//			// Move the cursor forward
//			rs.next();
//			
//			
//			
//			int id = rs.getInt("id");
//			String returnedUsername = rs.getString("username");
//			String password = rs.getString("pwd");
//			Role role = Role.valueOf(rs.getString("user_role"));
//			
//			u.setId(id);
//			u.setUsername(returnedUsername);
//			u.setPassword(password);
//			u.setRole(role);
			return true;
//		} 
		} catch (SQLException e) {
			System.out.println("SQL Exception Thrown - can't update user from DB");
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Account a) {

		int id = a.getId();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM accounts WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			return true;

		} catch (SQLException e) {
			System.out.println("SQL Exception Thrown - can't delete account from DB");
			e.printStackTrace();
		}

		return false;
	}

}
