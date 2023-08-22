package ncg.everdark.ui;

import java.io.*;
import java.util.Scanner;
import java.util.Map;
import java.util.EnumMap;

public class CFG {

	public enum Colour{BLACK, BLUE, GREEN, CYAN, RED, MAGENTA, BROWN, WHITE, GRAY,
							BRIGHT_BLUE, BRIGHT_GREEN, BRIGHT_CYAN, BRIGHT_RED, BRIGHT_MAGENTA, BRIGHT_YELLOW, BRIGHT_WHITE, RESET}

	private static CFG cfg = null;

	private boolean legacy;
	private boolean colour;
	private String globalPath;
	private String musicPath;
	private String savePath;
	private int width;
	private int height;
	
	private Map<Colour,String> colourTags;

	public CFG(String fileName, boolean legacy) throws FileNotFoundException, IOException {
		
		this.legacy = legacy;
		colourTags = new EnumMap<Colour,String>(Colour.class);
		
		File configFile = new File(fileName);
		Scanner lineScanner = new Scanner(configFile);
		String[] values = new String[6];
		for (int i = 0; i<6; i++) {
			Scanner valueGetter = new Scanner(lineScanner.nextLine());
			valueGetter.useDelimiter(": ");
			valueGetter.next();
			values[i] = valueGetter.next();
		}
		width = Integer.parseInt(values[0]);
		height = Integer.parseInt(values[1]);
		globalPath = values[2];
		savePath = values[3];
		musicPath = values[4];
		colour = (values[5].toLowerCase().charAt(0) == 'y');
		
		if (legacy && colour) {
			colourTags.put(Colour.BLACK, ConsoleColours.BLACK);
			colourTags.put(Colour.WHITE, ConsoleColours.WHITE);
			colourTags.put(Colour.GRAY, ConsoleColours.BLACK_BRIGHT);
			colourTags.put(Colour.BROWN, ConsoleColours.YELLOW);//
			colourTags.put(Colour.RED, ConsoleColours.RED);
			colourTags.put(Colour.BLUE, ConsoleColours.BLUE);
			colourTags.put(Colour.GREEN, ConsoleColours.GREEN);
			colourTags.put(Colour.BRIGHT_YELLOW, ConsoleColours.YELLOW_BRIGHT);
			colourTags.put(Colour.CYAN, ConsoleColours.CYAN);
			colourTags.put(Colour.MAGENTA, ConsoleColours.PURPLE);
			colourTags.put(Colour.BRIGHT_BLUE, ConsoleColours.BLUE_BRIGHT);
			colourTags.put(Colour.BRIGHT_GREEN, ConsoleColours.GREEN_BRIGHT);
			colourTags.put(Colour.BRIGHT_CYAN, ConsoleColours.CYAN_BRIGHT);
			colourTags.put(Colour.BRIGHT_RED, ConsoleColours.RED_BRIGHT);
			colourTags.put(Colour.BRIGHT_MAGENTA, ConsoleColours.PURPLE_BRIGHT);
			colourTags.put(Colour.BRIGHT_WHITE, ConsoleColours.WHITE_BRIGHT);
			colourTags.put(Colour.RESET, ConsoleColours.RESET);
		} else if (colour) {
			String htmlTag = "<span style=\"color:";
			String close = ";\">";
			colourTags.put(Colour.BLACK, htmlTag + "#19191E" + close);
			colourTags.put(Colour.WHITE, htmlTag + "#AAAAAA" + close);
			colourTags.put(Colour.GRAY, htmlTag + "#555555" + close);
			colourTags.put(Colour.BROWN, htmlTag + "#AA5500" + close);
			colourTags.put(Colour.RED, htmlTag + "#AA0000" + close);
			colourTags.put(Colour.BLUE, htmlTag + "#0000AA" + close);
			colourTags.put(Colour.GREEN, htmlTag + "#00AA00" + close);
			colourTags.put(Colour.BRIGHT_YELLOW, htmlTag + "#FFFF55" + close);
			colourTags.put(Colour.CYAN, htmlTag + "#00AAAA" + close);
			colourTags.put(Colour.MAGENTA, htmlTag + "#AA00AA" + close);
			colourTags.put(Colour.BRIGHT_BLUE, htmlTag + "#5555FF" + close);
			colourTags.put(Colour.BRIGHT_GREEN, htmlTag + "#55FF55" + close);
			colourTags.put(Colour.BRIGHT_CYAN, htmlTag + "#55FFFF" + close);
			colourTags.put(Colour.BRIGHT_RED, htmlTag + "#FF5555" + close);
			colourTags.put(Colour.BRIGHT_MAGENTA, htmlTag + "#FF55FF" + close);
			colourTags.put(Colour.BRIGHT_WHITE, htmlTag + "#FFFFFF" + close);
			colourTags.put(Colour.RESET, "</span>");
		} else {
			for (Colour c : Colour.values()) {
				colourTags.put(c, "");
			}
		}
	}

	public static void ini(String fileName, boolean legacy) throws FileNotFoundException, IOException {
		cfg = new CFG(fileName, legacy);
	}
	
	public static int getWidth() {
		return cfg.width;
	}

	public static int getHeight() {
		return cfg.height;
	}
	
	public static String getGlobalPath() {
		return cfg.globalPath;
	}
	
	public static String getSavePath() {
		return cfg.savePath;
	}
	
	public static String getMusicPath() {
		return cfg.musicPath;
	}
	
	public static String colour(String toColour, Colour c) {
		return cfg.colourTags.get(c) + toColour + cfg.colourTags.get(Colour.RESET);
	}
	
	public static String colour(char toColour, Colour c) {
		return cfg.colourTags.get(c) + toColour + cfg.colourTags.get(Colour.RESET);
	}
	
	public static String colour(int toColour, Colour c) {
		return cfg.colourTags.get(c) + toColour + cfg.colourTags.get(Colour.RESET);
	}
	
	/*public static String colour(Object toColour, Colour c) {
		return cfg.colourTags.get(c) + toColour + cfg.colourTags.get(Colour.RESET);
	}*/

}
