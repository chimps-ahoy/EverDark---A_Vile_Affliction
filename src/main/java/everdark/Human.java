package ncg.chimpsahoy.everdark;

public class Human extends Entity {

	public Human(int lvl) {
		super("test", lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, lvl, 'c', 1);
	}

	public String beginDialogue() throws EndOfDialogueException {
		String output = "\"Hello.\"\n" + "1 - \"Hello.\"\n" 
						+ "2 - \"Who are you?.\"\n";
		if (super.curStage() > 0) {
			output = "\"Hello, again.\"\n" + "1 - \"Hello.\"\n" 
						+ "2 - \"Who are you?.\"\n";
		}
		super.setStage(1);
		return output;
	}

	public String talk(int response) throws EndOfDialogueException {
		String output = "";
		if (super.curStage() == 0) {
			output = "\"Hello.\"\n" + "1 - \"Hello.\"\n" 
			       			+ "2 - \"Who are you?.\"\n";
			super.setStage(curStage()+1);
		} else if (super.curStage() == 1) {
			switch (response) {
				case 1:
					throw new EndOfDialogueException("\"Goodbye.\"\n");
				case 2:
					throw new EndOfDialogueException("\"I am a test of the dialogue routines.\"\n");
				default:
					output = "Please enter a valid option.\n";	
					break;
			}
		} else {
			output = "\"Hm...\"\n";	
		}
		return output;
	}

	public String toString() {
		return ConsoleColours.BLACK_BRIGHT + super.toString() + ConsoleColours.RESET; 
	}

}
