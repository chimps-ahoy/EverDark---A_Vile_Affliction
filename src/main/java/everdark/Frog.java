package ncg.chimpsahoy.everdark;
import java.util.LinkedList;

public class Frog extends Entity {

	private static int count = 1000;

	public Frog(int lvl) {
		super("frog", lvl*10, lvl*10, lvl*2, lvl, lvl/2, lvl*3, 5, 2, 0,(int)(0.5*Math.pow(lvl, 2)), 2, 'f', count++);
	}

	public String beginDialogue() throws EndOfDialogueException {
		throw new EndOfDialogueException("\"ribbit.\"");
	}

	public String talk(int response, LinkedList<Character> args, Entity player) throws EndOfDialogueException {
		throw new EndOfDialogueException("\"ribbit.\"");
	}

	public String toString() {
		return ConsoleColours.GREEN + super.toString() + ConsoleColours.RESET;
	}

}
