package main;

import java.util.Set;

import plugin.JSONFactory;
import server.plugin.AccountDTO;
import server.plugin.CommandDTO;
import server.plugin.GameDTO;
import server.plugin.IAccountDao;
import server.plugin.ICommandDao;
import server.plugin.IGameDao;
import shared.command_classes.Command;
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
//		
//		ICommandDao commandDAO = factory.createCommandDao();
//
//		CommandDTO dto = new CommandDTO();
//		Command command = new Command();
//		command.setCmdID(0);
//		dto.setCommand(command);
//		dto.setGameID(0);
//		commandDAO.addCommand(dto);
//		
//		dto = new CommandDTO();
//		command = new Command();
//		command.setCmdID(1);
//		dto.setCommand(command);
//		dto.setGameID(0);
//		commandDAO.addCommand(dto);
//		
//		dto = new CommandDTO();
//		command = new Command();
//		command.setCmdID(2);
//		dto.setCommand(command);
//		dto.setGameID(1);
//		commandDAO.addCommand(dto);
//		
//		dto = new CommandDTO();
//		command = new Command();
//		command.setCmdID(3);
//		dto.setCommand(command);
//		dto.setGameID(0);
//		commandDAO.addCommand(dto);
//		
//		dto = new CommandDTO();
//		command = new Command();
//		command.setCmdID(4);
//		dto.setCommand(command);
//		dto.setGameID(0);
//		commandDAO.addCommand(dto);
//		
//		dto = new CommandDTO();
//		command = new Command();
//		command.setCmdID(5);
//		dto.setCommand(command);
//		dto.setGameID(1);
//		commandDAO.addCommand(dto);
//		
//		Set<CommandDTO> commandList = commandDAO.getAll();
//		Set<CommandDTO> commandListByID = commandDAO.selectByGameID(0);
//		
//		System.out.println("All:");
//		for(CommandDTO commandDTO : commandList){
//			System.out.println("Cmd" + commandDTO.getCommand().getCmdID());
//			System.out.println(commandDTO.getGameID());
//		}
//		System.out.println("\nID 0:");
//		for(CommandDTO commandDTO : commandListByID){
//			System.out.println("Cmd" + commandDTO.getCommand().getCmdID());
//			System.out.println(commandDTO.getGameID());
//		}
//		
//		commandDAO.deleteGameCommands(0);
//		commandList = commandDAO.getAll();
//		
//		System.out.println("\nAll without game 0:");
//		for(CommandDTO commandDTO : commandList){
//			System.out.println("Cmd" + commandDTO.getCommand().getCmdID());
//			System.out.println(commandDTO.getGameID());
//		}
//		
//		commandDAO.clearData();
	}
}
