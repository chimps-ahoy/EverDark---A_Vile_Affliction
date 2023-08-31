package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.NPC;
import ncg.everdark.entities.NPC.Opinion;

public class OpinionRequirement implements Requirement {
	
	private Opinion requiredOpinion;
	
	public OpinionRequirement(Opinion requiredOpinion) {
		this.requiredOpinion = requiredOpinion;
	}
	
	public boolean test(Player p, NPC q) {
		return (q.getOpinion() == requiredOpinion);
	}
	
}