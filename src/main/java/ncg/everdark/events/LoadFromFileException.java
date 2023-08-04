package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;

public class LoadFromFileException extends Throwable {

	private GameState state;

	public LoadFromFileException(GameState state) {
		this.state = state;	
	}

	public GameState getState() {
		return state;
	}

}
