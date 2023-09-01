package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;

public class EndDialogue implements Consequence {
	
	public EndDialogue() {
		
	}
	
	public String apply(GameState state) {
		state.endDialogue();
		return "";
	}
}
