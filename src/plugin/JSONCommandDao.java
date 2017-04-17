package plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import server.plugin.CommandDTO;
import server.plugin.ICommandDao;
import shared.Result;
import shared.command_classes.Command;

public class JSONCommandDao implements ICommandDao {
	
	private String path = "JSONData\\commands.json";

	@Override
	public Result addCommand(CommandDTO arg0) {
		JSONParser parser = new JSONParser();

		try {
			File fileCheck = new File(path);
			if(fileCheck.length() > 0){
				Object obj = parser.parse(new FileReader(path));
				
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray jsonArray = (JSONArray) jsonObject.get("commands");
	
				JSONObject command = new JSONObject();
				command.put("gameID", arg0.getGameID());
				Command c = arg0.getCommand();
				command.put("command", JsonWriter.toJson(c));
				
				jsonArray.add(command);
				
				FileWriter file = new FileWriter(path);
				file.write(obj.toString());;
				file.flush();
				file.close();
			} else {
				JSONObject jsonObject = new JSONObject();
				JSONArray jsonArray = new JSONArray();
	
				JSONObject command = new JSONObject();
				command.put("gameID", arg0.getGameID());
				Command c = arg0.getCommand();
				command.put("command", JsonWriter.toJson(c));
				
				jsonArray.add(command);
				jsonObject.put("commands", jsonArray);
				
				FileWriter file = new FileWriter(path);
				file.write(jsonObject.toString());;
				file.flush();
				file.close();
				
			}
			
		} catch (FileNotFoundException e) {
			return new Result(false, e.getMessage());
		} catch (IOException e) {
			return new Result(false, e.getMessage());
		} catch (ParseException e) {
			return new Result(false, e.getMessage());
		}
		return new Result(true, "");
	}

	@Override
	public Result clearData() {
		try {
			FileWriter file = new FileWriter(path);
			file.write("");
			file.flush();
			file.close();
		} catch (IOException e) {
			return new Result(false, e.getMessage());
		}
		return new Result(true, "");
	}

	@Override
	public Result deleteGameCommands(int arg0) {
		JSONParser parser = new JSONParser();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return null;
			
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("commands");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject commandObj = (JSONObject) i.next();
				int gameID = (int) (long) commandObj.get("gameID");
				if(arg0 == gameID){
					i.remove();
				}
			}
			FileWriter file = new FileWriter(path);
			file.write(obj.toString());
			file.flush();
			file.close();
		} catch (FileNotFoundException e) {
			return new Result(false, e.getMessage());
		} catch (IOException e) {
			return new Result(false, e.getMessage());
		} catch (ParseException e) {
			return new Result(false, e.getMessage());
		}
		return new Result(true, "");
	}

	@Override
	public Set<CommandDTO> getAll() {
		JSONParser parser = new JSONParser();
		Set<CommandDTO> commands = new HashSet<>();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return commands;
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("commands");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject commandObj = (JSONObject) i.next();
				int gameID = (int) (long) commandObj.get("gameID");
				String commandStr = (String) commandObj.get("command");
				Command command = (Command) JsonReader.jsonToJava(commandStr);
				CommandDTO dto = new CommandDTO();
				dto.setGameID(gameID);
				dto.setCommand(command);
				
				commands.add(dto);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return commands;
	}

	@Override
	public Set<CommandDTO> selectByGameID(int arg0) {
		JSONParser parser = new JSONParser();
		Set<CommandDTO> commands = new HashSet<>();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return commands;
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("commands");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject commandObj = (JSONObject) i.next();
				int gameID = (int) (long) commandObj.get("gameID");
				String commandStr = (String) commandObj.get("command");
				Command command = (Command) JsonReader.jsonToJava(commandStr);
				if(arg0 == gameID){
					CommandDTO dto = new CommandDTO();
					dto.setGameID(gameID);
					dto.setCommand(command);
					
					commands.add(dto);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return commands;
	}

}
