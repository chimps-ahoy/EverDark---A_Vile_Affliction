package ncg.everdark.entities;

import ncg.everdark.ui.CFG;
import ncg.everdark.ui.CFG.Colour;
import ncg.everdark.events.Event;
import ncg.everdark.dialogue.DialogueTree;

import java.util.Deque;
import java.util.ArrayDeque;

public class NPC extends Entity {

	private Opinion opinion;	
	private int dialogueStage;//may be depreciated after dialogue rework, but is used in DialogueTree code so cannot be removed. Probably just gonna leave it in
									  //in the case that i find that one edge case that needs it
	private DialogueTree dialogue;
	private Deque<NPC> relationships;

	public enum Opinion { UNDEFINED, HOSTILE, FEARFUL, NEUTRAL, CURIOUS, FRIENDLY }

		public NPC(String name, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, Entity.Race race) {
		super(name, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, race);
		dialogueStage = 0;
		opinion = Opinion.UNDEFINED;
		dialogue = new DialogueTree();
		relationships = new ArrayDeque<NPC>();
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

	public void addRelationship(NPC npc) {
		if (!relationships.contains(npc)) {
			relationships.add(npc);
			npc.addRelationship(this);
		}
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
		for (NPC npc : relationships) {
			if (npc.opinion != opinion) {
				npc.setOpinion(opinion);
			}
		}
	}

	public void setDialogue(DialogueTree dialogue) {
		this.dialogue = dialogue;
	}

	public String toString() {
		return CFG.colour(super.toString(), Colour.GRAY);
	}

}
