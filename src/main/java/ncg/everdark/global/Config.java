package ncg.everdark.global;

import ncg.everdark.dialogue.DialogueTree;
import ncg.everdark.events.Consequence;
import ncg.everdark.gamedata.*;
import ncg.everdark.entities.*;

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
	public static final String VERS = "v0.1.0";
	
	public static final Map MAIN_MENU = new Map("main", "Would you like to load from file? (Y/N): ", null, null, null, 0, 0, 0);
	public static final Map WW;
	public static final Map TWN;
	public static final Map TNO;
	public static final Map TAN;
	public static final Map SHIP;

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
		
		System.out.println("Generating maps...");//-------------------------------------------------------------------------------------------------
		int[][] wwTopo = { 
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
							};
						
		char[][] wwFeat = {
									{'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', '~', '@', '~'},
									{';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', '@', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '@'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '@', '~'},
									{';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', '@', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '@', '~'},
								};
						
		int[][] twnTopo = { 
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
								};
						
		char[][] twnFeat = {
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', 'T', ';', ';', ';', ';', '#', '#', '#', ';', ';', '~', '~', '~'},
									{';', ';', '#', '#', '#', ';', ';', ';', '/', ':', '#', ';', ';', '~', '~', '~'},
									{';', ';', '#', ':', '#', ';', ';', ';', '#', '#', '#', ';', 'T', '~', '~', '~'},
									{';', ';', '#', ':', '/', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', 'T', '#', ':', '#', ';', ';', ';', '#', '#', '#', ';', 'T', '~', '~', '~'},
									{';', ';', '#', '#', '#', ';', ';', ';', '/', ':', '#', ';', ';', '~', '~', '~'},
									{';', ';', 'T', ';', ';', ';', ';', ';', '#', '#', '#', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', '#', '/', '#', 'T', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', 'T', '#', ':', '#', ';', ';', ';', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', '#', '#', '#', ';', ';', 'T', ';', ';', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
								};
		
		int[][] tnoTopo = { 
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   1,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   1,   0,  0,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   1,   0,   -99,  -99,   -99,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      1,   0,   -99,  -99, -99,   0,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   1,      0,   0, -99, -99,  1, -99,   0,  -99, -99, -99},
									{1, 1, 1,    1,   1,   0,     -99, -99, -99,  1,  1,   1,  -99, -99, -99, -99},
									{1, 1,  1,   1,   1,   1,      1,   1,   1,   1,  1,   1,  -99, -99, -99, -99},//dock
									{1, 1,  1,   1,   1,   1,      1,   1,   1,   1,  1,   1,  -99, -99, -99, -99},//dock
									{1, 1, -99, -99, -99, -99,   -99, -99, -99, -99, -99, -99, -99, -99, -99, -99},
									{1, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99, -99},
								};
		
		char[][] tnoFeat = {
									{'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', 'T', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', 'T', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', ';', ';', '~', '~', '~', '~', '~', '~'},
									{';', 'T', ';', ';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '~', '~', '~', '~', '~', '~'},
									{';', ';', 'T', ';', ';', ';', ';', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', ';', ';', '~', '~', '~', '~', '^', '~', '~', '~', '~', '~'},
									{';', ';', ';', ';', 'T', '~', '~', '~', '~', '/', '*', '\\', '~', '~', '~', '~'},
									{';', ';', ';', ';', '=', '=', '=', '=', '=', '[', '*', ']', '~', '~', '~', '~'},
									{';', ';', ';', '=', '=', '=', '=', '=', '=', '[', '*', ']', '~', '~', '~', '~'},
									{';', 'T', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
									{';', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
								};
		
		int[][] tanTopo = {
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, 1, 1, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, 1, 1, 1, 1,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},//dock
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},//dock
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, 1, 1, 1, 1, 1,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99, -99,},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, -99, -99, -99, -99, -99, -99, -99, },
						};
		
		char[][] tanFeat = { 
									{'#', '#', '#', '#', 'T', ';', ';', 'T', ';', ';', ';', ';', '~', '~', '~', '~',},
									{'#', ':', ':', '#', ';', ';', 'T', ';', ';', ';', ';', '~', '~', '~', '~', '~',},
									{'#', ':', ':', '\\', '=', '=', '=', ';', ';', ';', '~', '~', '~', '~', '~', '~',},
									{'#', ':', ':', '/', '=', '=', '=', ';', ';', ';', '~', '~', '~', '~', '~', '~',},
									{'#', ':', ':', '#', ';', '=', '=', ';', ';', ';', '~', '~', '~', '~', '~', '~',},
									{'#', '#', '#', '#', ';', '=', '=', ';', 'T', ';', '~', '~', '~', '^', '~', '~',},
									{'#', ':', ':', '#', ';', '=', '=', ';', ';', '~', '~', '~', '/', '*', '\\', '~',},
									{'#', ':', ':', '/', ';', '=', '=', ';', '~', '~', '~', '/', '*', '*', '*', '\\',},
									{'#', '#', '#', '#', ';', '=', '=', '=', '=', '=', '=', '[', '>', '>', '>', ']',},
									{'T', ';', ';', ';', ';', '=', '=', '=', '=', '=', '=', '[', '>', '>', '>', ']', },
									{';', ';', 'T', ';', ';', '=', '=', ';', '~', '~', '~', '[', '_', '_', '_', ']',},
									{';', ';', ';', ';', ';', '=', '=', ';', ';', '~', '~', '~', '~', '~', '~', '~',},
									{'T', ';', ';', 'T', '#', '/', '\\', '#', ';', '~', '~', '~', '~', '~', '~', '~',},
									{';', ';', ';', ';', '#', ':', ':', '#', ';', '~', '~', '~', '~', '~', '~', '~',},
									{';', 'T', ';', ';', '#', ':', ':', '#', '~', '~', '~', '~', '~', '~', '~', '~',},
									{';', ';', ';', 'T', '#', '#', '#', '#', '~', '~', '~', '~', '~', '~', '~', '~', },
						 		 };
						
		int[][] shipTopo = {
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
								};

		char[][] shipFeat = {
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
									{'=', '=', '=', '=', '='},
								};

		System.out.println("Populating maps...");//-----------------------------------------------------------------
		Entity[][] wwEnt = new Entity[16][16];
		wwEnt[0][14] = new Frog(1);
		wwEnt[4][5] = new NPC("Ele", 1, 1, 1, 1, 1, 2, 1, 1, 3, 0, 1, 'e', 98).setDialogue(new DialogueTree()
								.add("\"My doll isn't very good anymore, but I still play with it!\"",(p,q) -> p.getOrigin() == Player.Origin.FAIM || q.getOpinion() == NPC.Opinion.FRIENDLY)
								.add("The girl is too preoccupied playing with a doll to speak to you. The doll is shoddily made out of wood, and its hair " +
								"is falling out.", (p,q) -> p.getPerc() >= 5)
								.add("The girl is too preoccupied playing with a doll to speak to you.")
								.add(new int[] {0,1}, "Offer to help.", "\"Would you really? Thank you!\"")
								.add(new int[] {0,2}, "Leave.", "\"Bye bye!\""));
		wwEnt[4][4] = new NPC("Maia", 5, 5, 3, 2, 4, 2, 3, 3, 5, 1, 2, 'm', 93).setDialogue(new DialogueTree()
						 .add("\"Please... I have children...\"", (p,q) -> q.getOpinion() == NPC.Opinion.FEARFUL)
						 .add("\"Why don't you just leave already. You've already taken everything we have.\"",
						 (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == NPC.Opinion.HOSTILE)
						 .add("\"You're that odd one Mathieu told me about. You say some strange things...\"", 
						 (p,q) -> p.getOrigin() == Player.Origin.OTHER || q.getOpinion() == NPC.Opinion.CURIOUS)
						 .add("\"You say you don't know where you're from? Well, if you remember, you should let Mathieu know; he's in charge around here.\"",
						 (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN)
						 .add("\"Hello there, stranger.\"")
						 .add(new int[] {4,1}, "What is this place?", "\"This town is called Faim. Welcome.\"")
						 .add(new int[] {4,2}, "Who are you?", "\"I am Maia. Nice to meet you.\"")
						 .add(new int[] {4,3}, "Why is it so dark?", "\"What do you mean? Tonight is a full moon, and it shines so brightly.\"")
						 .add(new int[] {4,4}, "Bye.", "\"Bye, then.\""));

		wwEnt[3][4] = new NPC("Mathieu", 5, 5, 5, 5, 2, 2, 3, 5, 2, 4, 2, 'm', 92).setDialogue(new DialogueTree()
						 .add("\"Take what you want and begone. I can only stand your smell so long.\"", 
						 (p,q) -> p.getOrigin() == Player.Origin.TANIERE || q.getOpinion() == NPC.Opinion.HOSTILE)
						 .add("\"Have you remembered where you're from?\"", (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN || q.getOpinion() == NPC.Opinion.CURIOUS)
						 .add("\"Hello there, friend.\"", (p,q) -> p.getOrigin() == Player.Origin.FAIM || q.getOpinion() == NPC.Opinion.FRIENDLY)
						 .add("\"Hello there, stranger. From where are you coming?\"", (p,q) -> p.getOrigin() == Player.Origin.UNKNOWN || p.getOrigin() == Player.Origin.UNDEFINED)
						 .add(new int[] {1,1}, "Yes.", "\"Well, where is it, then?\"").add(new int[] {1,2}, "No.", "\"Well, let me know if you do.\"")
						 .add(new int[] {3,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
							 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", (g) -> g.setInterOpinion(NPC.Opinion.FRIENDLY),
							 (g) -> g.setPlayerOrigin(Player.Origin.FAIM))
						 .add(new int[] {3,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
							 (g) -> g.setInterOpinion(NPC.Opinion.NEUTRAL), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
						 .add(new int[] {3,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
							 (g) -> g.setInterOpinion(NPC.Opinion.HOSTILE), (g) -> g.setPlayerOrigin(Player.Origin.TANIERE))
						 .add(new int[] {3,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
							 + "and if you remember, let me know.\"", (g) -> g.setInterOpinion(NPC.Opinion.CURIOUS), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
						 .add(new int[] {1,0,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
							 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", (g) -> g.setInterOpinion(NPC.Opinion.FRIENDLY),
							 (g) -> g.setPlayerOrigin(Player.Origin.FAIM))
						 .add(new int[] {1,0,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
							 (g) -> g.setInterOpinion(NPC.Opinion.NEUTRAL), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
						 .add(new int[] {1,0,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
							 (g) -> g.setInterOpinion(NPC.Opinion.HOSTILE), (g) -> g.setPlayerOrigin(Player.Origin.TANIERE))
						 .add(new int[] {1,0,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
							 + "and if you remember, let me know.\"", (g) -> g.setInterOpinion(NPC.Opinion.CURIOUS), (g) -> g.setPlayerOrigin(Player.Origin.OTHER))
						 .add("\"Hello there.\""));

		Entity[][] twnEnt = new Entity[16][16];
		twnEnt[7][3] = new NPC.Maia();
		twnEnt[5][3] = new NPC("Oliver", 1, 1, 1, 1, 2, 3, 1, 1, 3, 0, 1, 'o', 99).setDialogue(
										new DialogueTree().add("\"My mom says not to play in the woods to the north, but I want to so bad! The wind always whispers funny jokes " +
											"in my ears...\""));
		twnEnt[11][6] = new NPC.Mathieu();

		Entity[][] tnoEnt = new Entity[16][16];

		Entity[][] tanEnt = new Entity[16][16];
		tanEnt[1][1] = new NPC.Pirate();
		tanEnt[4][2] = new NPC.Pirate();
		tanEnt[14][5] = new NPC.Pirate();

		Entity[][] shipEnt = new Entity[5][5];
		shipEnt[2][4] = new NPC.Captain();

		System.out.println("Writing descriptions...");//--------------------------------------------------------------------------------

		String wwDesc = "You find yourself surrounded by forest, lightly illuminated by the full moon overhead.\n" +
		"A light breeze flows between the trees and almost sounds like hushed voices.\n" +
		"Despite the light from the moon, the entire forest looks dull. The trees' hue are desaturated and the whole area feels devoid of life.\n";

		String twnDesc = "A little town on the outskirts of a forest.\n" +
						"The sound of rushing water from a river to the East can be heard, but otherwise it is deadly silent.\n" +
						"It seems oddly devoid of life.\n";

		String tnoDesc = "You stand on a small bit of wild peninsula, near the sea.\n" +
						"In the distance you can see an old ship floating in the bay.\n" +
						"The ship's sails are tattered and it seems to be in a state of disrepair.\n" +
						"Further to the south you can hear the light bustle of voices and song.\n";

		String tanDesc = "The salty win blows through a port town on the shore.\n" +
						"Much more lively than the little village to the North.\n" +
						"You can hear music and chatter. The people here seem to be thriving.\n";

		String shipDesc = "The floorboards creek as you step into the cabin of the ship.\n" +
								"The air is damp and smells of mildew and alcohol.\n";

		System.out.println("Finalizing maps...");//-------------------------------------------------------------------------------
		WW =  new Map("whispering woods", wwDesc, wwTopo, wwFeat, wwEnt, 16, 16, 2);
		TWN = new Map("town", twnDesc, twnTopo, twnFeat, twnEnt, 16, 16, 1);
		TNO = new Map("wilderness", tnoDesc, tnoTopo, tnoFeat, tnoEnt, 16, 16, 3);
		TAN = new Map("taniere", tanDesc, tanTopo, tanFeat, tanEnt, 16, 16, 2);
		SHIP = new Map("ship", shipDesc, shipTopo, shipFeat, shipEnt, 5, 5, 2);

		System.out.println("Adding links...");//-------------------------------------------------------------------------------
		for (int i = 0; i < 16; i++) {
			WW.addLink(TWN, 15, (i%13), 1, (i%13));
			TWN.addLink(WW, 0, (i%13), 14, (i%13));
			TWN.addLink(TNO, 15, (i%10), 1, (i%10));
			TNO.addLink(TWN, 0, (i%10), 14, (i%10));
		}
		TNO.addLink(TAN, 15, 0, 0, 10); 
		TAN.addLink(TNO, 0, 11, 14, 0);
		TAN.addLink(SHIP, 8, 13, 2, 0);
		SHIP.addLink(TAN, 2, 2, 8, 12);
	}

}
