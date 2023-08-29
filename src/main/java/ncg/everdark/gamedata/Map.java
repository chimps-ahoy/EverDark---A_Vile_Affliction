package ncg.everdark.gamedata;

import ncg.everdark.ui.CFG;
import ncg.everdark.ui.CFG.Colour;
import ncg.everdark.events.Event;
import ncg.everdark.entities.Entity;
import ncg.everdark.entities.Player;

import java.util.Scanner;
import java.io.Serializable;
import java.util.function.Function;

public class Map implements Serializable {
	
	private static int mapCount = 0;
	private final int ID;
	private String name;
	private String desc;
	private int[][] topoMap;
	private char[][] featMap;
	private Entity[][] entMap;
	private Event[][] evtMap;
	
	private int playerC;//player's current column position
	private int playerR;//player's current row position
	private final int ROWS;//# of rows in the Map
	private final int COLS;//# of columns in the Map
	private final int PERC_DELTA;
	
	private static final String BLOCKING = "T#";
	private static final String LIQUID = "~";

	public Map(String name, String desc, int[][] topoMap, char[][] featMap, int rows, int cols, int percDelta){
		this.ID = mapCount++;
		this.name = name;
		this.desc = desc;
		this.topoMap = topoMap;
		this.featMap = featMap;
		this.entMap = new Entity[rows][cols];
		this.evtMap = new Event[rows][cols];
		ROWS = rows;
		COLS = cols;
		PERC_DELTA = percDelta;
	}

	public void spawnEntity(Entity e, int r, int c) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (entMap[i][j] != null && entMap[i][j].equals(e)) {
					entMap[i][j] = null;
				}
			}
		}
		if (c >= 0 && c < COLS && r >= 0 && r < ROWS) {
			entMap[r][c] = e;
			if (e != null && e.getClass() == Player.class) {//this probably isn't the best way (at least not my preferred way),
																			//but this way I can SAY i've used reflection which makes me look cool B)
				playerC = c;
				playerR = r;
			}
		}
	}

	public void addLink(Map destination, int r, int c, int endR, int endC) {
		if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
			evtMap[r][c] = new Event("You travel to a new location. ", (state) -> state.changeLocation(destination, endR, endC));
		}
	}

	public Entity getNeighbor(char d) {
		Entity output = null;
		if ((d == 'n' && playerR == 0) || (d == 's' && playerR == ROWS-1) || (d == 'e' && playerC == COLS-1) || (d == 'w' && playerC == 0)) {	
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

	public String movePlayer(char d) throws Event {
	
		String output = "";
		
		if ((d == 'n' && playerR == 0) || (d == 's' && playerR == ROWS-1) || (d == 'e' && playerC == COLS-1) || (d == 'w' && playerC == 0)) {//checking for out of bounds
			output = "You can't walk there";
		} else if ( ((d == 'n' && !passable(-1,0)) || (d == 's' && !passable(1,0)) || (d == 'e' && !passable(0,1)) || (d == 'w' && !passable(0,-1))) ) {
			output = "Something blocks your path";
		} else if ((d == 'n' && !climbable(-1,0)) || (d == 's' && !climbable(1,0)) || (d == 'e' && !climbable(0,1)) || (d == 'w' && !climbable(0,-1))) {
			output = "The change in elevation is too great";
		} else if (d == 'n') {
			entMap[playerR-1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR--;			
			output = "You take a step to the North";
			eventTrigger();
		} else if (d == 's') {
			entMap[playerR+1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR++;
			output = "You take a step to the South";
			eventTrigger();
		} else if (d == 'e') {
			entMap[playerR][playerC+1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC++;
			output = "You take a step to the East";
			eventTrigger();
		} else if (d =='w') {
			entMap[playerR][playerC-1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC--;
			output = "You take a step to the West";
			eventTrigger();
		} else {
			output = "Unrecognized direction";
		}
		return output + ".\n";
	}
	
	private boolean climbable(int dR, int dC) {//WARNING: does NOT check for out of bounds - this is only expected to be called by movePlayer() for now, which checks that itself
		double playerClimbing = entMap[playerR][playerC].getClimbing();
		int CLIMBING_FACTOR = Math.round(Math.round(playerClimbing));//checks if a change in elevation can be traversed by the player
		return (Math.abs(topoMap[playerR][playerC] - topoMap[playerR+dR][playerC+dC]) <= CLIMBING_FACTOR);
	}
	
	private boolean passable(int dR, int dC) {//same as climbable but for obstacles
		return !blocking(featMap[playerR+dR][playerC+dC]) && (entMap[playerR+dR][playerC+dC] == null);
	}

	private void eventTrigger() throws Event {
		if (evtMap[playerR][playerC] != null) {
			throw evtMap[playerR][playerC];
		}
	}
	
	private boolean blocking(char feature) {//checks if a map feature is blocking
		return (BLOCKING.indexOf(feature) >= 0);
	}

	public String getDesc() {
		int playerPerc = (entMap.length > 0 && entMap[playerR][playerC] != null) ? (entMap[playerR][playerC].getStat(Entity.Stat.PERC)) : (0);
		String[] details = desc.split("\n");
		String output = details[0];
		for (int i = 1; i < details.length && playerPerc >= i*PERC_DELTA ; i++) {
			output += '\n' + details[i];
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
		StringBuilder output = new StringBuilder(ROWS*COLS+50);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (i == playerR && j == playerC) {
					output.append(CFG.colour(Math.abs(topoMap[i][j]), Colour.BRIGHT_YELLOW));
				} else if (Math.abs(topoMap[i][j]) > 9) {
					output.append(CFG.colour("!", Colour.RED));
				} else if (topoMap[i][j] < 0) {
					output.append(CFG.colour(Math.abs(topoMap[i][j]),Colour.GRAY));
				} else {
					output.append(topoMap[i][j]);
				}
				output.append(" ");
			}
			output.append("\n");
		}
		return output.toString();
	}

	public String toString() {
		StringBuilder output = new StringBuilder(ROWS*COLS+50);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (entMap[i][j] != null) {
					output.append(entMap[i][j]);
				} else if (evtMap[i][j] != null) {
					output.append(CFG.colour(">", Colour.CYAN));
				} else if (featMap[i][j] == '~') {
					output.append(CFG.colour(featMap[i][j], Colour.BLUE));
				} else if (featMap[i][j] == '@') {
					output.append(CFG.colour(featMap[i][j], Colour.GREEN));
				} else if (featMap[i][j] != '\u0000') { 
					output.append(featMap[i][j]);
				} else {
					output.append(topoMap[i][j]);
				}
				output.append(" ");
			}
			output.append("\n");
		}
		return output.toString();
	}

}
