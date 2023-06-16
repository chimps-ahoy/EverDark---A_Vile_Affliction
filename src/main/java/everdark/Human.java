package ncg.chimpsahoy.everdark;
import java.util.LinkedList;

public class Human extends Entity {

	public Human(int lvl) {
		super("test", lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, 'c', 1);
	}

	public String beginDialogue() throws EndOfDialogueException {
		String output = "\"Hello.\"\n" + "1 - \"I'm the president.\"\n" 
						+ "2 - \"Who are you?.\"";
		if (super.curStage() > 0) {
			output = "\"Hello, again.\"\n" + "1 - \"I'm the president.\"\n" 
						+ "2 - \"Who are you?.\"";
		}
		super.setStage(1);
		return output;
	}

	public String talk(int response, LinkedList<Character> args, Entity player) throws EndOfDialogueException {
		String output = "";
	      	if (super.curStage() == 1) {
			if (response == 1 && player.getCharm() > this.getWil() && args.contains('l')) {
				throw new EndOfDialogueException("\"You lied to me, but I believe it.\"");
			} else if (response == 1 &&  args.contains('l')) {
				throw new EndOfDialogueException("\"I don't believe that.\"");
			} else if (response == 1) {
				throw new EndOfDialogueException("\"That is the truth.\"");
			} else if (response == 2) {
				throw new EndOfDialogueException("\"I am a test of the dialogue routines.\"");
			} else {
				throw new IllegalArgumentException("Invalid selection.");
			}
		} else {
			output = "\"I shouldn't say this.\"";	
		}
		return output;
	}

	public String toString() {
		return ConsoleColours.BLACK_BRIGHT + super.toString() + ConsoleColours.RESET; 
	}

}
