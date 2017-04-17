package plugin;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import server.plugin.GameDTO;
import server.plugin.IGameDao;
import shared.Result;
import shared.model_classes.Game;

public class SQLGameDao implements IGameDao{

	@Override
	public Result addGame(GameDTO gameDTO) {
		Database database = new Database();
		Game game = gameDTO.getGame();
		int gameID = gameDTO.getGameID();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
						
			String gameString = JsonWriter.objectToJson(game);
						
			String sql =

					"CREATE TABLE IF NOT EXISTS games(" +

					"ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
					
					"gameID INTEGER NOT NULL," +

					"game TEXT NOT NULL);" +
					
					"INSERT INTO games (gameID, game) VALUES ("
					+ gameID +
					", '"
					+ gameString +					
					"')";
		      
		      stmt.executeUpdate(sql);
		      stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println("The server could not start a transaction");
			return new Result(false, e.getMessage());
		} catch (SQLException e) {
			System.out.println("The statement failed");
			System.out.println(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (IOException e) {
			System.out.println("JSONWriter Failed");
			System.out.println(e.getMessage());
		}
		return new Result(true, "");
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
		Database database = new Database();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			int gameID = arg0.getGameID();
			String sql = "DELETE FROM games WHERE gameID = " + gameID +";";
		      
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
	public Set<GameDTO> getAll() {
		Database database = new Database();
		Set<GameDTO> gameDTOSet = new HashSet<>();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM games;";
		      
		    ResultSet rs = stmt.executeQuery(sql);
		    		

		    
		    while(rs.next()) {
		    	String gameString = rs.getString("game");
		    	int gameID = rs.getInt("gameID");
		    	Game g =(Game) JsonReader.jsonToJava(gameString);
		    	
		    	GameDTO gameDTO = new GameDTO();
		    	
		    	gameDTO.setGame(g);
		    	gameDTO.setGameID(gameID);
		    	gameDTOSet.add(gameDTO);
		    	
		    }
		    
		    
		    stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println("The server could not start a transaction");
		} catch (SQLException e) {
			System.out.println("The statement failed");
		} catch (IOException e) {
			System.out.println("Json reader failed");
			e.printStackTrace();
		}
		
		return gameDTOSet;
	}

	@Override
	public GameDTO selectByGameID(int arg0) {
		Database database = new Database();
    	GameDTO gameDTO = new GameDTO();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM games WHERE gameID = " + arg0+ ";";
		      
		    ResultSet rs = stmt.executeQuery(sql);

		    	String gameString = rs.getString("game");
		    	int gameID = rs.getInt("gameID");
		    	Game g =(Game) JsonReader.jsonToJava(gameString);
		    			    	
		    	gameDTO.setGame(g);
		    	gameDTO.setGameID(gameID);
		    	
		    stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println("The server could not start a transaction");
		} catch (SQLException e) {
			System.out.println("The statement failed");
		} catch (IOException e) {
			System.out.println("Json reader failed");
			e.printStackTrace();
		}
		
		return gameDTO;
	}

	@Override
	public Result updateGame(GameDTO arg0) {
		Result r = null;
		
		 r = deleteGame(arg0);
		 if(r.isSuccess())
		 { 
			 r = addGame(arg0);
			 return r;
		 }
		 return r;
	}

}
