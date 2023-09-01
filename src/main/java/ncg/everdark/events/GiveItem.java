package ncg.everdark.events;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.items.Item;

public class GiveItem implements Consequence {
	
	private Item item;
	private boolean remove;
	
	public GiveItem(Item item, boolean notRemove) {
		this.item = item;
		this.remove = !notRemove;
	}
	
	public GiveItem(Item item) {
		this.item = item;
		this.remove = false;
	}
	
	public String apply(GameState state) {
		String output = "";
		if (remove) {
			state.removeFromPlayer(item);
		} else {
			state.givePlayer(item);
			boolean startsWithVowel = ("AEIOUaeiou".indexOf(item.NAME.charAt(0)) >= 0);
			String article = (startsWithVowel) ? "an " : "a ";
			output = (item.IS_HIDDEN) ? "" : "\nYou got " + article + item.NAME + ".";
		}
		return output;
	}
}