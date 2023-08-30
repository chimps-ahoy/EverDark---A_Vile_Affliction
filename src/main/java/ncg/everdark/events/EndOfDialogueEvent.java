package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;

public class EndOfDialogueEvent extends Event {
	public EndOfDialogueEvent(String message) {
		super(message, (state) -> {state.endDialogue(); return "";});
	}
}
