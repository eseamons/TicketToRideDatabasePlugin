package main;

import java.util.Set;

import plugin.JSONFactory;
import server.plugin.AccountDTO;
import server.plugin.GameDTO;
import server.plugin.IAccountDao;
import server.plugin.IGameDao;
import shared.model_classes.Account;
import shared.model_classes.Game;

public class TicketToRidePlugin {
	public static void main(String[] args) {
		
		/*
		 * LANCE'S TEST CODE
		 */
//		JSONFactory factory = new JSONFactory();
//		IAccountDao accountDAO = factory.createAccountDao();
//		
//		AccountDTO dto = new AccountDTO();
//		Account account = new Account();
//		account.setAuthentication("HELLO-12345-THEREX");
//		account.setUsername("master");
//		account.setPassword("power overwhelming");
//		dto.setAccount(account);
//		dto.setGameID(1);
//		
//		accountDAO.addAccount(dto);
//		Set<AccountDTO> accountList = accountDAO.getAll();
//		
//		for(AccountDTO accountDTO : accountList){
//			System.out.println(accountDTO.getAccount().getAuthentication());
//			System.out.println(accountDTO.getAccount().getUsername());
//			System.out.println(accountDTO.getAccount().getPassword());
//			System.out.println(accountDTO.getGameID());
//		}
//		
//		accountDAO.clearData();
//		
//		IGameDao gameDAO = factory.createGameDao();
//		
//		GameDTO dto = new GameDTO();
//		Game game = new Game();
//		Game game1 = new Game();
//		Game game2 = new Game();
//		
//		dto.setGame(game);
//		dto.setGameID(game.getGameID());
//		gameDAO.addGame(dto);
//		
//		dto.setGame(game1);
//		dto.setGameID(1);
//		gameDAO.addGame(dto);
//		
//		Set<GameDTO> gameList = gameDAO.getAll();
//		
//		System.out.println("2 Games added:");
//		for(GameDTO gameDTO : gameList){
//			System.out.println(gameDTO.getGame().toString());
//			System.out.println(gameDTO.getGameID());
//		}
//		
//		System.out.println("Select by GameID:");
//		GameDTO dto1 = gameDAO.selectByGameID(1);
//		System.out.println(dto1.getGame().toString());
//		System.out.println(dto1.getGameID());
//		
//		gameDAO.deleteGame(dto);
//		gameList = gameDAO.getAll();
//
//		System.out.println("Game 1 deleted:");
//		for(GameDTO gameDTO : gameList){
//			System.out.println(gameDTO.getGame().toString());
//			System.out.println(gameDTO.getGameID());
//		}
//		
//		gameDAO.clearAllGames();
	}
}
