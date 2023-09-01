package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.entities.NPC;

public class StageChange implements Consequence {
	
	private NPC inter;
	private int stage;
	
	public StageChange(NPC inter, int stage) {
		this.inter = inter;
		this.stage = stage;
	}
	
	public String apply(GameState state) {
		inter.setStage(stage);
		return "";
	}
	
}