package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;

import java.util.function.Function;

public class EndOfDialogueEvent extends Event {

	public EndOfDialogueEvent(String message) {
		super(message, (state) -> {state.endDialogue(); return message;});
	}

}
