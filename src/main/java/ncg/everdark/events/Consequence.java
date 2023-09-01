package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;

import java.io.Serializable;
import java.util.function.Function;

public interface Consequence extends Function<GameState,String>, Serializable {
	public String apply(GameState state);
	
	default Consequence and(Consequence other) {
		return (g) -> this.apply(g) + other.apply(g);
	}
}
