package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;

public class Event extends Throwable {
	
	private Consequence effect;

	public Event(String message, Consequence... effects) {
		super(message);
		this.effect = (state) -> { 
											String output = "";
											for (Consequence effect : effects) {
												output += effect.apply(state);
											}	
											return output;
									    };
	}

	public Event(String message, Consequence effect) {
		super(message);
		this.effect = effect;
	}

	public Event(Consequence effect) {
		super();
		this.effect = effect;
	}

	public String update(GameState state) {
		return super.getMessage() + effect.apply(state);
	}

}
