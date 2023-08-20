package ncg.everdark.items;

import ncg.everdark.entities.Entity.Stat;

import java.util.Map;
import java.util.EnumMap;
import java.io.Serializable;

public class Item implements Serializable {

	public static final boolean LOCKED = true;
	public static final boolean HIDDEN = true;

	public final String NAME;
	public final double WEIGHT;
	public final double VALUE;
	private Map<Stat,Integer> buffs;
	
	public final boolean IS_LOCKED;
	public final boolean IS_HIDDEN;

	private static int count = 0;
	private final int ID;

	public Item(String NAME, double WEIGHT, double VALUE, Map<Stat,Integer> buffs) {
		this.NAME = NAME;
		this.WEIGHT = WEIGHT;
		this.VALUE = VALUE;
		this.buffs = new EnumMap<Stat,Integer>(buffs);
		this.IS_LOCKED = false;
		this.IS_HIDDEN = false;
		this.ID = count++;
	}

	public Item(String NAME, double WEIGHT, double VALUE) {
		this.NAME = NAME;
		this.WEIGHT = WEIGHT;
		this.VALUE = VALUE;
		this.buffs = new EnumMap<Stat,Integer>(Stat.class);
		this.IS_LOCKED = false;
		this.IS_HIDDEN = false;
		for (Stat stat : Stat.values()) {
			buffs.put(stat,0);
		}
		this.ID = count++;
	}

	public Item(String NAME, double WEIGHT, double VALUE, boolean... flags) {
		this.NAME = NAME;
		this.WEIGHT = WEIGHT;
		this.VALUE = VALUE;
		this.buffs = new EnumMap<Stat,Integer>(Stat.class);
		this.IS_LOCKED = flags[0];
		this.IS_HIDDEN = flags[1];
		for (Stat stat : Stat.values()) {
			buffs.put(stat,0);
		}
		this.ID = count++;
	}

	public Item(Item i) {//copy
		this.NAME = i.NAME;
		this.WEIGHT = i.WEIGHT;
		this.VALUE = i.VALUE;
		this.buffs = new EnumMap<Stat,Integer>(i.buffs);
		this.IS_LOCKED = i.IS_LOCKED;
		this.IS_HIDDEN = i.IS_HIDDEN;
		this.ID = i.ID;
	}

	public Item put(Stat s, int i) {
		buffs.put(s,i);
		return this;
	}

	public int getBuff(Stat stat) {
		return buffs.get(stat);
	}

	public String toString() {
		return this.NAME + " - " + this.WEIGHT + "kg - " + this.VALUE + "g";
	}

	//TODO: figure out how going to handle different items and stuff. probably just do it the way its done here, but it can be Thought About
	public static final Item TEST = new Item("test", 1.0, 2.0).put(Stat.STR,10);

}
