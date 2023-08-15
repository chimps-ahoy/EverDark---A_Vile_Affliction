package ncg.everdark.items;

import ncg.everdark.entities.Entity.Stat;

import java.util.Map;
import java.util.EnumMap;
import java.io.Serializable;

public class Item implements Serializable {

	private String name;
	private double weight;
	private double value;
	private Map<Stat,Integer> buffs;

	private static int count = 0;
	private final int ID;

	public Item(String name, double weight, double value, Map<Stat,Integer> buffs) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.buffs = new EnumMap<Stat,Integer>(buffs);
		this.ID = count++;
	}

	public Item(String name, double weight, double value) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.buffs = new EnumMap<Stat,Integer>(Stat.class);
		for (Stat stat : Stat.values()) {
			buffs.put(stat,0);
		}
		this.ID = count++;
	}

	public Item(Item i) {
		this.name = i.name;
		this.weight = i.weight;
		this.value = i.value;
		this.buffs = new EnumMap<Stat,Integer>(i.buffs);
		this.ID = i.ID;
	}

	public Item put(Stat s, int i) {
		buffs.put(s,i);
		return this;
	}

	public int getBuff(Stat stat) {
		return buffs.get(stat);
	}
	//TODO: figure out how going to handle different items and stuff. probably just do it the way its done here, but it can be Thought About
	public static final Item TEST = new Item("test", 1.0, 2.0).put(Stat.STR,10);

}
