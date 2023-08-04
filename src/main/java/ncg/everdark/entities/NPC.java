package ncg.everdark.entities;

import ncg.everdark.global.ConsoleColours;
import ncg.everdark.events.Event;
import ncg.everdark.events.EndOfDialogueEvent;
import ncg.everdark.dialogue.DialogueTree;

import java.util.Deque;
import java.util.function.BiPredicate;

public class NPC extends Entity {

	private Opinion opinion;	
	private int dialogueStage;
	private DialogueTree dialogue;

	public NPC(String name, int maxHp, int hp, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, int id) {
		super(name, maxHp, hp, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, id);
		dialogueStage = 0;
		opinion = Opinion.UNDEFINED;
		dialogue = new DialogueTree();
	}

	public String beginDialogue(Player p) throws Event {
		return dialogue.beginDialogue(p, this);
	}

	public String talk(int response, Deque<Character> args, Player p) throws Event {
		return dialogue.progress(p, this, response);
	}	

	public enum Opinion { UNDEFINED, HOSTILE, FEARFUL, NEUTRAL, FRIENDLY }

	public int curStage() {
		return dialogueStage;
	}

	public void setStage(int stage) {
		dialogueStage = stage;
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	public void setDialogue(DialogueTree dialogue) {
		this.dialogue = dialogue;
	}

	public String toString() {
		return ConsoleColours.BLACK_BRIGHT + super.toString() + ConsoleColours.RESET;
	}

	public static class Mathieu extends NPC {

		public Mathieu() {
			super("Mathieu", 1, 1, 5, 5, 2, 2, 4, 5, 2, 5, 4, 'M', 1);
		}

		public Mathieu(int stage) {
			this();
			setStage(stage);
		}

		public String beginDialogue(Player player) throws Event {
			String output = "";
			if (player.getOrigin() == Player.Origin.TANIERE || super.getOpinion() == NPC.Opinion.HOSTILE) {
				throw new EndOfDialogueEvent("\"Just take what you want, and be quick with it. I can only put up with the stench of your kind for so long...\"");
			} else if (player.getOrigin() == Player.Origin.FAIM || super.getOpinion() == NPC.Opinion.FRIENDLY) {
				throw new EndOfDialogueEvent("\"Good day, neighbor.\"");
			} else if (player.getOrigin() == Player.Origin.UNDEFINED) {
				output = "\"Hello there. Where are you coming from, stranger?\"\n1 - I don't know.\n2 - The woods.\n3 - This village.\n4 - The town to the South, by the coast.";
			} else if (player.getOrigin() == Player.Origin.UNKNOWN) {
				output = "\"Have you remembered where you're from?\"\n1 - Yes\n2 - No";	
			} else {
				throw new EndOfDialogueEvent("\"Good day.\"");
			}
			return output;
		}

		public String talk(int response, Deque<Character> args, Player player) throws Event {
			String output = "";
			if (curStage() == 0 && response == 1) {
				player.setOrigin(Player.Origin.UNKNOWN);
				setStage(1);
				throw new EndOfDialogueEvent("\"You don't know where you're from? Well, let me know if you remember.\"");
			} else if (curStage() == 0 && response == 2) {
				if (!args.contains('l')) {
					player.setOrigin(Player.Origin.OTHER);
				}
				throw new EndOfDialogueEvent("\"What an odd place to come from...\"");
			} else if (curStage() == 0 && response == 3) {
				if (!args.contains('l')) {
					player.setOrigin(Player.Origin.FAIM);
			  	}
				super.setOpinion(NPC.Opinion.FRIENDLY);
				throw new EndOfDialogueEvent("\"Really? Have you been gone long? I don't think any of us recognize you, I'm sorry. Please make yourself at home, nonetheless.\"");
			} else if (curStage() == 0 && response == 4) {
				if (!args.contains('l')) {
					player.setOrigin(Player.Origin.TANIERE);
				}
				super.setOpinion(NPC.Opinion.HOSTILE);
				output = beginDialogue(player);
			} else if (curStage() == 0) {
				output = "Invalid Selection.\n1 - I don't know.\n2 - The woods.\n3 - This village.\n4 - The town to the South, by the coast.";
			} else if (curStage() == 1 && response == 1) {
				setStage(0);
				output = "\"Well, where is it then?\"\n1 - I don't know.\n2 - The woods.\n3 - This village.\n4 - The town to the South, by the coast.";
			} else if (curStage() == 1 && response == 2) {
				throw new EndOfDialogueEvent("\"Well, alright then. Come back if you do.\"");
			} else if (curStage() == 1) {
				output = "Invalid Selection.\n1 - Yes\n2 - No";
			} else {
				throw new EndOfDialogueEvent("\"I shouldn't say this!\"");
			}
			return output;
		}

	}

	public static class Maia extends NPC {

		public Maia() {
			super("Maia", 1, 1, 1, 3, 3, 2, 3, 2, 4, 1, 3, 'W', 2);
		}

		public Maia(int stage) {
			this();
			super.setStage(stage);
		}

		public String beginDialogue(Player player) throws Event {
			String output = "\"Hello.\"";
			if (player.getOrigin() == Player.Origin.TANIERE && super.getOpinion() == NPC.Opinion.FEARFUL) {
				throw new EndOfDialogueEvent("\"Please... I have children...\"");
			} else if (player.getOrigin() == Player.Origin.TANIERE && super.getOpinion() == NPC.Opinion.HOSTILE) {
				throw new EndOfDialogueEvent("\"You beast... Just leave already, scum.\"");
			} else if (player.getOrigin() == Player.Origin.OTHER) {
				throw new EndOfDialogueEvent("\"Hello there, woodsman.\"");
			} else if (player.getOrigin() == Player.Origin.TANIERE) {
				output = "\"Why are you here? You ought to just stay in your slums.\"\n1 - I've come to take your food.\n" +
																											"2 - I've come to take your wealth.\n" +
																											"3 - I've come for your children.\n" +
																											"4 - I've come for you.\n" +
																											"5 - Just came to visit.";
			} else if (player.getOrigin() == Player.Origin.UNKNOWN) {
				output = "\"So you're the one who doesn't remember where they're from? How peculiar...\"\n1 - I don't know where this place is.\n" +
																																	"2 - I have bad memory.\n3 - I think I remember.";
			} else if (player.getOrigin() == Player.Origin.UNDEFINED) {
				output = "\"Hello there, stranger.\"\n1 - Where am I?\n2 - Who are you?\n3 - Goodbye.";
			} else if (player.getOrigin() == Player.Origin.FAIM) {
				output = "\"So you're from here? I don't remember you.\"\n1 - I don't remember you.\n2 - I remember you.\n3 - How rude.\n4 - Goodbye.";
			}
			return output;
		}

		public String talk(int response, Deque<Character> args, Player player) throws Event {
			String output = ""; 
			if (super.curStage() == 0 && player.getOrigin() == Player.Origin.TANIERE && response == 1) {
				throw new EndOfDialogueEvent("\"I haven't even a crumb. Your people already took it all.\"");
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.TANIERE && response == 2) {
				output = "\"The only precious thing I have are my children.\"\n1 - Then I will take them.\n2 - Oh."; 
				super.setStage(1);
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.TANIERE && response == 3) {
				super.setOpinion(NPC.Opinion.HOSTILE);
				output = beginDialogue(player);
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.TANIERE && response == 4) {
				super.setOpinion(NPC.Opinion.FEARFUL);
				output = beginDialogue(player);
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.TANIERE && response == 5) {
				throw new EndOfDialogueEvent("\"We've been getting enough visits from your kind.\"");
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.UNKNOWN && response == 1) { 
				throw new EndOfDialogueEvent("\"This village is called Faim.\"");
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.UNKNOWN && response == 2) { 
				throw new EndOfDialogueEvent("\"Do you now?\"");
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.UNKNOWN && response == 3) { 
				throw new EndOfDialogueEvent("\"You ought to tell Mathieu; he's in charge around here. He lives in the Southern house.\"");
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.UNDEFINED && response == 1) { 
				output = "\"This village is called Faim.\"\n1 - Where am I?\n2 - Who are you?\n3 - Goodbye.";	
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.UNDEFINED && response == 2) { 
				output = "\"I am " + super.getName() + ".\"\n1 - Where am I?\n2 - Who are you?\n3 - Goodbye.";	
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.UNDEFINED && response == 3) { 
				throw new EndOfDialogueEvent("\"Goodbye.\"");	
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.FAIM && response == 1) {
				throw new EndOfDialogueEvent("\"That makes two of us.\"");
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.FAIM && response == 2) {
				throw new EndOfDialogueEvent("\"...\"");
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.FAIM && response == 3) {
				throw new EndOfDialogueEvent("\"My apologies.\""); 
			} else if (super.curStage() == 0 && player.getOrigin() == Player.Origin.FAIM && response == 4) {
				throw new EndOfDialogueEvent("\"Goodbye.\""); 
			} else if (super.curStage() == 1 && player.getOrigin() == Player.Origin.TANIERE && response == 1) {
				super.setOpinion(NPC.Opinion.HOSTILE);
				output = beginDialogue(player);
			} else if (super.curStage() == 1 && player.getOrigin() == Player.Origin.TANIERE && response == 2) {
				 throw new EndOfDialogueEvent("\"I suppose you couldn't understand that.\"");
			} else {
				output = "Invalid Selection.";
			}
			return output;
		}
	}

	public static class Pirate extends NPC {
		
		private static int count = 2000;
		private static final int RANGE = 2000;

		public Pirate() { 
			super("pirate", 10, 10, 5, 5, 2, 2, 1, 1, 1, 4, 2, 'p', (count++%RANGE) + RANGE);
		}

		public String beginDialogue(Player player) throws Event {
			String output = "";
			if (player.getOrigin() == Player.Origin.TANIERE || super.getOpinion() == NPC.Opinion.FRIENDLY) {
				throw new EndOfDialogueEvent("\"Yar, mate! Good to see ye!\"");
			} else {
				output = "\"Yer far from home, landlubber.\"\n1 - This be me home!\n2 - That's right.";
			}
			return output;
		}

		public String talk(int response, Deque<Character> args, Player player) throws Event {
			String output = "";
			if (super.curStage() == 0 && player.getOrigin() == Player.Origin.UNDEFINED && response == 1) {
				super.setOpinion(NPC.Opinion.FRIENDLY);
				if (!args.contains('l')) {
					player.setOrigin(Player.Origin.TANIERE);
				}
				output = beginDialogue(player);
			} else if (super.curStage() == 0 && response == 1) {
				throw new EndOfDialogueEvent("\"That ain't true!\"");
			} else if (super.curStage() == 0 && response == 2) {
				throw new EndOfDialogueEvent("");
			} else {
				output = "Invalid Selection.";
			}
			return output;
		}

	}

	public static class Captain extends NPC {
		
		public Captain() {
			super("Captain", 10, 10, 10, 10, 3, 2, 5, 3, 1, 7, 2, 'C', 3);
		}

		public Captain(int stage) {
			this();
			super.setStage(stage);
		}

		public String beginDialogue(Player player) throws Event {
			String desc = "The burly man has no interest in talking to you.\nHe strongly reeks of alcohol.\nHe is fiddling with a necklace between his index finger and thumb.";
			String[] details = desc.split("\n");
			String output = details[0];
			final int PERC_DELTA = 1;
			for (int i = 1; i < details.length && player.getPerc() >= i*PERC_DELTA; i++) {
				output += ' ' + details[i];
			}
			throw new EndOfDialogueEvent(output);
		}
	}
}
