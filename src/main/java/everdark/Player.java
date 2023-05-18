package ncg.chimpsahoy.everdark;
import java.io.*;

public class Player extends Entity {

	public Player(String name, int maxHp, int hp, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, int id) {
		super(name, maxHp, hp, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, id);
	}

	public static Player loadFromFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Player loaded = (Player)(ois.readObject());
		ois.close();
		fis.close();
		return loaded;

	}

	public void saveToFile() throws FileNotFoundException, IOException {

		String fileName = super.getName() + ".ed";
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
		fos.close();	

	}

	public static Player characterCreation() {
		return null;
	}



}
