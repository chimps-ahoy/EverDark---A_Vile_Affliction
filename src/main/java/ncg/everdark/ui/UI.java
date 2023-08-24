package ncg.everdark.ui;

import ncg.everdark.gamedata.*;

public abstract class UI {

	protected GameState state;
	protected boolean acceptingInput;

	public UI() {
		this.state = new GameState();
		this.acceptingInput = true;
	}

	public abstract void start();

	public abstract void handle(String input);

}
