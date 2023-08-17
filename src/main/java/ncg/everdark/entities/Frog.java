package ncg.everdark.entities;

import ncg.everdark.global.ConsoleColours;
import ncg.everdark.events.Event;
import ncg.everdark.events.EndOfDialogueEvent;

import java.util.Deque;

public class Frog extends Entity {

	public Frog(int lvl) {
		super("frog", lvl*2, lvl, lvl/2, lvl*3, 5, 2, 0,(int)(0.5*Math.pow(lvl, 2)), 2, 'f', Entity.Race.FROG);
	}

	public String beginDialogue(Player player) throws Event {
		throw new EndOfDialogueEvent("\"ribbit.\"");
	}

	public String toString() {
		return ConsoleColours.GREEN + super.toString() + ConsoleColours.RESET;
	}

}
