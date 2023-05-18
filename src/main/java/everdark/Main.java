package ncg.chimpsahoy.everdark;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Player p = new Player("joe", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 'a', 0);
		Player b = null;
		try {
			//p.saveToFile();
			b = Player.loadFromFile("joe.ed");		
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Class not found");
			cnfe.printStackTrace();
		} catch (InvalidClassException ice) {
			System.out.println("Class is broken");
			ice.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Unknown error" + ioe.getMessage());
			ioe.printStackTrace();
		}
		System.out.println((b.equals(p)) ? "IDs are same and .equals works" : "IDs or .equals is broke");
		System.out.println(p);
		System.out.println(b);
	}

}
