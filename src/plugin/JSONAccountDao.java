package plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import server.plugin.AccountDTO;
import server.plugin.IAccountDao;
import shared.Result;
import shared.model_classes.Account;

public class JSONAccountDao implements IAccountDao {
	
	private String path = "JSONdata\\accounts.json";

	@Override
	public Result addAccount(AccountDTO arg0) {
		JSONParser parser = new JSONParser();

		try {
			File fileCheck = new File(path);
			if(fileCheck.length() > 0){
				Object obj = parser.parse(new FileReader(path));
				
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
	
				JSONObject account = new JSONObject();
				account.put("gameID", arg0.getGameID());
				Account a = arg0.getAccount();
				account.put("account", JsonWriter.toJson(a));
				
				jsonArray.add(account);
				
				FileWriter file = new FileWriter(path);
				file.write(obj.toString());;
				file.flush();
				file.close();
			} else {
				JSONObject jsonObject = new JSONObject();
				JSONArray jsonArray = new JSONArray();
	
				JSONObject account = new JSONObject();
				account.put("gameID", arg0.getGameID());
				Account a = arg0.getAccount();
				account.put("account", JsonWriter.toJson(a));
				
				jsonArray.add(account);
				jsonObject.put("accounts", jsonArray);
				
				FileWriter file = new FileWriter(path);
				file.write(jsonObject.toString());;
				file.flush();
				file.close();
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new Result(false, e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new Result(false, e.getMessage());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
	public Set<AccountDTO> getAll() {
		JSONParser parser = new JSONParser();
		Set<AccountDTO> accounts = new HashSet<>();
		
		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return accounts;
			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject accountObj = (JSONObject) i.next();
				int gameID = (int) (long) accountObj.get("gameID");
				String accountStr = (String) accountObj.get("account");
				Account account = (Account) JsonReader.jsonToJava(accountStr);
				AccountDTO dto = new AccountDTO();
				dto.setGameID(gameID);
				dto.setAccount(account);
				
				accounts.add(dto);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public AccountDTO selectByAuth(String arg0) {
		JSONParser parser = new JSONParser();

		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return null;

			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject accountObj = (JSONObject) i.next();
				int gameID = (int) (long) accountObj.get("gameID");
				String accountStr = (String) accountObj.get("account");
				Account account = (Account) JsonReader.jsonToJava(accountStr);
				if(arg0.equals(account.getAuthentication())){
					AccountDTO dto = new AccountDTO();
					dto.setGameID(gameID);
					dto.setAccount(account);
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
	public AccountDTO selectByUserName(String arg0) {
		JSONParser parser = new JSONParser();

		try {
			File fileCheck = new File(path);
			if(fileCheck.length() == 0) return null;

			Object obj = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
			Iterator i = jsonArray.iterator();
			
			while(i.hasNext()){
				JSONObject accountObj = (JSONObject) i.next();
				int gameID = (int) (long) accountObj.get("gameID");
				String accountStr = (String) accountObj.get("account");
				Account account = (Account) JsonReader.jsonToJava(accountStr);
				if(arg0.equals(account.getUsername())){
					AccountDTO dto = new AccountDTO();
					dto.setGameID(gameID);
					dto.setAccount(account);
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
}
