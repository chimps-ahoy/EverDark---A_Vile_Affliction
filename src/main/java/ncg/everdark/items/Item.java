package ncg.everdark.items;

import ncg.everdark.entities.Entity.Stat;

import java.util.Map;
import java.util.EnumMap;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Item implements Serializable {

	public static final boolean LOCKED = true;
	public static final boolean HIDDEN = true;
	private static int count = 0;

	public final String NAME;
	public final double WEIGHT;
	public final double VALUE;
	public final int ID;
	private Map<Stat,Integer> buffs;
	
	public final boolean IS_LOCKED;
	public final boolean IS_HIDDEN;

	public Item(String NAME, double WEIGHT, double VALUE, Map<Stat,Integer> buffs) {
		this.NAME = NAME;
		this.WEIGHT = WEIGHT;
		this.VALUE = VALUE;
		this.buffs = new EnumMap<Stat,Integer>(buffs);
		this.IS_LOCKED = false;
		this.IS_HIDDEN = false;
		ID = count++;
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
		ID = count++;
	}

	public Item(String NAME, double WEIGHT, double VALUE, boolean... flags) {
		this.NAME = NAME;
		this.WEIGHT = WEIGHT;
		this.VALUE = VALUE;
		this.buffs = new EnumMap<Stat,Integer>(Stat.class);
		this.IS_LOCKED = flags[0];
		this.IS_HIDDEN = (flags.length > 1) ? flags[1] : false;
		for (Stat stat : Stat.values()) {
			buffs.put(stat,0);
		}
		ID = count++;
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

	public boolean equals(Object o) {
		Item i = (Item)(o);
		return (i != null && this.ID == i.ID);
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return this.NAME + " - " + df.format(this.WEIGHT) + "kg - " + df.format(this.VALUE) + "g";
	}

}
