package main;

import java.rmi.ServerException;

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
			String auth = "DTEDO-56738-DDERFT";
			account.setAuthentication(auth);
			account.setUsername("eseamons");
			account.setPassword("test");
			
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setAccount(account);
			
			accountDao.addAccount(accountDTO);
			//accountDao.clearData();
			
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
