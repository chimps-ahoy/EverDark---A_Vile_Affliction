package ncg.everdark.ui;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.global.Config;

public abstract class UI {

	protected GameState state;
	protected boolean acceptingInput;

	public UI() {
		this.state = new GameState(Config.MAIN_MENU);
		this.acceptingInput = true;
	}

	public abstract void start();

	public abstract void handle(String input);

}
