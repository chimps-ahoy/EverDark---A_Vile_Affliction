package ncg.chimpsahoy.everdark;
import java.util.LinkedList;

public class Frog extends Entity {

	private static int count = 1000;
	private static final int RANGE = 1000;

	public Frog(int lvl) {
		super("frog", lvl*10, lvl*10, lvl*2, lvl, lvl/2, lvl*3, 5, 2, 0,(int)(0.5*Math.pow(lvl, 2)), 2, 'f', (count++%RANGE) + RANGE);
	}

	public String beginDialogue(Player player) throws EndOfDialogueEvent {
		throw new EndOfDialogueEvent("\"ribbit.\"");
	}

	public String toString() {
		return ConsoleColours.GREEN + super.toString() + ConsoleColours.RESET;
	}

}
