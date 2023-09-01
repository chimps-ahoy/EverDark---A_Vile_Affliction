package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.entities.Player.Origin;

public class OriginChange implements Consequence {
	
	private Origin origin;
	
	public OriginChange(Origin origin) {
		this.origin = origin;
	}
	
	public String apply(GameState state) {
		state.setPlayerOrigin(origin);
		return "\n(What you said has set a fact about your life into stone.)";
	}
	
}