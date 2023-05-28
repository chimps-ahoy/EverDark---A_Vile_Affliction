package ncg.chimpsahoy.everdark;

//import java.util.Scanner;

public class Map {
	
	private final int ID;
	private String name;
	private String loDesc;
	private String medDesc;
	private String hiDesc;
	private int[][] topoMap;
	private char[][] featMap;
	private Entity[][] entMap;
	//private Event[][] evntMap;
	
	private int playerC;//player's current column position
	private int playerR;//player's current row position
	private final int ROWS;//# of rows in the Map
	private final int COLS;//# of columns in the Map


	public Map(int ID, String name, String loDesc, String medDesc, String hiDesc, int[][] topoMap, char[][] featMap, Entity[][] entMap, int rows, int cols){//Event[][] evntMap
		this.ID = ID;
		this.name = name;
		this.loDesc = loDesc;
		this.medDesc = medDesc;
		this.hiDesc = hiDesc;
		this.topoMap = topoMap;
		this.featMap = featMap;
		this.entMap = entMap;
		//this.evntMap = evntMap;
		ROWS = rows;
		COLS = cols;
	}

	public void spawnPlayer(Entity player, int c, int r) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (entMap[i][j] != null && entMap[i][j].equals(player)) {
					entMap[i][j] = null;
				}
			}
		}
		if (c >= 0 && c < COLS && r >= 0 && r < ROWS && entMap[r][c] == null) {
			entMap[r][c] = player;
			playerC = c;
			playerR = r;
		}
	}

	public String movePlayer(char d) {//TODO: look into cleaning this up. 
	
		String output = "";
		
		if ((d == 'n' && playerR == 0) || (d == 's' && playerR == 15) || (d == 'e' && playerC == 15) || (d == 'w' && playerC == 0)) {//checking if we're on the edge and we try walking off
			output = "You can't walk there.";
		} else if (blocked(d)) {
			output = "An obstacle blocks your path";
		} else if ((d == 'n' && !climbable(-1,0)) || (d == 's' && !climbable(1,0)) || (d == 'e' && !climbable(0,1)) || (d == 'w' && !climbable(0,-1))) {
			output = "The change in elevation is too great.";
		} else if (d == 'n') {
			entMap[playerR-1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR--;			
			output = "You take a step to the North.";
		} else if (d == 's') {
			entMap[playerR+1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR++;
			output = "You take a step to the South.";
		} else if (d == 'e') {
			entMap[playerR][playerC+1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC++;
			output = "You take a step to the East.";
		} else if (d =='w') {
			entMap[playerR][playerC-1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC--;
			output = "You take a step to the West.";
		} else {
			output = "Something has stopped you from moving.";
		}
		return output + "\n";
	}
	
	private boolean climbable(int dR, int dC) {//WARNING: does NOT check for out of bounds - this is only expected to be called by movePlayer() for now, which checks that itself
		final int CLIMBING_FACTOR = 1;
		 return (Math.abs(topoMap[playerR][playerC] - topoMap[playerR+dR][playerC+dC]) <= CLIMBING_FACTOR);
	}
	
	private boolean blocked(char d) {
		return ( (d == 'n' && blocking(featMap[playerR-1][playerC])) || (d == 's' && blocking(featMap[playerR+1][playerC])) || 
				(d == 'e' && blocking(featMap[playerR][playerC+1])) || (d == 'w' && blocking(featMap[playerR][playerC-1])) );
	}
	
	private boolean blocking(char feature) {
		return (feature == 'T' || feature == '#');
	}

	public String getDesc() {//TODO: look into ways of making this better? I'd like to have one description variable and split it up with a Scanner, but that was REALLY slow and didn't work
		String output = loDesc;
		if (entMap[playerR][playerC].getPerc() >= 66) {//this method just uses 3 description variables and displays them depending on the perception
			output += medDesc + hiDesc;
		} else if (entMap[playerR][playerC].getPerc() >= 33) { 
			output +=  medDesc;
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
				} else if (featMap[i][j] == ';') { 
					output += featMap[i][j];
				} else if (featMap[i][j] == 'T') {
					output += featMap[i][j]; //these are separated incase I want to do ANSI colour shenanigans
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
