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

import server.plugin.CommandDTO;
import server.plugin.ICommandDao;
import shared.Result;
import shared.command_classes.Command;

public class SQLCommandDao implements ICommandDao {

	@Override
	public Result addCommand(CommandDTO commandDTO) {
		Database database = new Database();
		Command command = commandDTO.getCommand();
		int gameID = commandDTO.getGameID();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			//Convert the command into a string...
			String commandString = JsonWriter.objectToJson(command).toString();
						
			String sql =

					"CREATE TABLE IF NOT EXISTS commands(" +

					"ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
					
					"gameID INTEGER NOT NULL," +

					"command TEXT NOT NULL);" +
					
					"INSERT INTO commands(gameID, command) VALUES ("
					+ gameID +
					", '"
					+ commandString +					
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
		Database database = new Database();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "DELETE FROM commands WHERE gameID = " + arg0+ ";";
		      
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
	public Set<CommandDTO> getAll() {
		Database database = new Database();
		Set<CommandDTO> commandDTOSet = new HashSet<>();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM commands;";
		      
		    ResultSet rs = stmt.executeQuery(sql);
		    		

		    
		    while(rs.next()) {
		    	String commandString = rs.getString("command");
		    	int gameID = rs.getInt("gameID");
		    	Command c =(Command) JsonReader.jsonToJava(commandString);
		    	
		    	CommandDTO commandDTO = new CommandDTO();
		    	
		    	commandDTO.setCommand(c);
		    	commandDTO.setGameID(gameID);
		    	commandDTOSet.add(commandDTO);
		    	
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
		
		return commandDTOSet;
	}

	@Override
	public Set<CommandDTO> selectByGameID(int arg0) {
		Database database = new Database();
		Set<CommandDTO> commandDTOSet = new HashSet<>();
		
		try {
			database.startTransaction();
			
			Connection conn = database.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM commands WHERE gameID = "+ arg0 +";";
		      
		    ResultSet rs = stmt.executeQuery(sql);
		    		
		    
		    while(rs.next()) {
		    	String commandString = rs.getString("command");
		    	int gameID = rs.getInt("gameID");
		    	Command c =(Command) JsonReader.jsonToJava(commandString);
		    	
		    	CommandDTO commandDTO = new CommandDTO();
		    	
		    	commandDTO.setCommand(c);
		    	commandDTO.setGameID(gameID);
		    	commandDTOSet.add(commandDTO);
		    	
		    }
		    
		    
		    stmt.close();
			
			
			database.endTransaction(true);
			
			
		} catch (ServerException e) {
			System.out.println("The server could not start a transaction");
		} catch (SQLException e) {
			System.out.println("The statement failed");
		} catch (IOException e) {
			System.out.println("JSON reader failed");
			e.printStackTrace();
		}
		
		return commandDTOSet;
	}

}
