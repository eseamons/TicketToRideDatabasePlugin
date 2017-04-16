package main;

import java.rmi.ServerException;
import java.util.Set;

import plugin.Database;
import plugin.SQLAccountDao;
import plugin.SQLFactory;
import server.plugin.AccountDTO;
import server.plugin.IAccountDao;
import server.plugin.IDaoFactory;
import shared.model_classes.Account;

public class TicketToRidePlugin {
	public static void main(String[] args) {
		try {
			Database.initialize();
			IDaoFactory factory = new SQLFactory();
			IAccountDao accountDao = factory.createAccountDao();
			
			Account account = new Account();
			account.setAuthentication("DTEDO-56738-DDERFT");
			account.setUsername("eseamons");
			account.setPassword("test");
			
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setAccount(account);
			accountDTO.setGameID(1);
			
			//accountDao.addAccount(accountDTO);
			//Set<AccountDTO> accountDTOList = accountDao.getAll();
//		
//			for(AccountDTO accDTO : accountDTOList) {
//				System.out.println(accDTO.getGameID());
//			}
			accountDTO = accountDao.selectByAuth(account.getAuthentication());
			account = accountDTO.getAccount();
			
			System.out.println(account.getUsername());
			
			//accountDao.clearData();
			
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
