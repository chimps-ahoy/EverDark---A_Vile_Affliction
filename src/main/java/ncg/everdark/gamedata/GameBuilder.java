package ncg.everdark.gamedata;

import ncg.everdark.dialogue.DialogueTree;
import ncg.everdark.events.Consequence;
import ncg.everdark.entities.*;
import ncg.everdark.entities.Entity.Stat;
import ncg.everdark.items.Item;

import java.util.Scanner;

public class GameBuilder {//this is ONLY to be used for development so i can construct the .game file quickly, should be removed for releases
	public static void buildGame()  {
		Map MAIN_MENU = new Map("main", "Would you like to load from file? (Y/N): ", null, null, 0, 0, 0);
		Map WW;
		Map CAVE_1;
		Map CAVE_2;
		Map CAVE_3;
		Map CAVE_4;
		Map CAVE_5;
		Map TWN;
		Map TNO;
		Map TAN;
		Map SHIP;

		NPC MAIA = new NPC("Maia", 3, 2, 4, 2, 3, 3, 5, 1, 2, 'm', Entity.Race.HUMAN);
		MAIA.setDialogue(new DialogueTree()
			 .add("\"Please... I have children...\"", (p,q) -> q.getOpinion() == NPC.Opinion.FEARFUL)
			 .add("\"Why don't you just leave already. You've already taken everything we have.\"",
			 (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == NPC.Opinion.HOSTILE)
			 .add("\"You're that odd one Mathieu told me about. You say some strange things...\"", 
			 (p,q) -> p.getOrigin() == Player.Origin.OTHER || q.getOpinion() == NPC.Opinion.NEUTRAL)
			 .add("\"You say you don't know where you're from? Well, if you remember, you should let Mathieu know; he's in charge around here.\"",
			 (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN || q.getOpinion() == NPC.Opinion.CURIOUS)
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
				 "The sun has not risen here for thirty years. If there is somewhere it still shines, I'd love to go.\"")
			 .add(new int[] {5,2,2}, "Of course.", "\"Yes.\"")
			 .add(new int[] {3,2,1}, "When is sunrise?", "\"Sun... rise?\" She looks at you like you have two heads, \"Where are you from? And how old are you? " +
				 "The sun has not risen here for thirty years. If there is somewhere it still shines, I'd love to go.\"")
			 .add(new int[] {3,2,2}, "Of course.", "\"Yes.\""));

		NPC MATHIEU = new NPC("Mathieu", 5, 5, 2, 2, 3, 5, 2, 4, 2, 'm', Entity.Race.HUMAN);
		MATHIEU.setDialogue(new DialogueTree()
				 .add("\"Take what you want and begone. I can only stand your smell so long.\"", 
				 (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == NPC.Opinion.HOSTILE)
				 .add("\"Have you remembered where you're from?\"", (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN || q.getOpinion() == NPC.Opinion.CURIOUS)
				 .add("\"Hello there, friend.\"", (p,q) -> p.getOrigin() == Player.Origin.FAIM || q.getOpinion() == NPC.Opinion.FRIENDLY)
				 .add("\"Hello there, stranger. From where are you coming?\"", (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN || p.getOrigin() == Player.Origin.UNDEFINED)
				 .add(new int[] {1,1}, "Yes.", "\"Well, where is it, then?\"").add(new int[] {1,2}, "No.", "\"Well, let me know if you do.\"")
				 .add(new int[] {3,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
					 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", (g) -> g.setInterOpinion(NPC.Opinion.FRIENDLY),
					 (g) -> g.setPlayerOrigin(Player.Origin.FAIM))
				 .add(new int[] {3,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
					 (g) -> g.setInterOpinion(NPC.Opinion.NEUTRAL), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
				 .add(new int[] {3,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
					 (g) -> g.setInterOpinion(NPC.Opinion.HOSTILE), (g) -> g.setPlayerOrigin(Player.Origin.TANIERE))
				 .add(new int[] {3,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
					 + "and if you remember, let me know.\"", (g) -> g.setInterOpinion(NPC.Opinion.CURIOUS), (g) -> g.setPlayerOrigin(Player.Origin.UNKNOWN))
				 .add(new int[] {1,0,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
					 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", (g) -> g.setInterOpinion(NPC.Opinion.FRIENDLY),
					 (g) -> g.setPlayerOrigin(Player.Origin.FAIM))
				 .add(new int[] {1,0,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
					 (g) -> g.setInterOpinion(NPC.Opinion.NEUTRAL), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
				 .add(new int[] {1,0,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
					 (g) -> g.setInterOpinion(NPC.Opinion.HOSTILE), (g) -> g.setPlayerOrigin(Player.Origin.TANIERE))
				 .add(new int[] {1,0,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
					 + "and if you remember, let me know.\"", (g) -> g.setInterOpinion(NPC.Opinion.CURIOUS), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
				 .add("\"Hello there.\""));

		NPC ELE = new NPC("Ele", 1, 1, 1, 2, 1, 1, 3, 0, 1, 'e', Entity.Race.HUMAN);
		ELE.setDialogue(new DialogueTree()
			.add("\"My doll isn't very good anymore, but I still play with it!\"",(p,q) -> p.getOrigin() == Player.Origin.FAIM || q.getOpinion() == NPC.Opinion.FRIENDLY)
			.add("The girl is too preoccupied playing with a doll to speak to you. The doll is shoddily made out of wood, and its hair " +
			"is falling out.", (p,q) -> p.getStat(Entity.Stat.PERC) >= 5)
			.add("The girl is too preoccupied playing with a doll to speak to you.")
			.add(new int[] {0,1}, "Offer to help.", "\"Would you really? Thank you!\"", (g) -> g.givePlayer(new Item("Old doll", 0.2, 0.0)))
			.add(new int[] {0,2}, "Leave.", "\"Bye bye!\""));

		NPC OLIVER = new NPC("Oliver", 1, 1, 2, 3, 1, 1, 3, 0, 1, 'o', Entity.Race.HUMAN);
		OLIVER.setDialogue(new DialogueTree().add("\"The woods to the north can be so scary! I went in the cave once, and even though I took two " + 
											"steps left and two steps right, I still didn't end up back in the same place! ...Maybe I should listen to mom when she " + 
											"says not to play there...\""));

		NPC CAPTAIN = new NPC("Captain", 10, 10, 3, 4, 5, 8, 4, 10, 4, 'c', Entity.Race.HUMAN);
		
		Item FROG_AMULET = new Item("Frog Amulet", 0.2, 10).put(Stat.CHARM, 3);
		NPC FROG_PRINCESS = new NPC("Frog Princess", 1, 3, 2, 5, 1, 1, 1, 1, 1, 'f', Entity.Race.FROG);
		FROG_PRINCESS.setDialogue(new DialogueTree().add("\"ribbit.\"", (p,q) -> !p.has(FROG_AMULET))
						.add("\"Please contact my father right away!\"", (p,q) -> q.getOpinion() == NPC.Opinion.CURIOUS)
						.add("\"Have you come to change your mind about helping me? Please, I do not know what to do.\"", (p,q) -> q.getOpinion() == NPC.Opinion.FEARFUL)
						.add("\"Hello?\"", (p,q) -> p.has(FROG_AMULET))
						.add(new int[] {3,1}, "Hello.", "\"You can understand me? It must be that amulet you have, isn't it?\"")
						.add(new int[] {3,2}, "Leave.", "The frog looks... oddly sad?")
						.add(new int[] {3,0,1}, "I'm fluent in frog; the amulet is just for looks.", 
							"\"Please listen, the spirits of these woods cursed me to be a frog. My father lives in Taniere and he has not seen me for so long. " +
							"He must be so worried. Can you help me?\"")
						.add(new int[] {3,0,2}, "I suppose that would be it.", "\"Please help me. I'm a human, but I was cursed! My father lives in Taniere. He must be so worried.\"")
						.add(new int[] {3,0,0,1}, "Accept.", "\"Thank you! Please find him and tell him what happened!\"", (g) -> g.setInterOpinion(NPC.Opinion.CURIOUS))
						.add(new int[] {3,0,0,2}, "Decline.", "\"Oh...\"", (g) -> g.setInterOpinion(NPC.Opinion.FEARFUL))
						.add(new int[] {3,0,1,1}, "Accept.", "\"Thank you! Please find him and tell him what happened!\"", (g) -> g.setInterOpinion(NPC.Opinion.CURIOUS))
						.add(new int[] {3,0,1,2}, "Decline.", "\"Oh...\"", (g) -> g.setInterOpinion(NPC.Opinion.FEARFUL))
						.add(new int[] {2,1}, "Yes.", "\"Thank you! Please find him and tell him what happened!\"", (g) -> g.setInterOpinion(NPC.Opinion.CURIOUS))
						.add(new int[] {2,2}, "No.", "\"Oh...\"", (g) -> g.setInterOpinion(NPC.Opinion.FEARFUL))
						.add("\"ribbit.\""));

		MATHIEU.addRelationship(MAIA);
		MATHIEU.addRelationship(ELE);
		MATHIEU.addRelationship(OLIVER);
		MAIA.addRelationship(OLIVER);
		MAIA.addRelationship(ELE);
		OLIVER.addRelationship(ELE);
		CAPTAIN.addRelationship(FROG_PRINCESS);

		//System.out.println("Generating maps...");//-------------------------------------------------------------------------------------------------
		int[][] wwTopo = { 
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
							};
						
		char[][] wwFeat = {
									{'^', '^', '^', '^', '^', '^', '^', '^', ';', ';', 'T', ';', ';', '~', '@', '~'},
									{'^', '^', '^', '^', '^', '^', '^', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{'^', '^', '^', ';', '^', ';', ';', ';', ';', 'T', ';', ';', ';', '~', '~', '~'},
									{'^', '^', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '@', '~', '~'},
									{'^', '^', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '~', '~'},
									{'^', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '@'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '@', '~'},
									{';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', '@', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '@', '~'},
								};

		int[][] cavTopo = {
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1}
								};

		char[][] cavFeat = {
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'}
								};

		int[][] twnTopo = { 
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
								};
						
		char[][] twnFeat = {
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', 'T', ';', ';', ';', ';', '#', '#', '#', ';', ';', '~', '~', '~'},
									{';', ';', '#', '#', '#', ';', ';', ';', '/', ':', '#', ';', ';', '~', '~', '~'},
									{';', ';', '#', ':', '#', ';', ';', ';', '#', '#', '#', ';', 'T', '~', '~', '~'},
									{';', ';', '#', ':', '/', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', 'T', '#', ':', '#', ';', ';', ';', '#', '#', '#', ';', 'T', '~', '~', '~'},
									{';', ';', '#', '#', '#', ';', ';', ';', '/', ':', '#', ';', ';', '~', '~', '~'},
									{';', ';', 'T', ';', ';', ';', ';', ';', '#', '#', '#', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', '#', '/', '#', 'T', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', 'T', '#', ':', '#', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', '#', '#', '#', ';', ';', 'T', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
								};
		
		int[][] tnoTopo = { 
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   0,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   0,   -99,  -99,   -99,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   0,   -99,  -99, -99,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      0,   0, -99, -99,  1, -99,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   0,     -99, -99, -99,  1,  1,   1,  -99, -99, -99, -99},
									{1, 1,  1,   1,   1,   1,      1,   1,   1,   1,  1,   1,  -99, -99, -99, -99},//dock
									{1, 1,  1,   1,   1,   1,      1,   1,   1,   1,  1,   1,  -99, -99, -99, -99},//dock
									{1, 1, -99, -99, -99, -99,   -99, -99, -99, -99, -99, -99, -99, -99, -99, -99},
									{1, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99},
								};
		
		char[][] tnoFeat = {
									{'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', 'T', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '~', '~', '~', '~', '~', '~'},
									{';', ';', 'T', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '^', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', 'T', '~', '~', '~', '~', '/', '*', '\\', '~', '~', '~', '~'},
									{';', ';', ';', ';', '=', '=', '=', '=', '=', '[', '*', ']', '~', '~', '~', '~'},
									{';', ';', ';', '=', '=', '=', '=', '=', '=', '[', '*', ']', '~', '~', '~', '~'},
									{';', 'T', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
									{';', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
								};
		
		int[][] tanTopo = {
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, 1, 1, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, 1, 1, 1, 1,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},//dock
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},//dock
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, 1, 1, 1, 1,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99, -99, },
						};
		
		char[][] tanFeat = { 
									{'#', '#', '#', '#', 'T', ';', ';', 'T', ';', ';', ';', ';', '~', '~', '~', '~',},
									{'#', ':', ':', '#', ';', ';', 'T', ';', ';', ';', ';', '~', '~', '~', '~', '~',},
									{'#', ':', ':', '\\', '=', '=', '=', ';', ';', ';', '~', '~', '~', '~', '~', '~',},
									{'#', ':', ':', '/', '=', '=', '=', ';', ';', ';', '~', '~', '~', '~', '~', '~',},
									{'#', ':', ':', '#', ';', '=', '=', ';', ';', ';', '~', '~', '~', '~', '~', '~',},
									{'#', '#', '#', '#', ';', '=', '=', ';', 'T', ';', '~', '~', '~', '^', '~', '~',},
									{'#', ':', ':', '#', ';', '=', '=', ';', ';', '~', '~', '~', '/', '*', '\\', '~',},
									{'#', ':', ':', '/', ';', '=', '=', ';', '~', '~', '~', '/', '*', '*', '*', '\\',},
									{'#', '#', '#', '#', ';', '=', '=', '=', '=', '=', '=', '[', '>', '>', '>', ']',},
									{'T', ';', ';', ';', ';', '=', '=', '=', '=', '=', '=', '[', '>', '>', '>', ']', },
									{';', ';', 'T', ';', ';', '=', '=', ';', '~', '~', '~', '[', '_', '_', '_', ']',},
									{';', ';', ';', ';', ';', '=', '=', ';', ';', '~', '~', '~', '~', '~', '~', '~',},
									{'T', ';', ';', 'T', '#', '/', '\\', '#', ';', '~', '~', '~', '~', '~', '~', '~',},
									{';', ';', ';', ';', '#', ':', ':', '#', ';', '~', '~', '~', '~', '~', '~', '~',},
									{';', 'T', ';', ';', '#', ':', ':', '#', '~', '~', '~', '~', '~', '~', '~', '~',},
									{';', ';', ';', 'T', '#', '#', '#', '#', '~', '~', '~', '~', '~', '~', '~', '~', },
						 		 };
						
		int[][] shipTopo = {
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
								};

		char[][] shipFeat = {
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
								};

		//System.out.println("Writing descriptions...");//--------------------------------------------------------------------------------
		String wwDesc = "You find yourself surrounded by forest, lightly illuminated by the full moon overhead.\n" +
		"A light breeze flows between the trees and almost sounds like hushed voices.\n" +
		"Despite the light from the moon, the entire forest looks dull. The trees' hue are desaturated and the whole area feels devoid of life.\n";

		String caveDesc = "The cold, dank air clings to your skin within the dark cave.\nTo the left and right are entrances that lead deeper into the earth,\nYou can hear " +
								"water dripping onto the stone floor.\n";

		String twnDesc = "A little town on the outskirts of a forest.\n" +
						"The sound of rushing water from a river to the East can be heard, but otherwise it is deadly silent.\n" +
						"It seems oddly devoid of life.\n";

		String tnoDesc = "You stand on a small bit of wild peninsula, near the sea.\n" +
						"In the distance you can see an old ship floating in the bay.\n" +
						"The ship's sails are tattered and it seems to be in a state of disrepair.\n" +
						"Further to the south you can hear the light bustle of voices and song.\n";

		String tanDesc = "The salty win blows through a port town on the shore.\n" +
						"Much more lively than the little village to the North.\n" +
						"You can hear music and chatter. The people here seem to be thriving.\n";

		String shipDesc = "The floorboards creek as you step into the cabin of the ship.\n" +
								"The air is damp and smells of mildew and alcohol.\n";

		//System.out.println("Finalizing maps...");//-------------------------------------------------------------------------------
		WW =  new Map("whispering woods", wwDesc, wwTopo, wwFeat, 16, 16, 2);
		CAVE_1 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_2 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_3 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_4 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_5 = new Map("cave", "Congrats! You solved it, connor :)", cavTopo, cavFeat, 5, 5, 2);
		TWN = new Map("town", twnDesc, twnTopo, twnFeat, 16, 16, 1);
		TNO = new Map("wilderness", tnoDesc, tnoTopo, tnoFeat, 16, 16, 3);
		TAN = new Map("taniere", tanDesc, tanTopo, tanFeat, 16, 16, 2);
		SHIP = new Map("ship", shipDesc, shipTopo, shipFeat, 5, 5, 2);

		//System.out.println("Spawning Entities...");//--------------------------------------------------------------------------------------
		WW.spawnEntity(FROG_PRINCESS, 0, 14);

		NPC FROG_KING = new NPC("Frog King", 10, 10, 2, 5, 1, 1, 1, 1, 1, 'f', Entity.Race.FROG);
		FROG_KING.setDialogue(new DialogueTree().add("The fat frog shifts backwards slightly, revealing an amulet hidden under its stomach.")
				  .add(new int[] {0,1}, "Take the amulet.", "The frog hops away.", (g) -> {CAVE_5.spawnEntity(null, 0, 2); return g.givePlayer(FROG_AMULET);})
				  .add(new int[] {0,2}, "Leave it.", "The frog tilts its head and stares at you in confusion, before moving to conceal the amulet once again."));

		CAVE_5.spawnEntity(FROG_KING, 0, 2);

		TWN.spawnEntity(ELE, 5, 3);
		TWN.spawnEntity(MAIA, 7, 3);
		TWN.spawnEntity(OLIVER, 6, 7);
		TWN.spawnEntity(MATHIEU, 11, 6);

		TAN.spawnEntity(new Pirate(3), 6, 6);
		TAN.spawnEntity(new Pirate(2), 1, 1);
		TAN.spawnEntity(new Pirate(2), 3, 1);
		TAN.spawnEntity(new Pirate(3), 4, 2);
		TAN.spawnEntity(new Pirate(2), 6, 2);
		TAN.spawnEntity(new Pirate(3), 14, 5);

		SHIP.spawnEntity(CAPTAIN, 2, 4);
		CAPTAIN.setDialogue(new DialogueTree()
				.add("\"Yar, lad...\"", (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == NPC.Opinion.FRIENDLY)
				.add("The large man doesn't even meet your eyes as you approach. Instead, he simply stares at a chain he holds between " +
				"his fat fingers. He reeks of booze.", (p,q) -> p.getStat(Entity.Stat.PERC) >= 6)
				.add("The large man doesn't even acknowledge you. He reeks of booze.", (p,q) -> p.getStat(Entity.Stat.PERC) >= 3)
				.add("The large man doesn't even acknowledge you.")
				.add(new int[] {0,1}, "Leave.", "\"See ya then, lad...\"")
				.add(new int[] {0,2}, "There is a frog in the whispering woods that claims to be your daughter, transformed by a curse.",
						"\"L-lad? That be true? Yer not pulling on me leg?\" He appears to be brought to tears, \"Blast it, I'll go there myself! I need to see for me self!\"",
						(p,q) -> q.getOpinion() == NPC.Opinion.CURIOUS, (g) -> {SHIP.spawnEntity(null, 2, 4);return "The man storms out of the ship cabin, floorboards " +
							"creaking and even rocking the ship slightly as he stomps away.";})
				.add(new int[] {1,1}, "Leave.", "Just as he didn't notice you approach, he doesn't notice you leaving.")
				.add(new int[] {1,2}, "There is a frog in the whispering woods that claims to be your daughter, transformed by a curse.",
						"\"L-lad? That be true? Yer not pulling on me leg?\" He appears to be brought to tears, \"Blast it, I'll go there myself! I need to see for me self!\"",
						(p,q) -> q.getOpinion() == NPC.Opinion.CURIOUS, (g) -> {SHIP.spawnEntity(null, 2, 4);return "The man storms out of the ship cabin, floorboards " +
							"creaking and even rocking the ship slightly as he stomps away.";})
				.add(new int[] {2,1}, "Leave.", "Just as he didn't notice you approach, he doesn't notice you leaving.")
				.add(new int[] {2,2}, "There is a frog in the whispering woods that claims to be your daughter, transformed by a curse.",
						"\"L-lad? That be true? Yer not pulling on me leg?\" He appears to be brought to tears, \"Blast it, I'll go there myself! I need to see for me self!\"",
						(p,q) -> q.getOpinion() == NPC.Opinion.CURIOUS, (g) -> {SHIP.spawnEntity(null, 2, 4);return "The man storms out of the ship cabin, floorboards " +
							"creaking and even rocking the ship slightly as he stomps away.";})
				.add(new int[] {3,1}, "Leave.", "Just as he didn't notice you approach, he doesn't notice you leaving.")
				.add(new int[] {3,2}, "There is a frog in the whispering woods that claims to be your daughter, transformed by a curse.",
						"\"L-lad? That be true? Yer not pulling on me leg?\" He appears to be brought to tears, \"Blast it, I'll go there myself! I need to see for me self!\"",
						(p,q) -> q.getOpinion() == NPC.Opinion.CURIOUS, (g) -> {SHIP.spawnEntity(null, 2, 4);return "The man storms out of the ship cabin, floorboards " +
							"creaking and even rocking the ship slightly as he stomps away.";}));


		//System.out.println("Adding links...");//-------------------------------------------------------------------------------

		for (int i = 0; i < 16; i++) {
			WW.addLink(TWN, 15, (i%13), 1, (i%13));
			TWN.addLink(WW, 0, (i%13), 14, (i%13));
			TWN.addLink(TNO, 15, (i%10), 1, (i%10));
			TNO.addLink(TWN, 0, (i%10), 14, (i%10));
		}
		WW.addLink(CAVE_1, 2, 3, 2, 2);
		CAVE_1.addLink(CAVE_2, 2, 0, 2, 2);
		CAVE_1.addLink(CAVE_1, 2, 4, 2, 2);
		CAVE_2.addLink(CAVE_3, 2, 0, 2, 2);
		CAVE_2.addLink(CAVE_1, 2, 4, 2, 2);
		CAVE_3.addLink(CAVE_4, 2, 4, 2, 2);
		CAVE_3.addLink(CAVE_1, 2, 0, 2, 2);
		CAVE_4.addLink(CAVE_5, 2, 4, 2, 2);
		CAVE_4.addLink(CAVE_1, 2, 0, 2, 2);
		CAVE_1.addLink(WW, 4, 2, 2, 3);
		CAVE_5.addLink(WW, 4, 2, 2, 3);
		TNO.addLink(TAN, 15, 0, 0, 10); 
		TAN.addLink(TNO, 0, 11, 14, 0);
		TAN.addLink(SHIP, 8, 13, 2, 0);
		SHIP.addLink(TAN, 2, 2, 8, 12);
		try {
			System.out.println("Building game...");
			new GameState.GameData(MAIN_MENU, WW, 6, 6).save("global/EverDark");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
