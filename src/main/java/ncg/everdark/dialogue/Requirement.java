package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.NPC;

import java.io.Serializable;
import java.util.function.BiPredicate;

public interface Requirement extends BiPredicate<Player,NPC>, Serializable {
	public boolean test(Player p, NPC q);
}
