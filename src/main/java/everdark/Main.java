package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Main {
	
	public static Entity player;
	public static Map ww;
	public static Map m;
	public static GameState g;
	
	public static void main(String[] args) {

		//This is just so I always have a test file to load from.
		//IMPORTANT: similar idea could be applied to the GameState info for a New Game at startup. Instead of relying on the user having the files from the inital download, we can simply generate them in
		//iniGameData() whenever New Game is selected.
		//the only BIG thing to remember is to keep the file names different so we don't overwrite their old save.
		Player joe = null;
		joe = new Player("joe", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 'a', 1);
		try {
			joe.saveToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		
		//unimportant test stuff - NOTE: maps will still be generated at startup when the player selects to start a new game/if the files arent present?
		int[][] topography = new int[16][16];
		Entity[][] entities = new Entity[16][16];
		char[][] features = new char[16][16];
		int count = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				features[i][j] = (count%7==0) ? 'T' : ';';
				topography[i][j] = (i == 15 || j == 15 || i == 0 || j == 0) ? 0 : 2;
				count++;
			}
		}
		
		//important permanent-ish stuff
		String wwDesc1 = "You find yourself surrounded by forest, lightly illuminated by the full moon overhead.\n";
		String wwDesc2 = "A light breeze flows between the trees and almost sounds like hushed voices.\n";
		String wwDesc3 = "Despite the light from the moon, the entire forest looks dull. The trees' hue are desaturated and the whole area feels devoid of life.\n";
		m = new Map(0, "main", "", "", "", null, null, null, 0, 0);
		ww =  new Map(1, "whispering woods", wwDesc1, wwDesc2, wwDesc3, topography, features, entities, 16, 16);
		g = new GameState(m);
	}
	
	public static void playerIni() {
		Scanner in = new Scanner(System.in);
		System.out.print("Would you like to load from file? (Y/N): ");
		try { 
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
		} catch (Exception e) {
			System.out.println("An unexpected error has occurred.");
			playerIni();
		}
	}

	public static String drawMenu() {
		return "_______________________________________________________\n" + "|                        EverDark                     |\n" +
				"|                 ---A Vile Affliction---             |\n" + "|____________________________________________v0.0.5.1_|\n";
	}
}
