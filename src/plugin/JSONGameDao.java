package plugin;

import java.io.IOException;
import java.util.Set;

import com.cedarsoftware.util.io.JsonWriter;

import server.plugin.GameDTO;
import server.plugin.IGameDao;
import shared.Result;

public class JSONGameDao implements IGameDao {

	@Override
	public Result addGame(GameDTO gameDTO) {
		try {
			String json = JsonWriter.objectToJson(gameDTO);
			System.out.println(json);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new Result(false, e.getMessage());
		}

		return new Result(true, "");
	}

	@Override
	public Result clearAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result deleteGame(GameDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<GameDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameDTO selectByGameID(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result updateGame(GameDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
