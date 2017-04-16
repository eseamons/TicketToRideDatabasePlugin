package plugin;

import java.io.IOException;
import java.util.Set;

import com.cedarsoftware.util.io.JsonWriter;

import server.plugin.CommandDTO;
import server.plugin.ICommandDao;
import shared.Result;

public class JSONCommandDao implements ICommandDao {

	@Override
	public Result addCommand(CommandDTO commandDTO) {
		try {
			String json = JsonWriter.objectToJson(commandDTO);
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
	public Result deleteGameCommands(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CommandDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CommandDTO> selectByGameID(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
