package ncg.everdark.items;

import ncg.everdark.ui.CFG;
import ncg.everdark.ui.CFG.Colour;
import ncg.everdark.entities.Entity.Race;

import java.util.List;
import java.util.ArrayList;

public class Bodypart extends Item {

	private int hp;

	public Bodypart(String name, double weight) {
		super(name, weight, 0, Item.LOCKED, Item.HIDDEN);
		this.hp = 100;
	}

	public String toString() {
		return this.NAME + " - " + this.status();
	}

	private Status status() {
		Status output = Status.GOOD;
		for (Status s : Status.values()) {
			if (this.hp >= s.THRESHOLD) {
				output = s;
				break;
			}
		}	
		return output;
	}

	public static List<Bodypart> getBody(double weight, Race race) {
		List<Bodypart> parts = new ArrayList<Bodypart>(race.PARTS.size());
		for (Bodypart part : race.PARTS) {
			parts.add(get(weight, part));
		}	
		return parts;
	}

	private static Bodypart get(double weight, Bodypart proto) {
		return new Bodypart(proto.NAME, weight*proto.WEIGHT);
	}

	public enum Status {
		GOOD(100, Colour.GREEN),
		FINE(75, Colour.WHITE),
		INJURED(50, Colour.BRIGHT_YELLOW),
		BROKEN(25, Colour.RED),
		MISSING(0, Colour.GRAY);
		
		public final int THRESHOLD;
		public final Colour COLOUR;

		Status(int threshold, Colour colour) {
			this.THRESHOLD = threshold;
			this.COLOUR = colour;
		}

		public String toString() {
			return CFG.colour(this.name(), this.COLOUR);
		}
	}

}
