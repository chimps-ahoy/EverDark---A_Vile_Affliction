package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.gamedata.Map;
import ncg.everdark.entities.Entity;

public class SpawnEntity implements Consequence {
	
	private Map location;
	private Entity entity;
	private int r;
	private int c;
	
	public SpawnEntity(Map location, Entity entity, int r, int c) {
		this.location = location;
		this.entity = entity;
		this.r = r;
		this.c = c;
	}
	
	public String apply(GameState state) {
		location.spawnEntity(entity, r, c);
		return "";
	}
	
}