package ncg.everdark.gamedata;

import ncg.everdark.ui.CFG;
import ncg.everdark.events.*;
import ncg.everdark.dialogue.*;
import ncg.everdark.entities.*;
import ncg.everdark.entities.Entity.Stat;
import ncg.everdark.entities.Player.Origin;
import ncg.everdark.entities.NPC.Opinion;
import ncg.everdark.items.Item;

public class GameBuilder {//this is ONLY to be used for development so i can construct the .game file quickly, should be removed for releases
	
	public static void buildGame()  {
		
		Map MAIN_MENU = null;
		Map WW = null;
		Map CAVE_1 = null;
		Map CAVE_2 = null;
		Map CAVE_3 = null;
		Map CAVE_4 = null;
		Map CAVE_5 = null;
		Map TWN = null;
		Map TNO = null;
		Map TAN = null;
		Map SHIP = null;
		
		System.out.println("Generating topographies and features...");
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
									{'^', '^', '^', '^', '^', '^', '^', '^', ';', ';', 'T', ';', ';', '~', '@', '~'},
									{'^', '^', '^', '^', '^', '^', '^', ';', ';', ';', ';', ';', ';', '~', '~', '~'},
									{'^', '^', '^', ';', '^', ';', ';', ';', ';', 'T', ';', ';', ';', '~', '~', '~'},
									{'^', '^', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', ';', '@', '~', '~'},
									{'^', '^', ';', ';', ';', ';', ';', 'T', ';', ';', ';', ';', ';', '~', '~', '~'},
									{'^', ';', 'T', ';', ';', ';', ';', ';', ';', ';', ';', ';', 'T', '~', '~', '@'},
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

		int[][] cavTopo = {
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1},
									{-1, -1, -1, -1, -1}
								};

		char[][] cavFeat = {
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'},
									{'*', '*', '*', '*', '*'}
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

		System.out.println("Writing descriptions...");
		String wwDesc = "You find yourself surrounded by forest, lightly illuminated by the full moon overhead.\n" +
		"A light breeze flows between the trees and almost sounds like hushed voices.\n" +
		"Despite the light from the moon, the entire forest looks dull. The trees' hue are desaturated and the whole area feels devoid of life.\n";

		String caveDesc = "The cold, dank air clings to your skin within the dark cave.\nTo the left and right are entrances that lead deeper into the earth,\nYou can hear " +
								"water dripping onto the stone floor.\n";

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

		System.out.println("Building maps...");
		MAIN_MENU = new Map("main", "Would you like to load from file? (Y/N): ", null, null, 0, 0, 0);
		WW =  new Map("whispering woods", wwDesc, wwTopo, wwFeat, 16, 16, 2);
		CAVE_1 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_2 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_3 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_4 = new Map("cave", caveDesc, cavTopo, cavFeat, 5, 5, 2);
		CAVE_5 = new Map("cave", "Congrats! You solved it, connor :)", cavTopo, cavFeat, 5, 5, 2);
		TWN = new Map("town", twnDesc, twnTopo, twnFeat, 16, 16, 1);
		TNO = new Map("wilderness", tnoDesc, tnoTopo, tnoFeat, 16, 16, 3);
		TAN = new Map("taniere", tanDesc, tanTopo, tanFeat, 16, 16, 2);
		SHIP = new Map("ship", shipDesc, shipTopo, shipFeat, 5, 5, 2);
		
		System.out.println("Creating NPCs...");
		NPC MAIA = new NPC("Maia", 3, 2, 4, 2, 3, 3, 5, 1, 2, 'm', Entity.Race.HUMAN);
		NPC MATHIEU = new NPC("Mathieu", 5, 5, 2, 2, 3, 5, 2, 4, 2, 'm', Entity.Race.HUMAN);
		NPC ELE = new NPC("Ele", 1, 1, 1, 2, 1, 1, 3, 0, 1, 'e', Entity.Race.HUMAN);
		NPC OLIVER = new NPC("Oliver", 1, 1, 2, 3, 1, 1, 3, 0, 1, 'o', Entity.Race.HUMAN);
		NPC CAPTAIN = new NPC("Captain", 10, 10, 3, 4, 5, 8, 4, 10, 4, 'c', Entity.Race.HUMAN);
		NPC FROG_PRINCESS = new NPC("Frog Princess", 1, 3, 2, 5, 1, 1, 1, 1, 1, 'f', Entity.Race.FROG);
		NPC FROG_KING = new NPC("Frog King", 10, 10, 2, 5, 1, 1, 1, 1, 1, 'f', Entity.Race.FROG);
		
		System.out.println("Setting relationships...");
		setRelationships(MAIA, MATHIEU, ELE, OLIVER, CAPTAIN, FROG_PRINCESS, FROG_KING);
		
		System.out.println("Writing dialogue...");
		writeDialogue(MAIA, MATHIEU, ELE, OLIVER, CAPTAIN, FROG_PRINCESS, FROG_KING, WW, CAVE_1, CAVE_2, CAVE_3, CAVE_4, CAVE_5, TWN, TNO, TAN, SHIP);

		System.out.println("Populating maps...");
		populateMaps(MAIA, MATHIEU, ELE, OLIVER, CAPTAIN, FROG_PRINCESS, FROG_KING, WW, CAVE_1, CAVE_2, CAVE_3, CAVE_4, CAVE_5, TWN, TNO, TAN, SHIP);

		System.out.println("Buidling map links...");
		addLinks(WW, CAVE_1, CAVE_2, CAVE_3, CAVE_4, CAVE_5, TWN, TNO, TAN, SHIP);

		System.out.println("Generating Everdark.game...");
		saveToFile(MAIN_MENU, WW);
	}
	
	private static void setRelationships(NPC MAIA, NPC MATHIEU, NPC ELE, NPC OLIVER, NPC CAPTAIN, NPC FROG_PRINCESS, NPC FROG_KING) {
		MATHIEU.addRelationship(MAIA);
		MATHIEU.addRelationship(ELE);
		MATHIEU.addRelationship(OLIVER);
		MAIA.addRelationship(OLIVER);
		MAIA.addRelationship(ELE);
		OLIVER.addRelationship(ELE);
		CAPTAIN.addRelationship(FROG_PRINCESS);
	}
	
	private static void writeDialogue(NPC MAIA, NPC MATHIEU, NPC ELE, NPC OLIVER, NPC CAPTAIN, NPC FROG_PRINCESS, NPC FROG_KING,
									  Map WW, Map CAVE_1, Map CAVE_2, Map CAVE_3, Map CAVE_4, Map CAVE_5, Map TWN, Map TNO, Map TAN, Map SHIP) {
		
		Item OLD_DOLL = new Item("Old doll", 0.2, 0);
		Item FROG_AMULET = new Item("Frog amulet", 0.2, 20).put(Stat.CHARM, 3);
		Item GOLD_CHAIN = new Item("Gold chain", 0.031, 200);
		
		MAIA.setDialogue(new DialogueTree()//NEED somekinda OR for Opinion/Origin requirements
			 .add("\"Please... I have children...\"", new OpinionRequirement(Opinion.FEARFUL).and(new OriginRequirement(Origin.TANIERE)))
			 .add("\"Why don't you just leave already. You've already taken everything we have.\"",
			 new OpinionRequirement(Opinion.HOSTILE).or(new OriginRequirement(Origin.TANIERE)))
			 .add("\"You're that odd one Mathieu told me about. You say some strange things...\"", 
			 new OpinionRequirement(Opinion.NEUTRAL).or(new OriginRequirement(Origin.OTHER)))
			 .add("\"You say you don't know where you're from? Well, if you remember, you should let Mathieu know; he's in charge around here.\"",
			 new OpinionRequirement(Opinion.CURIOUS).or(new OriginRequirement(Origin.UNKNOWN)))
			 .add("\"So you're from around here? I'd love to get to know you some more. The kids have been wanting someone to play with too...\"",
			 new OpinionRequirement(Opinion.FRIENDLY).or(new OriginRequirement(Origin.FAIM)))
			 .add("\"Hello there, stranger.\"")
			 .add(new int[] {5,1}, "What is this place?", "\"This town is called Faim. Welcome.\"")
			 .add(new int[] {5,2}, "Who are you?", "\"I am Maia. Nice to meet you.\"")
			 .add(new int[] {5,3}, "Why is it so dark?", "\"What do you mean? Tonight is a full moon, and it shines so brightly.\"")
			 .add(new int[] {5,4}, "Bye.", "\"Bye, then.\"")
			 .add(new int[] {3,1}, "What is this place?", "\"This town is called Faim. Welcome.\"")
			 .add(new int[] {3,2}, "Who are you?", "\"I am Maia. Nice to meet you.\"")
			 .add(new int[] {3,3}, "Why is it so dark?", "\"What do you mean? Tonight is a full moon, and it shines so brightly.\"")
			 .add(new int[] {3,4}, "Bye.", "\"Bye, then.\"")
			 .add(new int[] {5,2,1}, "When is sunrise?", "\"Sun... rise?\" She looks at you like you have two heads, \"Where are you from? And how old are you? " +
				 "The sun has not risen here for thirty years. If there is somewhere it still shines, I'd love to go.\"")
			 .add(new int[] {5,2,2}, "Of course.", "\"Yes.\"")
			 .add(new int[] {3,2,1}, "When is sunrise?", "\"Sun... rise?\" She looks at you like you have two heads, \"Where are you from? And how old are you? " +
				 "The sun has not risen here for thirty years. If there is somewhere it still shines, I'd love to go.\"")
			 .add(new int[] {3,2,2}, "Of course.", "\"Yes.\""));

		
		MATHIEU.setDialogue(new DialogueTree()//Opinion/Origin requirements
				 .add("\"Take what you want and begone. I can only stand your smell so long.\"", 
				 new OpinionRequirement(Opinion.HOSTILE).or(new OriginRequirement(Origin.TANIERE)))
				 .add("\"Have you remembered where you're from?\"", new OpinionRequirement(Opinion.CURIOUS).or(new OriginRequirement(Origin.UNKNOWN)))
				 .add("\"Hello there, friend.\"", new OpinionRequirement(Opinion.FRIENDLY).or(new OriginRequirement(Origin.FAIM)))
				 .add("\"Hello there, stranger. From where are you coming?\"", new OriginRequirement(Origin.UNDEFINED))
				 .add(new int[] {1,1}, "Yes.", "\"Well, where is it, then?\"").add(new int[] {1,2}, "No.", "\"Well, let me know if you do.\"")
				 .add(new int[] {3,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
					 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", new OpinionChange(MATHIEU, Opinion.FRIENDLY),
					 new OriginChange(Origin.FAIM))
				 .add(new int[] {3,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
					 new OpinionChange(MATHIEU, Opinion.NEUTRAL), new OriginChange(Origin.OTHER))
				 .add(new int[] {3,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
					 new OpinionChange(MATHIEU, Opinion.HOSTILE), new OriginChange(Origin.TANIERE))
				 .add(new int[] {3,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
					 + "and if you remember, let me know.\"", new OpinionChange(MATHIEU, Opinion.CURIOUS), new OriginChange(Origin.UNKNOWN))
				 .add(new int[] {1,0,1}, "This village.", "\"Really? I'm sorry then, friend. You must have been gone long. None of us remember you. "
					 + "Regardless, any resident of Faim is like a child to me. Please, make yourself at home.\"", new OpinionChange(MATHIEU, Opinion.FRIENDLY),
					 new OriginChange(Origin.FAIM))
				 .add(new int[] {1,0,2}, "The forest to the North.", "\"Haha... Oh? Well... We of Faim will still treat you with hospility, odd one.\"",
					 new OpinionChange(MATHIEU, Opinion.NEUTRAL), new OriginChange(Origin.OTHER))
				 .add(new int[] {1,0,3}, "The coastal town to the South.", "\"So you've come for more? Well, we don't have much left. Be quick with it.\"",
					 new OpinionChange(MATHIEU, Opinion.HOSTILE), new OriginChange(Origin.TANIERE))
				 .add(new int[] {1,0,4}, "I don't know.", "\"You don't know where you're from? I'm sorry to here that. Feel free to make yourself at home here, " 
					 + "and if you remember, let me know.\"", new OpinionChange(MATHIEU, Opinion.CURIOUS), new OriginChange(Origin.UNKNOWN))
				 .add("\"Hello there.\""));

		
		ELE.setDialogue(new DialogueTree()//Origin/Opinion requirements
			.add("\"My doll isn't very good anymore, but I still play with it!\"", new OpinionRequirement(Opinion.FRIENDLY).or(new OriginRequirement(Origin.FAIM)))
			.add("The girl is too preoccupied playing with a doll to speak to you. The doll is shoddily made out of wood, and its hair " +
			"is falling out.", new StatRequirement(Stat.PERC, 5, 1))
			.add("The girl is too preoccupied playing with a doll to speak to you.")
			.add(new int[] {0,1}, "Offer to help.", "\"Would you really? Thank you!\"", new GiveItem(OLD_DOLL))
			.add(new int[] {0,2}, "Leave.", "\"Bye bye!\""));

		
		OLIVER.setDialogue(new DialogueTree().add("\"The woods to the north can be so scary! I went in the cave once, and even though I took two " + 
											"steps left and two steps right, I still didn't end up back in the same place! ...Maybe I should listen to mom when she " + 
											"says not to play there...\""));

		Consequence CAPTAIN_LEAVES = new SpawnEntity(SHIP, null, 2, 4).and(new SpawnEntity(WW, CAPTAIN, 1, 13)).and(new StageChange(CAPTAIN, 2)).and(new StageChange(FROG_PRINCESS, 3));
		Requirement FROG_SPEAKING = new ItemRequirement(FROG_AMULET);
		
		CAPTAIN.setDialogue(new DialogueTree()
				.add("\"Yar, lad...\"", new OpinionRequirement(Opinion.FRIENDLY).or(new OriginRequirement(Origin.TANIERE)))
				.add("The large man doesn't even meet your eyes as you approach. Instead, he simply stares at a chain he holds between " +
				"his fat fingers. He reeks of booze.", new StatRequirement(Stat.PERC, 6, 1))
				.add("The large man doesn't even acknowledge you. He reeks of booze.", new StatRequirement(Stat.PERC, 3, 1))
				.add("The large man doesn't even acknowledge you.")
				.add(new int[] {1}, "\"Yar, lad...\"", new OpinionRequirement(Opinion.FRIENDLY).or(new OriginRequirement(Origin.TANIERE)))
				.add(new int[] {1}, "The large man doesn't even meet your eyes as you approach. Instead, he simply stares at a chain he holds between " +
				"his fat fingers. He reeks of booze.", new StatRequirement(Stat.PERC, 6, 1))
				.add(new int[] {1}, "The large man doesn't even acknowledge you. He reeks of booze.", new StatRequirement(Stat.PERC, 3, 1))
				.add(new int[] {1}, "The large man doesn't even acknowledge you.")
				.add(new int[] {4,1}, "There is a frog in the whispering woods that claims to be your daughter.",
											"\"L-lad? That be true? Yer not pullin' on me leg?\" Tears well in his eyes, \"Blast it, I'll go there me self! I need to see!\"",
											CAPTAIN_LEAVES)
				.add(new int[] {4,2}, "Leave.", "\"Bye then, lad...\"")
				.add(new int[] {5,1}, "There is a frog in the whispering woods that claims to be your daughter.",
											"\"That be true? If ye be lyin' to me...\" He clenches his fist with the threat, "
											+ "but tears well in his eyes, \"Blast it, I'll go there me self! I need to see!\"",
											CAPTAIN_LEAVES)
				.add(new int[] {5,2}, "Leave.", "The man does not even notice you leave. He stares, transfixed, at the chain.")
				.add(new int[] {6,1}, "There is a frog in the whispering woods that claims to be your daughter.",
											"\"That be true? If ye be lyin' to me...\" He clenches his fist with the threat, \"Blast it, I'll go there me self! I need to see!\"",
											CAPTAIN_LEAVES)
				.add(new int[] {6,2}, "Leave.", "The man does not even notice you leave. He continues starting at the chain in his hand.")
				.add(new int[] {7,1}, "There is a frog in the whispering woods that claims to be your daughter.",
											"\"That be true? If ye be lyin' to me... Blast it, I'll go there me self! I need to see!\"",
											CAPTAIN_LEAVES)
				.add(new int[] {7,2}, "Leave.", "The man does not even notice you leave.")
				.add(new int[] {2}, "\"What be going on here!? You say it's me daughter, but all it just looks like a normal frog t'me!\"", FROG_SPEAKING)
				.add(new int[] {2}, "\"What be going on here!? You say it's me daughter, but all it just looks like a normal frog t'me!\"")
				.add(new int[] {8,1}, "Give him the amulet", "\"That be the punchline to your bloody prank? Ye want me to wear an amulet of a frog now too? Fine! " 
									+ "Must be worth something at least...\" As he puts on the amphibian jewelry, his expression of rage quickly softens, and tears "
									+ "being pouring down his face. \"Ye wasn't lying! That be her!\""
									+ " After taking a moment to wipe his face, he turns to face you warmly, holding out a gold chain." 
									+ " \"Take this. I shant be needing it anymore.\"", 
									new GiveItem(FROG_AMULET, false).and(new GiveItem(GOLD_CHAIN)).and(new StageChange(CAPTAIN, 3)))
				.add(new int[] {8,2}, "Leave.", "He glares at you as you walk off. \"Ye better be back with an answer!\"")
				.add(new int[] {3}, "\"Thanks, lad. I'll always remember what ye did fer me.\""));
		
		FROG_PRINCESS.setDialogue(new DialogueTree().add("\"ribbit.\"", FROG_SPEAKING.negate())
						.add(new int[] {1}, "\"ribbit.\"", FROG_SPEAKING.negate())
						.add(new int[] {2}, "\"ribbit.\"", FROG_SPEAKING.negate())
						.add(new int[] {3}, "\"ribbit.\"", FROG_SPEAKING.negate())
						.add(new int[] {1}, "\"Please contact my father right away!\"")
						.add(new int[] {2}, "\"Have you come to change your mind about helping me? Please, I do not know what to do.\"")
						.add("\"Hello?\"", FROG_SPEAKING)
						.add(new int[] {6,1}, "Hello.", "\"You can understand me? It must be that amulet you have, isn't it?\"")
						.add(new int[] {6,2}, "Leave.", "The frog looks... oddly sad?")
						.add(new int[] {6,0,1}, "I'm fluent in frog; the amulet is just for looks.", 
							"\"Please listen, the spirits of these woods cursed me to be a frog. My father lives in Taniere and he has not seen me for so long. " +
							"He must be so worried. Can you help me?\"")
						.add(new int[] {6,0,2}, "I suppose that would be it.", "\"Please help me. I'm a human, but I was cursed! My father lives in Taniere. He must be so worried.\"")
						.add(new int[] {6,0,0,1}, "Accept.", "\"Thank you! Please find him and tell him what happened!\"", new StageChange(FROG_PRINCESS, 1).and(new StageChange(CAPTAIN, 1)))
						.add(new int[] {6,0,0,2}, "Decline.", "\"Oh...\"", new StageChange(FROG_PRINCESS, 2))
						.add(new int[] {6,0,1,1}, "Accept.", "\"Thank you! Please find him and tell him what happened!\"", new StageChange(FROG_PRINCESS, 1).and(new StageChange(CAPTAIN, 1)))
						.add(new int[] {6,0,1,2}, "Decline.", "\"Oh...\"", new StageChange(FROG_PRINCESS, 2))
						.add(new int[] {5,1}, "Yes.", "\"Thank you! Please find him and tell him what happened!\"", new StageChange(FROG_PRINCESS, 1).and(new StageChange(CAPTAIN, 1)))
						.add(new int[] {5,2}, "No.", "\"Oh...\"")
						.add(new int[] {3}, "\"There he is! That's my dad! But... He can't understand me...\"", FROG_SPEAKING)
						.add("\"ribbit.\""));
						
		
		FROG_KING.setDialogue(new DialogueTree().add("The fat frog shifts backwards slightly, revealing an amulet hidden under its stomach.")
				  .add(new int[] {0,1}, "Take the amulet.", "The frog hops away.", new GiveItem(FROG_AMULET).and(new SpawnEntity(CAVE_5, null, 0 ,2)))
				  .add(new int[] {0,2}, "Leave it.", "The frog tilts its head and stares at you in confusion, before moving to conceal the amulet once again."));

	}
		
	private static void populateMaps(NPC MAIA, NPC MATHIEU, NPC ELE, NPC OLIVER, NPC CAPTAIN, NPC FROG_PRINCESS, NPC FROG_KING,
									  Map WW, Map CAVE_1, Map CAVE_2, Map CAVE_3, Map CAVE_4, Map CAVE_5, Map TWN, Map TNO, Map TAN, Map SHIP) {
										  
		WW.spawnEntity(FROG_PRINCESS, 0, 14);

		CAVE_5.spawnEntity(FROG_KING, 0, 2);

		TWN.spawnEntity(ELE, 5, 3);
		TWN.spawnEntity(MAIA, 7, 3);
		TWN.spawnEntity(OLIVER, 6, 7);
		TWN.spawnEntity(MATHIEU, 11, 6);

		TAN.spawnEntity(new Pirate(3), 6, 6);
		TAN.spawnEntity(new Pirate(2), 1, 1);
		TAN.spawnEntity(new Pirate(2), 3, 1);
		TAN.spawnEntity(new Pirate(3), 4, 2);
		TAN.spawnEntity(new Pirate(2), 6, 2);
		TAN.spawnEntity(new Pirate(3), 14, 5);

		SHIP.spawnEntity(CAPTAIN, 2, 4);
	}
	
	private static void addLinks(Map WW, Map CAVE_1, Map CAVE_2, Map CAVE_3, Map CAVE_4, Map CAVE_5, Map TWN, Map TNO, Map TAN, Map SHIP) {
		for (int i = 0; i < 16; i++) {
			WW.addLink(TWN, 15, (i%13), 1, (i%13));
			TWN.addLink(WW, 0, (i%13), 14, (i%13));
			TWN.addLink(TNO, 15, (i%10), 1, (i%10));
			TNO.addLink(TWN, 0, (i%10), 14, (i%10));
		}
		WW.addLink(CAVE_1, 2, 3, 2, 2);
		CAVE_1.addLink(CAVE_2, 2, 0, 2, 2);
		CAVE_1.addLink(CAVE_1, 2, 4, 2, 2);
		CAVE_2.addLink(CAVE_3, 2, 0, 2, 2);
		CAVE_2.addLink(CAVE_1, 2, 4, 2, 2);
		CAVE_3.addLink(CAVE_4, 2, 4, 2, 2);
		CAVE_3.addLink(CAVE_1, 2, 0, 2, 2);
		CAVE_4.addLink(CAVE_5, 2, 4, 2, 2);
		CAVE_4.addLink(CAVE_1, 2, 0, 2, 2);
		CAVE_1.addLink(WW, 4, 2, 2, 3);
		CAVE_5.addLink(WW, 4, 2, 2, 3);
		TNO.addLink(TAN, 15, 0, 0, 10); 
		TAN.addLink(TNO, 0, 11, 14, 0);
		TAN.addLink(SHIP, 8, 13, 2, 0);
		SHIP.addLink(TAN, 2, 2, 8, 12);
	}

	private static void saveToFile(Map MAIN_MENU, Map WW) {
		try {
			new GameState.GameData(MAIN_MENU, WW, 6, 6).save(CFG.getGlobalPath() + "EverDark");
		} catch (Exception e) {
			System.out.println("Error building Everdark.game\nThis is a fatal error. Please reinstall EverDark and ensure it is the latest version.");
			e.printStackTrace();
		}
	}
	
}
