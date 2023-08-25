package ncg.everdark.entities;

import ncg.everdark.ui.CFG;
import ncg.everdark.ui.CFG.Colour;
import ncg.everdark.items.Item;
import ncg.everdark.items.Bodypart;
import ncg.everdark.events.Event;
import ncg.everdark.events.EndOfDialogueEvent;

import java.util.Map;
import java.util.EnumMap;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class Entity implements Serializable{

	private String name;
	private List<Item> inv;
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

		inv.addAll(Bodypart.getBody(65, race));
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
		output.append("\n\n");
		for (Item item : inv.subList(0, NUM_PARTS)) {
			output.append(item + "\n");
		}
		return output.toString();
	}

	public String stuff() {
		StringBuilder output = new StringBuilder("Carrying:\n");
		for (Item item : inv) {
			String locked = (item.IS_LOCKED) ? CFG.colour(" (locked)", Colour.RED) : "";
			if (!item.IS_HIDDEN) {
				output.append(item + locked + "\n");
			}
		}

		DecimalFormat df = new DecimalFormat("0.00");
		String carryingWeight = df.format(getWeight(NUM_PARTS, inv.size()));
		String invValue = df.format(getInvValue());	

		output.append("\nWeight: " + carryingWeight + "kg, Value: " + invValue + "g");

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
		for (Item item : inv) {
			output += item.getBuff(stat);
		}
		return output;
	}

	private double getWeight(int a, int b) {
		double output = 0;
		for (Item item : inv.subList(a, b)) {
			output += item.WEIGHT;	
		}
		return output;
	}

	private double getInvValue() {
		double output = 0;
		for (Item item : inv) {
			output += item.VALUE;
		}
		return output;
	}

	public double getClimbing() {
		double bodyweight = getWeight(0, NUM_PARTS);
		double carriedweight = getWeight(NUM_PARTS, inv.size());
		double strengthFactor = Math.sqrt(this.getStat(Stat.STR) * this.getStat(Stat.ENDUR));
		return (bodyweight * strengthFactor) / (bodyweight + carriedweight);
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

		OTHER(Arrays.asList(new Bodypart("head", 1))),

		HUMAN((Arrays.asList(new Bodypart("head", 0.0723),
									new Bodypart("left arm", 0.04335), new Bodypart("right arm", 0.04335),
									new Bodypart("left leg", 0.16555), new Bodypart("right leg", 0.16555),
									new Bodypart("torso", 0.5099)))),

		FROG(Arrays.asList(new Bodypart("head", 0.2667), new Bodypart ("torso", 0.5333),
								 new Bodypart("front left leg", 0.04), new Bodypart("front right leg", 0.04),
								 new Bodypart("hind left leg", 0.06), new Bodypart("hind right leg", 0.06)));

		public final List<Bodypart> PARTS;

		Race(List<Bodypart> parts) {
			PARTS = parts;
		}
	}
	
}
