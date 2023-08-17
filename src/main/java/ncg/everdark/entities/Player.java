package ncg.everdark.entities;

import ncg.everdark.global.ConsoleColours;

public class Player extends Entity {

	private Origin origin;

	public Player(String name, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod) {
		super(name, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, Entity.Race.HUMAN);
		origin = Origin.UNDEFINED;
	}

	public enum Origin { UNDEFINED, UNKNOWN, FAIM, TANIERE, OTHER }

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	//public String talk() [or whatever I call it], maybe the player character can become saved to the world after they start a new game, and their actions from their initial playthrough
	//influence the now-NPC's dialogue? This is a FAR off pipedream, I'd say think about this LAST, maybe for v1.2 or wtv yk

	public String toString() {
		return ConsoleColours.YELLOW_BOLD + super.toString() + ConsoleColours.RESET;
	}

}
