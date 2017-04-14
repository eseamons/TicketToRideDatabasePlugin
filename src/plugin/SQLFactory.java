package plugin;

import server.plugin.IAccountDao;
import server.plugin.ICommandDao;
import server.plugin.IDaoFactory;
import server.plugin.IGameDao;

public class SQLFactory implements IDaoFactory{

	@Override
	public IAccountDao createAccountDao() {
		
		SQLAccountDao accountDao = new SQLAccountDao();
		return accountDao;
	}

	@Override
	public ICommandDao createCommandDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGameDao createGameDao() {
		// TODO Auto-generated method stub
		return null;
	}

	
}