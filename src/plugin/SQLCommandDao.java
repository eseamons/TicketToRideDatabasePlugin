package plugin;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import server.plugin.CommandDTO;
import server.plugin.ICommandDao;
import shared.Result;

public class SQLCommandDao implements ICommandDao{

	@Override
	public Result addCommand(CommandDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result clearData() {
		Database database = new Database();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS commands;";
		      
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
	public Result deleteGameCommands(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CommandDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CommandDTO> selectByGameID(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
