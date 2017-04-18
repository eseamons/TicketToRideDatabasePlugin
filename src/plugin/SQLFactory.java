package plugin;

import server.plugin.IAccountDao;
import server.plugin.ICommandDao;
import server.plugin.IDaoFactory;
import server.plugin.IGameDao;

public class SQLFactory implements IDaoFactory{

	@Override
	public IAccountDao createAccountDao() {
		System.out.println("Created new SQLAccountDao");
		return new SQLAccountDao();
	}

	@Override
	public ICommandDao createCommandDao() {
		System.out.println("Created new SQLCommandDao");
		return new SQLCommandDao();
	}

	@Override
	public IGameDao createGameDao() {
		System.out.println("Created new SQLGameDao");
		return new SQLGameDao();
	}

	
}
