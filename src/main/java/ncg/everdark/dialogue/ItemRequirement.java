package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.NPC;
import ncg.everdark.items.Item;

public class ItemRequirement implements Requirement {
	
	private Item requiredItem;
	private boolean negation;
	
	public ItemRequirement(Item requiredItem) {
		this.requiredItem = requiredItem;
		this.negation = true;
	}
	
	public ItemRequirement(Item requiredItem, boolean negation) {
		this.requiredItem = requiredItem;
		this.negation = negation;
	}
	
	public boolean test(Player p, NPC q) {
		return !(negation ^ p.has(requiredItem));
	}
	
}