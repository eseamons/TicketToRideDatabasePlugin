package plugin;

import server.plugin.IAccountDao;
import server.plugin.ICommandDao;
import server.plugin.IDaoFactory;
import server.plugin.IGameDao;

public class JSONFactory implements IDaoFactory {

	@Override
	public IAccountDao createAccountDao() {
		return new JSONAccountDao();
	}

	@Override
	public ICommandDao createCommandDao() {
		return new JSONCommandDao();
	}

	@Override
	public IGameDao createGameDao() {
		return new JSONGameDao();
	}

}
