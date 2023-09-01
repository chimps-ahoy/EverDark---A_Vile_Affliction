package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.entities.NPC;
import ncg.everdark.entities.NPC.Opinion;

public class OpinionChange implements Consequence {
	
	private NPC inter;
	private Opinion opinion;
	
	public OpinionChange(NPC inter, Opinion opinion) {
		this.inter = inter;
		this.opinion = opinion;
	}
	
	public String apply(GameState state) {
		inter.setOpinion(opinion);
		return "\n(What you said has changed their thoughts about you, and the thoughts of those they know.)";
	}
	
}