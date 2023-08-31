package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.Entity.Stat;
import ncg.everdark.entities.NPC;

public class StatRequirement implements Requirement {
	
	private Stat stat;
	private int threshold;
	private int plusOrMinus;
	
	public StatRequirement(Stat stat, int threshold, int plusOrMinus) {
		this.stat = stat;
		this.threshold = threshold;
		this.plusOrMinus = plusOrMinus;
	}
	
	public boolean test(Player p, NPC q) {
		return (plusOrMinus * (p.getStat(stat) - threshold) >= 0);
	}
	
}