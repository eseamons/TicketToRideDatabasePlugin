package junitTests;

import static org.junit.Assert.*;

import java.util.Set;


import org.junit.BeforeClass;
import org.junit.Test;

import plugin.SQLFactory;
import server.plugin.CommandDTO;
import server.plugin.GameDTO;
import server.plugin.ICommandDao;
import server.plugin.IDaoFactory;
import server.plugin.IGameDao;
import shared.command_classes.Command;
import shared.command_classes.LoginCommand;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public class SQLCommandDaoTest {

	private static ICommandDao commandDao;
	private static IGameDao gameDao;
	
	
	@BeforeClass
	public static void setup() {
		IDaoFactory factory = new SQLFactory();
		commandDao = factory.createCommandDao();
		gameDao = factory.createGameDao();
	}
	
	@Test
	public void addCommandTest() {
		commandDao.clearData();
		
		Command inputCommand = new LoginCommand();
		inputCommand.setAuth("AUTH_TEST");
		inputCommand.setCmdID(3);
		
		CommandDTO inputCommandDTO = new CommandDTO();
		
		inputCommandDTO.setCommand(inputCommand);;
		inputCommandDTO.setGameID(1);
		commandDao.addCommand(inputCommandDTO);
		
		Set<CommandDTO> outputCommandDtoSet = commandDao.selectByGameID(inputCommandDTO.getGameID()); 
		for(CommandDTO commandDTO : outputCommandDtoSet)
		{
			Command c = commandDTO.getCommand();
			
			assertEquals(c.getAuth(), inputCommand.getAuth());
			assertEquals(c.getCmdID(), inputCommand.getCmdID());
		}
		
	}
	
	@Test
	public void addGameTest() {
		gameDao.clearAllGames();
		
		GameLobby lobby = new GameLobby();
		lobby.setID(10);
		lobby.setMax_players(4);
		lobby.setName("GAME!");
		
		
		Game inputGame = new Game(lobby);
		
		GameDTO inputGameDTO = new GameDTO();
		
		inputGameDTO.setGame(inputGame);
		inputGameDTO.setGameID(inputGame.getGameID());
		gameDao.addGame(inputGameDTO);
		
		GameDTO gameDTO = gameDao.selectByGameID(inputGameDTO.getGameID()); 
		
			Game outputGame = gameDTO.getGame();
			
		
		
	}

}
