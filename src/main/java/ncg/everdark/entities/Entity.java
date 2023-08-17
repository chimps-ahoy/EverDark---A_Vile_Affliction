package ncg.everdark.entities;

import ncg.everdark.items.Item;
import ncg.everdark.items.Bodypart;
import ncg.everdark.global.ConsoleColours;
import ncg.everdark.events.Event;
import ncg.everdark.events.EndOfDialogueEvent;

import java.util.Map;
import java.util.EnumMap;
import java.util.Deque;
import java.util.LinkedList;
import java.io.Serializable;

public abstract class Entity implements Serializable{

	private String name;
	private Deque<Item> inv;
	private Map<Stat,Integer> stats;
	private final char APPEAR_MOD; //the appearance modifier. the basic char for their appearance before it is affected by stuff like starvation
	private final int NUM_PARTS;
	
	private static int count = 0;
	private final int ID;	

	public Entity(String name, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, Race race) {//once body parts are added
																																				//add an easy constructor for different creatures
		this.name = name;
		this.stats = new EnumMap<Stat,Integer>(Stat.class);
		this.inv = new LinkedList<Item>();

		inv.addAll(Bodypart.get(65, race));
		this.NUM_PARTS = inv.size();//IMPORTANT - any other items added at construction need to be AFTER NUM_PARTS is set.

		//inv.add(Item.TEST);

		stats.put(Stat.STR,str);
		stats.put(Stat.ENDUR,endur);
		stats.put(Stat.DEX,dex);
		stats.put(Stat.SWIFT,swift);
		stats.put(Stat.INT,iq);
		stats.put(Stat.WIL,wil);
		stats.put(Stat.CHARM,charm);
		stats.put(Stat.INTIM,intim);
		stats.put(Stat.PERC,perc);

		APPEAR_MOD = appearMod;
		ID = count++;
	} 

	public String beginDialogue(Player player) throws Event {
		throw new EndOfDialogueEvent("They don't have anything to say to you.");
	}

	public String talk(int response, Deque<Character> args, Player player) throws Event {
		return beginDialogue(player);
	}

	public boolean equals(Entity other) {
		return other != null && this.ID == other.ID;
	}

	public String toString() {
		char lilGuy = Character.toLowerCase(APPEAR_MOD);
		if (getStat(Stat.STR) + getStat(Stat.ENDUR) >= getStat(Stat.DEX) + getStat(Stat.SWIFT)) {
			lilGuy = Character.toUpperCase(APPEAR_MOD);
		}
		return "" + lilGuy;
	}

	public String stats() {
		StringBuilder output = new StringBuilder("Name: " + name + " - " + this);
		for (Stat s : stats.keySet()) {
			int buff = getItemBuffs(s);
			output.append("\n" + s + " - " + stats.get(s));
			if (buff > 0) {
				output.append(" (+" + buff + ")");
			} else if (buff < 0) {
				output.append(" (" + buff + ")");
			}
		}
		return output.toString();
	}

	public String getName() {
		return name;
	}
	
	public int getStat(Stat	stat) {
		int output = stats.get(stat);
		output += getItemBuffs(stat);	
		return output;
	}

	private int getItemBuffs(Stat stat) {
		int output = 0;
		for (Item i : inv) {
			output += i.getBuff(stat);
		}
		return output;
	}

	public int getClimbing() {
		return (int)(Math.sqrt(this.getStat(Stat.STR) * this.getStat(Stat.ENDUR)));
	}

	public enum Stat {STR, ENDUR, DEX, SWIFT, INT, WIL, CHARM, INTIM, PERC;
		public String toString() {
			String output = "";
			switch (this) {
				case STR:
					output = "Strength";
					break;
				case ENDUR:
					output = "Endurance";
					break;
				case DEX:
					output = "Dexterity";
					break;
				case SWIFT:
					output = "Swiftness";
					break;
				case INT:
					output = "Intelligence";
					break;
				case WIL:
					output = "Willpower";
					break;
				case CHARM:
					output = "Charisma";
					break;
				case INTIM:
					output = "Intimidation";
					break;
				case PERC:
					output = "Perception";
					break;
				default:
					output = this.name();
					break;
			}
			return output;
		}
	}

	public enum Race {//TODO: precentages depend on race, etc
		OTHER(new Bodypart[] {Bodypart.HEAD}),
		HUMAN(new Bodypart[] {Bodypart.HEAD, Bodypart.ARM, Bodypart.ARM, Bodypart.LEG, Bodypart.LEG, Bodypart.TORSO}),
		FROG(new Bodypart[] {Bodypart.HEAD, Bodypart.LEG, Bodypart.LEG, Bodypart.LEG, Bodypart.LEG, Bodypart.TORSO});

		public final Bodypart[] PARTS;

		Race(Bodypart[] parts) {
			PARTS = parts;
		}
	}
	
}
