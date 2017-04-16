package plugin;

import java.io.IOException;
import java.util.Set;

import com.cedarsoftware.util.io.JsonWriter;

import server.plugin.AccountDTO;
import server.plugin.IAccountDao;
import shared.Result;

public class JSONAccountDao implements IAccountDao {

	@Override
	public Result addAccount(AccountDTO accountDTO) {
		try {
			String json = JsonWriter.objectToJson(accountDTO);
			System.out.println(json);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new Result(false, e.getMessage());
		}

		return new Result(true, "");
	}

	@Override
	public Result clearData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<AccountDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDTO selectByAuth(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDTO selectByUserName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
