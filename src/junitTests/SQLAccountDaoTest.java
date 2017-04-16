package junitTests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import plugin.SQLFactory;
import server.plugin.AccountDTO;
import server.plugin.IAccountDao;
import server.plugin.IDaoFactory;
import shared.model_classes.Account;

public class SQLAccountDaoTest {

	private static IAccountDao accountDao;
	
	
	@BeforeClass
	public static void setup() {
		IDaoFactory factory = new SQLFactory();
		accountDao = factory.createAccountDao();

	}
	
	@Test
	public void addAccountTest() {
		accountDao.clearData();
		Account inputAccount = new Account();
		AccountDTO inputAccountDTO = new AccountDTO();
		
		inputAccount.setAuthentication("DTEDO-56738-DDERFT");
		inputAccount.setUsername("eseamons");
		inputAccount.setPassword("test");
		
		inputAccountDTO.setAccount(inputAccount);
		inputAccountDTO.setGameID(1);
		accountDao.addAccount(inputAccountDTO);
		
		AccountDTO outputAccountDTO = accountDao.selectByAuth(inputAccount.getAuthentication());
		
		Account outputAccount = outputAccountDTO.getAccount();
		
		assertEquals(outputAccount.getUsername(),inputAccount.getUsername());
		assertEquals(outputAccount.getPassword(),inputAccount.getPassword());
		assertEquals(outputAccount.getAuthentication(),inputAccount.getAuthentication());
		assertEquals(outputAccountDTO.getGameID(),inputAccountDTO.getGameID());
	}
	
	@Test
	public void getAllTest() {
		accountDao.clearData();
		
		//Account One and AccountDTO One
		Account inputAccountOne = new Account();
		AccountDTO inputAccountDTOOne = new AccountDTO();
		
		inputAccountOne.setAuthentication("DTEDO-56738-DDERFT");
		inputAccountOne.setUsername("eseamons");
		inputAccountOne.setPassword("test");
		
		inputAccountDTOOne.setAccount(inputAccountOne);
		inputAccountDTOOne.setGameID(1);
		
		//Account Two and AccountDTO Two
		Account inputAccountTwo = new Account();
		AccountDTO inputAccountDTOTwo = new AccountDTO();
		
		inputAccountTwo.setAuthentication("AGTWI-93658-YUTIOH");
		inputAccountTwo.setUsername("rkseamons");
		inputAccountTwo.setPassword("pass");
		
		inputAccountDTOTwo.setAccount(inputAccountTwo);
		inputAccountDTOTwo.setGameID(2);
		
		//adding accounts
		accountDao.addAccount(inputAccountDTOOne);
		accountDao.addAccount(inputAccountDTOTwo);
		
		Set<AccountDTO> outputAccountDTOSet = accountDao.getAll();
		
		assertEquals(outputAccountDTOSet.size(), 2);		
	}
	
	
	

}
