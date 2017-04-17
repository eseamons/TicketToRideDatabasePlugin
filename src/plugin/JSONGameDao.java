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

import server.plugin.AccountDTO;
import server.plugin.GameDTO;
import server.plugin.IGameDao;
import shared.Result;
import shared.model_classes.Account;
import shared.model_classes.Game;

public class JSONGameDao implements IGameDao {
	
	private String path = "JSONdata\\games.json";

	@Override
	public Result addGame(GameDTO arg0) {
		JSONParser parser = new JSONParser();

		try {
			File fileCheck = new File(path);
			if(fileCheck.length() > 0){
				Object obj = parser.parse(new FileReader(path));
				
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray jsonArray = (JSONArray) jsonObject.get("games");
	
				JSONObject game = new JSONObject();
				game.put("gameID", arg0.getGameID());
				Game g = arg0.getGame();
				game.put("game", JsonWriter.toJson(g));
				
				jsonArray.add(game);
				
				FileWriter file = new FileWriter(path);
				file.write(obj.toString());;
				file.flush();
				file.close();
			} else {
				JSONObject jsonObject = new JSONObject();
				JSONArray jsonArray = new JSONArray();
	
				JSONObject game = new JSONObject();
				game.put("gameID", arg0.getGameID());
				Game g = arg0.getGame();
				game.put("game", JsonWriter.toJson(g));
				
				jsonArray.add(game);
				jsonObject.put("games", jsonArray);
				
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
	public Result clearAllGames() {
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
	public Result deleteGame(GameDTO arg0) {
		JSONParser parser = new JSONParser();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return null;
			
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("games");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject gameObj = (JSONObject) i.next();
				int gameID = (int) (long) gameObj.get("gameID");
				if(arg0.getGameID() == gameID){
					i.remove();
				}
			}
			FileWriter file = new FileWriter(path);
			file.write(obj.toString());;
			file.flush();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public Set<GameDTO> getAll() {
		JSONParser parser = new JSONParser();
		Set<GameDTO> games = new HashSet<>();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return games;
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("games");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject gameObj = (JSONObject) i.next();
				int gameID = (int) (long) gameObj.get("gameID");
				String gameStr = (String) gameObj.get("game");
				Game game = (Game) JsonReader.jsonToJava(gameStr);
				GameDTO dto = new GameDTO();
				dto.setGameID(gameID);
				dto.setGame(game);
				
				games.add(dto);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return games;
	}

	@Override
	public GameDTO selectByGameID(int arg0) {
		JSONParser parser = new JSONParser();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return null;
			
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("games");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject gameObj = (JSONObject) i.next();
				int gameID = (int) (long) gameObj.get("gameID");
				String gameStr = (String) gameObj.get("game");
				Game game = (Game) JsonReader.jsonToJava(gameStr);
				if(arg0 == gameID){
					GameDTO dto = new GameDTO();
					dto.setGameID(gameID);
					dto.setGame(game);
					return dto;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public Result updateGame(GameDTO arg0) {
		JSONParser parser = new JSONParser();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return null;
			
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("games");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject gameObj = (JSONObject) i.next();
				int gameID = (int) (long) gameObj.get("gameID");
				String gameStr = (String) gameObj.get("game");
				Game game = (Game) JsonReader.jsonToJava(gameStr);
				if(arg0.getGameID() == gameID){
					gameObj.replace("game", game);
					
					FileWriter file = new FileWriter(path);
					file.write(obj.toString());;
					file.flush();
					file.close();
					
					return new Result(true, "");
				}
			}
		} catch (FileNotFoundException e) {
			return new Result(false, e.getMessage());
		} catch (IOException e) {
			return new Result(false, e.getMessage());
		} catch (ParseException e) {
			return new Result(false, e.getMessage());
		} 
		return new Result(false, "Not found");
	}
}
