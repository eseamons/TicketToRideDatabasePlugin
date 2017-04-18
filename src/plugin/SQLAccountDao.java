package plugin;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import server.plugin.AccountDTO;
import server.plugin.IAccountDao;
import shared.Result;
import shared.model_classes.Account;

public class SQLAccountDao implements IAccountDao{

	@Override
	public Result addAccount(AccountDTO accountDTO) {
		Database database = new Database();
		Account account = accountDTO.getAccount();
		String auth = account.getAuthentication();
		String username = account.getUsername();
		String password = account.getPassword();
		int gameID = accountDTO.getGameID();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql =

					"CREATE TABLE IF NOT EXISTS accounts(" +

					"userID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
					
					"auth TEXT NOT NULL UNIQUE," +

					"username TEXT NOT NULL UNIQUE," +

					"password TEXT NOT NULL," +
					
					"gameID Integer NOT NULL" +
					
					");" +
					
					"INSERT INTO accounts (auth, username, password, gameID) VALUES ('"
					+ auth +
					"', '"
					+ username +
					"', '"
					+ password +
					"', " +
					gameID +
					
					")"
					;
		      
		      stmt.executeUpdate(sql);
		      stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("The server could not start a transaction");
			return new Result(false, e.getMessage());
		} catch (SQLException e) {
			System.out.println("The statement failed");
			System.out.println(e.getMessage());
			return new Result(false, e.getMessage());
		}
		return new Result(true, "");
	}

	@Override
	public Result clearData() {
		Database database = new Database();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS accounts;";
		      
		    stmt.executeUpdate(sql);
		    stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("The server could not start a transaction");
			return new Result(false, e.getMessage());
		} catch (SQLException e) {
			System.out.println("The statement failed");
			return new Result(false, e.getMessage());
		}
		
		return new Result(true, "");
	}

	@Override
	public Set<AccountDTO> getAll() {
		Database database = new Database();
		Set<AccountDTO> accountDTOSet = new HashSet<>();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM accounts";
		      
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {
		    	String auth = rs.getString("auth");
		    	String username = rs.getString("username");
		    	String password = rs.getString("password");
		    	int gameID = rs.getInt("gameID");
		    	
		    	AccountDTO accountDTO = new AccountDTO();
		    	
		    	Account account = new Account();
		    	account.setAuthentication(auth);
		    	account.setUsername(username);
		    	account.setPassword(password);
		    	
		    	accountDTO.setAccount(account);
		    	accountDTO.setGameID(gameID);
		    	accountDTOSet.add(accountDTO);
		    }
		    
		    
		    stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("The server could not start a transaction");
		} catch (SQLException e) {
			System.out.println("The statement failed");
		}
		
		return accountDTOSet;
	}

	@Override
	public AccountDTO selectByAuth(String auth) {
		System.out.println("Selecting Account by auth code");
		Database database = new Database();
    	AccountDTO accountDTO = new AccountDTO();
    	Account account = new Account();
    	
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM accounts WHERE auth = '" + auth + "';";
		      
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {
		    	auth = rs.getString("auth");
		    	String username = rs.getString("username");
		    	String password = rs.getString("password");
		    	int gameID = rs.getInt("gameID");
		    	
		    	
		    	account = new Account();
		    	account.setAuthentication(auth);
		    	account.setUsername(username);
		    	account.setPassword(password);
		    	
		    	accountDTO.setAccount(account);
		    	accountDTO.setGameID(gameID);
		    }
		    
		    
		    stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("The server could not start a transaction");
		} catch (SQLException e) {
			System.out.println("The statement failed");
		}
		
		return accountDTO;
	}

	@Override
	public AccountDTO selectByUserName(String username) {
		Database database = new Database();
    	AccountDTO accountDTO = new AccountDTO();
    	Account account = new Account();
    	
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM accounts WHERE username = '" + username + "';";
		      
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {
		    	String auth = rs.getString("auth");
		    	username = rs.getString("username");
		    	String password = rs.getString("password");
		    	int gameID = rs.getInt("gameID");
		    	
		    	
		    	account = new Account();
		    	account.setAuthentication(auth);
		    	account.setUsername(username);
		    	account.setPassword(password);
		    	
		    	accountDTO.setAccount(account);
		    	accountDTO.setGameID(gameID);
		    }
		    
		    
		    stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("The server could not start a transaction");
		} catch (SQLException e) {
			System.out.println("The statement failed");
		}
		
		return accountDTO;
	}
	
	

}
