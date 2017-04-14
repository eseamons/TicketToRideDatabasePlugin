package plugin;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql =

					"CREATE TABLE IF NOT EXISTS accounts(" +

					"userID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
					
					"auth TEXT NOT NULL UNIQUE," +

					"username TEXT NOT NULL UNIQUE," +

					"password TEXT NOT NULL);" +
					
					"INSERT INTO accounts (auth, username, password) VALUES ('"
					+ auth +
					"', '"
					+ username +
					"', '"
					+ password +
					"')"
					;
		      
		      stmt.executeUpdate(sql);
		      stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println("The server could not start a transaction");
			return new Result(false, e.getMessage());
		} catch (SQLException e) {
			System.out.println("The statement failed");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDTO selectByAuth(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDTO selectByUserName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
