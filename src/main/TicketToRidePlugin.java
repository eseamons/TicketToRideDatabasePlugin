package main;

import java.util.Set;

import plugin.JSONFactory;
import server.plugin.AccountDTO;
import server.plugin.IAccountDao;
import shared.model_classes.Account;

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
//		Set<AccountDTO> list = accountDAO.getAll();
//		
//		for(AccountDTO accountDTO : list){
//			System.out.println(accountDTO.getAccount().getAuthentication());
//			System.out.println(accountDTO.getAccount().getUsername());
//			System.out.println(accountDTO.getAccount().getPassword());
//			System.out.println(accountDTO.getGameID());
//		}
//		
//		accountDAO.clearData();
	}
}
