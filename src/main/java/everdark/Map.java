package ncg.chimpsahoy.everdark;

import java.util.Scanner;

public class Map {
	
	private final int ID;
	private String name;
	private String desc;
	private int[][] topoMap;
	private char[][] featMap;
	private Entity[][] entMap;
	private MapLink[][] links;
	
	private int playerC;//player's current column position
	private int playerR;//player's current row position
	private final int ROWS;//# of rows in the Map
	private final int COLS;//# of columns in the Map
	private final int PERC_DELTA;
	
	private static final String BLOCKING = "T#";
	private static final String LIQUID = "~";

	public Map(int ID, String name, String desc, int[][] topoMap, char[][] featMap, Entity[][] entMap, int rows, int cols, int percDelta){
		this.ID = ID;
		this.name = name;
		this.desc = desc;
		this.topoMap = topoMap;
		this.featMap = featMap;
		this.entMap = entMap;
		this.links = new MapLink[rows][cols];
		ROWS = rows;
		COLS = cols;
		PERC_DELTA = percDelta;
	}

	public void spawnPlayer(Entity player, int r, int c) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (entMap[i][j] != null && entMap[i][j].equals(player)) {
					entMap[i][j] = null;
				}
			}
		}
		if (c >= 0 && c < COLS && r >= 0 && r < ROWS) {
			entMap[r][c] = player;
			playerC = c;
			playerR = r;
		}
	}

	public void addLink(Map destination, int r, int c, int endR, int endC) {
		if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
			links[r][c] = new MapLink(this, destination);
			links[r][c].setEndPoint(endR, endC);
		}
	}

	public Entity beginDialogue(char d) {
		Entity output = null;
		if ((d == 'n' && playerR == 0) || (d == 's' && playerR == 15) || (d == 'e' && playerC == 15) || (d == 'w' && playerC == 0)) {	
			output = null;
		} else if (d == 'n') {
			output = entMap[playerR-1][playerC];
		} else if (d == 's') {
			output = entMap[playerR+1][playerC];
		} else if (d == 'e') {
			output = entMap[playerR][playerC+1];
		} else if (d == 'w') {
			output = entMap[playerR][playerC-1];
		}
		return output;
	}

	public String movePlayer(char d) throws MapLink {
	
		String output = "";
		
		if ((d == 'n' && playerR == 0) || (d == 's' && playerR == 15) || (d == 'e' && playerC == 15) || (d == 'w' && playerC == 0)) {//checking for out of bounds
			output = "You can't walk there";
		} else if ( ((d == 'n' && !passable(-1,0)) || (d == 's' && !passable(1,0)) || (d == 'e' && !passable(0,1)) || (d == 'w' && !passable(0,-1))) ) {
			output = "An obstacle blocks your path";
		} else if ((d == 'n' && !climbable(-1,0)) || (d == 's' && !climbable(1,0)) || (d == 'e' && !climbable(0,1)) || (d == 'w' && !climbable(0,-1))) {
			output = "The change in elevation is too great";
		} else if (d == 'n') {
			entMap[playerR-1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR--;			
			output = "You take a step to the North";
			transportPlayer(output);
		} else if (d == 's') {
			entMap[playerR+1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR++;
			output = "You take a step to the South";
			transportPlayer(output);
		} else if (d == 'e') {
			entMap[playerR][playerC+1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC++;
			output = "You take a step to the East";
			transportPlayer(output);
		} else if (d =='w') {
			entMap[playerR][playerC-1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC--;
			output = "You take a step to the West";
			transportPlayer(output);
		} else {
			output = "Unrecognized direction";
		}
		return output + ".\n";
	}
	
	private boolean climbable(int dR, int dC) {//WARNING: does NOT check for out of bounds - this is only expected to be called by movePlayer() for now, which checks that itself
		final int CLIMBING_FACTOR = 1;
		 return (Math.abs(topoMap[playerR][playerC] - topoMap[playerR+dR][playerC+dC]) <= CLIMBING_FACTOR);
	}
	
	private boolean passable(int dR, int dC) {//same as climbable but for obstacles
		return !blocking(featMap[playerR+dR][playerC+dC]);
	}

	private void transportPlayer(String output) throws MapLink {
		if (links[playerR][playerC] != null) {
			links[playerR][playerC].setMessage(output + " and are transported to a new location.\n");
			throw links[playerR][playerC];
		}
	}
	
	private boolean blocking(char feature) {//checks if a map feature is blocking
		boolean output = false;
		for (int i = 0; i < BLOCKING.length() && !output; i++) {
			output = (feature == BLOCKING.charAt(i));
		}
		return output;
	}

	public String getDesc() {
		int playerPerc = entMap[playerR][playerC].getPerc();
		String output = "";
		String[] details = desc.split("\n");
		for (int i = 0; i < details.length && playerPerc >= i*PERC_DELTA ; i++) {
			output += details[i] + '\n';
		}	
		return output;
	}
	
	public String getName() {
		return name;
	}

	public boolean equals(Map other) {
		return (this.ID == other.ID);
	}

	public String getTopoMapString() {//gets a string of the topography of the area. 
		String output = "";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (i == playerR && j == playerC) {
					output += ConsoleColours.YELLOW_BOLD + topoMap[i][j] + ConsoleColours.RESET + " ";//highlights the player location in  yellow
				} else {
					output += topoMap[i][j] + " ";
				}
			}
			output += '\n';
		}
		return output;
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (entMap[i][j] != null) {
					output += entMap[i][j];
				} else if (links[i][j] != null) {
					output += ConsoleColours.RED_BRIGHT + '@' + ConsoleColours.RESET;	
				} else if (featMap[i][j] == ';') { 
					output += featMap[i][j];
				} else if (featMap[i][j] == 'T') {
					output += featMap[i][j]; //these are separated incase I want to do ANSI colour shenanigans
				} else if (featMap[i][j] == '~') {
					output += ConsoleColours.BLUE_BRIGHT + featMap[i][j] + ConsoleColours.RESET;
				} else if (featMap[i][j] != '\u0000') { 
					output += featMap[i][j];
				} else {
					output += topoMap[i][j];
				}
				output += " ";
			}
			output += '\n';
		}
		return output;
	}

}
