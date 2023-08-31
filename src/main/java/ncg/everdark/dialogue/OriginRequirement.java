package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.NPC;
import ncg.everdark.entities.Player.Origin;

public class OriginRequirement implements Requirement {
	
	private Origin requiredOrigin;
	
	public OriginRequirement(Origin requiredOrigin) {
		this.requiredOrigin = requiredOrigin;
	}
	
	public boolean test(Player p, NPC q) {
		return p.getOrigin() == requiredOrigin;
	}
	
}