package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.NPC;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface Effect extends BiConsumer<Player,NPC>, Serializable {
	public void accept(Player p, NPC q);
}
