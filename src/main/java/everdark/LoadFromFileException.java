package ncg.chimpsahoy.everdark;

public class LoadFromFileException extends Exception {

	private GameState state;

	public LoadFromFileException(GameState state, String message) {
		super(message);
		this.state = state;	
	}

	public GameState getState() {
		return state;
	}

}
