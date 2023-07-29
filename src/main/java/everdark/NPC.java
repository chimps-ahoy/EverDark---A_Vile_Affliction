package ncg.chimpsahoy.everdark;
import java.util.LinkedList;

public abstract class NPC extends Entity {

	private Opinion opinion;	
	private int dialogueStage;

	public NPC(String name, int maxHp, int hp, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, int id) {
		super(name, maxHp, hp, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, id);
		dialogueStage = 0;
		opinion = Opinion.UNDEFINED;
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

	public static class Mathieu extends NPC {
		public Mathieu() {
			super("Mathieu", 1, 1, 5, 5, 2, 2, 4, 5, 2, 5, 4, 'M', 1);
		}

		public Mathieu(int stage) {
			super("Mathieu", 1, 1, 5, 5, 2, 2, 4, 5, 2, 5, 4, 'M', 1);
			setStage(stage);
		}

		public String beginDialogue(Player player) throws EndOfDialogueException {
			String output = "";
			if (player.getOrigin() == Player.Origin.TANIERE || super.getOpinion() == NPC.Opinion.HOSTILE) {
				throw new EndOfDialogueException("\"Just take what you want, and be quick with it. I can only put up with the stench of your kind for so long...\"");
			} else if (player.getOrigin() == Player.Origin.FAIM || super.getOpinion() == NPC.Opinion.FRIENDLY) {
				throw new EndOfDialogueException("\"Good day, neighbor.\"");
			} else if (player.getOrigin() == Player.Origin.UNDEFINED) {
				output = "\"Hello there. Where are you coming from, stranger?\"\n1 - I don't know.\n2 - The woods.\n3 - This village.\n4 - The town to the South, by the coast.";
			} else if (player.getOrigin() == Player.Origin.UNKNOWN) {
				output = "\"Have you remembered where you're from?\"\n1 - Yes\n2 - No";	
			} else {
				throw new EndOfDialogueException("\"Good day.\"");
			}
			return output;
		}

		public String talk(int response, LinkedList<Character> args, Player player) throws EndOfDialogueException {
			String output = "";
			if (curStage() == 0 && response == 1) {
				player.setOrigin(Player.Origin.UNKNOWN);
				setStage(1);
				throw new EndOfDialogueException("\"You don't know where you're from? Well, let me know if you remember.\"");
			} else if (curStage() == 0 && response == 2) {
				if (!args.contains('l')) {
					player.setOrigin(Player.Origin.OTHER);
				}
				throw new EndOfDialogueException("\"What an odd place to come from...\"");
			} else if (curStage() == 0 && response == 3) {
				if (!args.contains('l')) {
					player.setOrigin(Player.Origin.FAIM);
			  	}
				super.setOpinion(NPC.Opinion.FRIENDLY);
				throw new EndOfDialogueException("\"Really? Have you been gone long? I don't think any of us recognize you, I'm sorry. Please make yourself at home, nonetheless.\"");
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
				throw new EndOfDialogueException("\"Well, alright then. Come back if you do.\"");
			} else if (curStage() == 1) {
				output = "Invalid Selection.\n1 - Yes\n2 - No";
			} else {
				throw new EndOfDialogueException("\"I shouldn't say this!\"");
			}
			return output;
		}

	}
}
