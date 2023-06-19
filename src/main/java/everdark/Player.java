package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Player extends Entity {

	private static final int MAX_HP = 100;

	public Player(String name, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, int id) {
		super(name, MAX_HP, MAX_HP, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, id);
	}

	public static Player loadFromFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream(Config.SAVE_PATH + path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Player loaded = (Player)(ois.readObject());
		ois.close();
		fis.close();
		return loaded;

	}

	public void saveToFile() throws FileNotFoundException, IOException {

		String fileName = Config.SAVE_PATH + super.getName() + ".ed";
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
		fos.close();	

	}

	//public String talk() [or whatever I call it], maybe the player character can become saved to the world after they start a new game, and their actions from their initial playthrough
	//influence the now-NPC's dialogue? This is a FAR off pipedream, I'd say think about this LAST, maybe for v1.2 or wtv yk

	public String toString() {
		return ConsoleColours.YELLOW_BOLD + super.toString() + ConsoleColours.RESET;
	}

}
