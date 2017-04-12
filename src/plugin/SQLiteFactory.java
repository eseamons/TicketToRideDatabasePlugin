package plugin;

import server.plugin.IAccountDao;
import server.plugin.ICommandDao;
import server.plugin.IDaoFactory;
import server.plugin.IGameDao;

public class SQLiteFactory implements IDaoFactory{

	@Override
	public IAccountDao createAccountDao() {
		// TODO Auto-generated method stub
		return null;
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
