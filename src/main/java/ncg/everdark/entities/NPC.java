package ncg.everdark.entities;

import ncg.everdark.global.ConsoleColours;
import ncg.everdark.events.Event;
import ncg.everdark.events.EndOfDialogueEvent;
import ncg.everdark.dialogue.DialogueTree;

import java.util.Deque;

public class NPC extends Entity {

	private Opinion opinion;	
	private int dialogueStage;//may be depreciated after dialogue rework, but is used in DialogueTree code so cannot be removed. Probably just gonna leave it in
									  //in the case that i find that one edge case that needs it
	private DialogueTree dialogue;

	public enum Opinion { UNDEFINED, HOSTILE, FEARFUL, NEUTRAL, CURIOUS, FRIENDLY }

	public static final NPC MAIA = new NPC("Maia", 3, 2, 4, 2, 3, 3, 5, 1, 2, 'm').setDialogue(new DialogueTree()
						 .add("\"Please... I have children...\"", (p,q) -> q.getOpinion() == Opinion.FEARFUL)
						 .add("\"Why don't you just leave already. You've already taken everything we have.\"",
						 (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == Opinion.HOSTILE)
						 .add("\"You're that odd one Mathieu told me about. You say some strange things...\"", 
						 (p,q) -> p.getOrigin() == Player.Origin.OTHER)
						 .add("\"You say you don't know where you're from? Well, if you remember, you should let Mathieu know; he's in charge around here.\"",
						 (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN)
						 .add("\"So you're from around here? I'd love to get to know you some more. The kids have been wanting someone to play with too...\"",
						 (p,q) -> p.getOrigin() == Player.Origin.FAIM)
						 .add("\"Hello there, stranger.\"")
						 .add(new int[] {5,1}, "What is this place?", "\"This town is called Faim. Welcome.\"")
						 .add(new int[] {5,2}, "Who are you?", "\"I am Maia. Nice to meet you.\"")
						 .add(new int[] {5,3}, "Why is it so dark?", "\"What do you mean? Tonight is a full moon, and it shines so brightly.\"")
						 .add(new int[] {5,4}, "Bye.", "\"Bye, then.\"")
					    .add(new int[] {3,1}, "What is this place?", "\"This town is called Faim. Welcome.\"")
						 .add(new int[] {3,2}, "Who are you?", "\"I am Maia. Nice to meet you.\"")
						 .add(new int[] {3,3}, "Why is it so dark?", "\"What do you mean? Tonight is a full moon, and it shines so brightly.\"")
						 .add(new int[] {3,4}, "Bye.", "\"Bye, then.\"")
						 .add(new int[] {5,2,1}, "When is sunrise?", "\"Sun... rise?\" She looks at you like you have two heads, \"Where are you from? And how old are you? " +
							 "The sun has not risen here for thirty years. If there is somewhere it still shines, I'd love to go.\"",	
						 (g) -> g.setInterOpinion(Opinion.CURIOUS))
						 .add(new int[] {5,2,2}, "Of course.", "\"Yes.\"")
						 .add(new int[] {3,2,1}, "When is sunrise?", "\"Sun... rise?\" She looks at you like you have two heads, \"Where are you from? And how old are you? " +
							 "The sun has not risen here for thirty years. If there is somewhere it still shines, I'd love to go.\"", 
						 (g) -> g.setInterOpinion(Opinion.CURIOUS))
						 .add(new int[] {3,2,2}, "Of course.", "\"Yes.\""));

	public static final NPC MATHIEU = new NPC("Mathieu", 5, 5, 2, 2, 3, 5, 2, 4, 2, 'm').setDialogue(new DialogueTree()
						 .add("\"Take what you want and begone. I can only stand your smell so long.\"", 
						 (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == Opinion.HOSTILE)
						 .add("\"Have you remembered where you're from?\"", (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN || q.getOpinion() == Opinion.CURIOUS)
						 .add("\"Hello there, friend.\"", (p,q) -> p.getOrigin() == Player.Origin.FAIM || q.getOpinion() == Opinion.FRIENDLY)
						 .add("\"Hello there, stranger. From where are you coming?\"", (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN || p.getOrigin() == Player.Origin.UNDEFINED)
						 .add(new int[] {1,1}, "Yes.", "\"Well, where is it, then?\"").add(new int[] {1,2}, "No.", "\"Well, let me know if you do.\"")
						 .add(new int[] {3,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
							 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", (g) -> g.setInterOpinion(Opinion.FRIENDLY),
							 (g) -> g.setPlayerOrigin(Player.Origin.FAIM))
						 .add(new int[] {3,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
							 (g) -> g.setInterOpinion(Opinion.NEUTRAL), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
						 .add(new int[] {3,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
							 (g) -> g.setInterOpinion(Opinion.HOSTILE), (g) -> g.setPlayerOrigin(Player.Origin.TANIERE))
						 .add(new int[] {3,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
							 + "and if you remember, let me know.\"", (g) -> g.setInterOpinion(Opinion.CURIOUS), (g) -> g.setPlayerOrigin(Player.Origin.UNKNOWN))
						 .add(new int[] {1,0,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
							 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", (g) -> g.setInterOpinion(Opinion.FRIENDLY),
							 (g) -> g.setPlayerOrigin(Player.Origin.FAIM))
						 .add(new int[] {1,0,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
							 (g) -> g.setInterOpinion(Opinion.NEUTRAL), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
						 .add(new int[] {1,0,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
							 (g) -> g.setInterOpinion(Opinion.HOSTILE), (g) -> g.setPlayerOrigin(Player.Origin.TANIERE))
						 .add(new int[] {1,0,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
							 + "and if you remember, let me know.\"", (g) -> g.setInterOpinion(Opinion.CURIOUS), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
						 .add("\"Hello there.\""));

	public static final NPC ELE = new NPC("Ele", 1, 1, 1, 2, 1, 1, 3, 0, 1, 'e').setDialogue(new DialogueTree()
								.add("\"My doll isn't very good anymore, but I still play with it!\"",(p,q) -> p.getOrigin() == Player.Origin.FAIM || q.getOpinion() == Opinion.FRIENDLY)
								.add("The girl is too preoccupied playing with a doll to speak to you. The doll is shoddily made out of wood, and its hair " +
								"is falling out.", (p,q) -> p.getStat(Entity.Stat.PERC) >= 5)
								.add("The girl is too preoccupied playing with a doll to speak to you.")
								.add(new int[] {0,1}, "Offer to help.", "\"Would you really? Thank you!\"")
								.add(new int[] {0,2}, "Leave.", "\"Bye bye!\""));

	public static final NPC OLIVER = new NPC("Oliver", 1, 1, 2, 3, 1, 1, 3, 0, 1, 'o').setDialogue(
										new DialogueTree().add("\"My mom says not to play in the woods to the north, but I want to so bad! The wind always whispers funny jokes " +
											"in my ears...\""));

	public static final NPC CAPTAIN = new NPC("Captain", 10, 10, 3, 4, 5, 8, 4, 10, 4, 'c').setDialogue(new DialogueTree()
														.add("\"Yar, lad...\"", (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == Opinion.FRIENDLY)
														.add("The large man doesn't even meet your eyes as you approach. Instead, he simply stares at a chain he holds between " +
														"his fat fingers.", (p,q) -> p.getStat(Entity.Stat.PERC) >= 6)
														.add("The large man doesn't even acknowledge you. He reeks of booze.", (p,q) -> p.getStat(Entity.Stat.PERC) >= 3)
														.add("The large man doesn't even acknowledge you."));

	public NPC(String name, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod) {
		super(name, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod);
		dialogueStage = 0;
		opinion = Opinion.UNDEFINED;
		dialogue = new DialogueTree();
	}

	public String beginDialogue(Player p) throws Event {
		return dialogue.beginDialogue(p, this);
	}

	public String talk(int response, Deque<Character> args, Player p) throws Event {
		return dialogue.progress(p, this, response, args);
	}	

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

	public NPC setDialogue(DialogueTree dialogue) {
		this.dialogue = dialogue;
		return this;
	}

	public String toString() {
		return ConsoleColours.BLACK_BRIGHT + super.toString() + ConsoleColours.RESET;
	}

}
