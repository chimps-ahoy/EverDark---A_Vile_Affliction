package ncg.chimpsahoy.everdark;

public class LoadFromFileException extends Exception {

	private GameState state;

	public LoadFromFileException(GameState state) {
		this.state = state;	
	}

	public GameState getState() {
		return state;
	}

}
