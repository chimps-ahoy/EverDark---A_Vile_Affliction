package ncg.everdark.items;

import ncg.everdark.entities.Entity.Race;

import java.util.List;
import java.util.ArrayList;

public class Bodypart extends Item {

	private int hp;

	public Bodypart(String name, double weight) {
		super(name, weight, 0, Item.LOCKED, Item.HIDDEN);
		this.hp = 100;
	}

	public Status status() {
		Status output = Status.GOOD;
		for (Status s : Status.values()) {
			if (this.hp >= s.THRESHOLD) {
				output = s;
				break;
			}
		}	
		return output;
	}

	public static List<Bodypart> get(double weight, Race race) {
		List<Bodypart> parts = new ArrayList<Bodypart>(race.PARTS.length);
		for (Bodypart part : race.PARTS) {
			parts.add(get(weight, part));
		}	
		return parts;
	}

	private static Bodypart get(double weight, Bodypart proto) {
		return new Bodypart(proto.NAME, weight*proto.WEIGHT);
	}
	public enum Status {
		GOOD(100),
		FINE(75),
		INJURED(50),
		BROKEN(25),
		MISSING(0);
		
		public final int THRESHOLD;

		Status(int i) {
			this.THRESHOLD = i;
		}
	}

	public static final Bodypart LEG = new Bodypart("leg", 16.8);//weights in the prototypes represent PRECENTAGES
	public static final Bodypart ARM = new Bodypart("arm", 5.7);//TODO: percent depends on race?
	public static final Bodypart HEAD = new Bodypart("head", 8.26);
	public static final Bodypart TORSO = new Bodypart("torso", 55.1);

}
