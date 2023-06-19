package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Config {

	private static final int n = 5;
	private static String[] values = new String[n];
	public static final String MUSIC_PATH;
	public static final String SAVE_PATH;
	public static final int WIDTH;
	public static final int HEIGHT;
	public static final boolean ANSI;
	public static final String VERS = "v0.0.9";
	
	public static final Map MAIN_MENU = new Map(0, "main", "", null, null, null, 0, 0, 0);
	public static final Map WW;
	public static final Map TWN;

	static  {
		File f = null;
		Scanner lineScanner = null;
		try {
			f = new File("config.txt");
			lineScanner = new Scanner(f);
			for (int i = 0; i<n; i++) {
				Scanner valueGetter = new Scanner(lineScanner.nextLine());
				valueGetter.useDelimiter(": ");
				valueGetter.next();
				values[i] = valueGetter.next();
			}
		} catch (Exception e) {
			System.out.println("Game Data could not be initialized. Files may be missing or corrupt.");
			System.exit(1);
		}
		WIDTH = Integer.parseInt(values[0]);
		HEIGHT = Integer.parseInt(values[1]);
		SAVE_PATH = values[2];
		MUSIC_PATH = values[3];
		ANSI = (values[4].toLowerCase().charAt(0) == 'y');
		
		//unimportant test stuff - NOTE: maps will still be generated at startup when the player selects to start a new game/if the files arent present?
		int[][] wwTopo = new int[16][16];
		Entity[][] wwEnt = new Entity[16][16];
		char[][] wwFeat = new char[16][16];
		MapLink[][] wwLink = new MapLink[16][16];

		int[][] twnTopo = new int[16][16];
		Entity[][] twnEnt = new Entity[16][16];
		char[][] twnFeat = new char[16][16];
		MapLink[][] twnLink = new MapLink[16][16];

		int count = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				wwFeat[i][j] = (count%7==0) ? 'T' : ';';
				twnFeat[i][j] = ';';
				wwFeat[i][j] = (j >= 7 && j <= 9) ? '~' : wwFeat[i][j];
				wwTopo[i][j] = (i == 15 || j == 15 || i == 0 || j == 0) ? 0 : 1;
				count++;
			}
		}
		wwEnt[0][8] = new Frog(1);
		wwEnt[15][3] = new Human(1);
		
		//important permanent-ish stuff
		String wwDesc = "You find yourself surrounded by forest, lightly illuminated by the full moon overhead.\n" +
		"A light breeze flows between the trees and almost sounds like hushed voices.\n" +
		"Despite the light from the moon, the entire forest looks dull. The trees' hue are desaturated and the whole area feels devoid of life.\n";
		String twnDesc = "A town.";

		WW =  new Map(1, "whispering woods", wwDesc, wwTopo, wwFeat, wwEnt, 16, 16, 5);
		TWN = new Map(2, "town", twnDesc, twnTopo, twnFeat, twnEnt, 16, 16, 0);

		WW.addLink(TWN, 15, 15, 0, 0);
		TWN.addLink(WW, 0, 0, 15, 15);
	}

}
