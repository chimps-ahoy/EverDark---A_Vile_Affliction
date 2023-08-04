package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;

public class Event extends Throwable {
	
	private Consequence effect;

	public Event(String message, Consequence effect) {
		super(message);
		this.effect = effect;
	}

	public Event(Consequence effect) {
		super();
		this.effect = effect;
	}

	public String update(GameState state) {
		return effect.apply(state);
	}

}
