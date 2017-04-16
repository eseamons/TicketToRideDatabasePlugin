package plugin;

import server.plugin.IAccountDao;
import server.plugin.ICommandDao;
import server.plugin.IDaoFactory;
import server.plugin.IGameDao;

public class SQLFactory implements IDaoFactory{

	@Override
	public IAccountDao createAccountDao() {	
		return new SQLAccountDao();
	}

	@Override
	public ICommandDao createCommandDao() {		
		return new SQLCommandDao();
	}

	@Override
	public IGameDao createGameDao() {
		return new SQLGameDao();
	}

	
}
