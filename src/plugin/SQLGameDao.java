package plugin;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import server.plugin.GameDTO;
import server.plugin.IGameDao;
import shared.Result;

public class SQLGameDao implements IGameDao{

	@Override
	public Result addGame(GameDTO gameDTO) {
		return null;
	}

	@Override
	public Result clearAllGames() {
		Database database = new Database();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS games;";
		      
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
	public Result deleteGame(GameDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<GameDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameDTO selectByGameID(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result updateGame(GameDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
