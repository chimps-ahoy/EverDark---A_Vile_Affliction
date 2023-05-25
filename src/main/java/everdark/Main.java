package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Main {
	
	public static int[][] topography;
	public static Entity[][] entities;
	public static char[][] features;
	public static int count;
	public static Entity player;
	public static Map ww;
	public static Map m;
	public static GameState g;
	
	public static void main(String[] args) {

		//menu drawing
		System.out.print(drawMenu());

		//ini 
		//TODO: replace with file IO 
		iniGameData();
		g.addLocation(ww);

		//player ini
		playerIni();
		ww.spawnPlayer(player, 6, 6);
		g.changeLocation(ww);
		
		//game runtime
		InputHandler ioHandler = new InputHandler(g);
		Scanner in = new Scanner(System.in);
		while (ioHandler.acceptingInput()) {
			System.out.println(ioHandler.handle(in.nextLine()));
		}
		System.exit(0);
	}
	
	public static void iniGameData() {
		
		//unimportant test stuff
		topography = new int[16][16];
		entities = new Entity[16][16];
		features = new char[16][16];
		count = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				features[i][j] = (count%7==0) ? 'T' : ';';
				topography[i][j] = (i == 15 || j == 15 || i == 0 || j == 0) ? 0 : 1;
				count++;
			}
		}
		
		//important permanent-ish stuff
		Entity player = null;
		ww = null;
		m = null;
		try {
			m = new Map(0, "main", null, null, null, 0, 0);
			ww =  new Map(1, "whispering woods", topography, features, entities, 16, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		g = new GameState(m);
	}
	
	public static void playerIni() {
		Scanner in = new Scanner(System.in);
		System.out.print("Would you like to load from file? (Y/N): ");
		if (in.nextLine().toLowerCase().charAt(0)=='y') {
			boolean found = false;
			while (!found) {
				System.out.print("\nPlease specify the name of the character: ");
				try {
					player = Player.loadFromFile(in.nextLine() + ".ed");
					found = true;
				} catch (Exception e) {
					System.out.print("The character file could not be found. Try again.");
				}	
			}
			System.out.println();
		} else {
			player = Player.characterCreation();
		}
	}

	public static String drawMenu() {
		return "_______________________________________________________\n" + "|                        EverDark                     |\n" +
				"|                 ---A Vile Affliction---             |\n" + "|______________________________________________v0.0.5_|\n";
	}
}
