package ncg.chimpsahoy.everdark;

public class Human extends Entity {

	public Human(int lvl) {
		super("test", lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, 'c', 1);
	}

	public String beginDialogue() throws EndOfDialogueException {
		String output = "\"Hello.\"\n" + "1 - \"Hello.\"\n" 
						+ "2 - \"Who are you?.\"";
		if (super.curStage() > 0) {
			output = "\"Hello, again.\"\n" + "1 - \"Hello.\"\n" 
						+ "2 - \"Who are you?.\"";
		}
		super.setStage(1);
		return output;
	}

	public String talk(int response) throws EndOfDialogueException {
		String output = "";
	      	if (super.curStage() == 1) {
			switch (response) {
				case 1:
					throw new EndOfDialogueException("\"Goodbye.\"");
				case 2:
					throw new EndOfDialogueException("\"I am a test of the dialogue routines.\"");
				default:
					output = "Please enter a valid option.";	
					break;
			}
		} else {
			output = "\"Hm...\"";	
		}
		return output;
	}

	public String toString() {
		return ConsoleColours.BLACK_BRIGHT + super.toString() + ConsoleColours.RESET; 
	}

}
