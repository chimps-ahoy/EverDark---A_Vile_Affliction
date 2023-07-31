package ncg.chimpsahoy.everdark;

public class EndOfDialogueEvent extends Event {

	public EndOfDialogueEvent(String message) {
		super(message);
	}

	public String update(GameState state) {
		state.endDialogue();
		return super.getMessage();
	}
}
