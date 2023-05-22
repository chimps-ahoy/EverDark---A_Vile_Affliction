package ncg.chimpsahoy.everdark;
import java.io.*;

public class Map {
	
	//private static int mapCount = 0; ? dunno if i wanna do it this way because could lead to issues with importing from file
	private final int ID;
	private String name;
	private int[][] topoMap;
	private char[][] featMap;
	private Entity[][] entMap;
	//private Event[][] evntMap;
	
	private int playerC;//player's current column position
	private int playerR;//player's current row position
	private final int ROWS;//# of rows in the Map
	private final int COLS;//# of columns in the Map

	public Map(int ID, String name, int[][] topoMap, char[][] featMap, Entity[][] entMap, int rows, int cols) {//Event[][] evntMap
		this.ID = ID;
		this.name = name;
		this.topoMap = topoMap;
		this.featMap = featMap;
		this.entMap = entMap;
		//this.evntMap = evntMap;
		ROWS = rows;
		COLS = cols;
	}

	public void spawnPlayer(Entity player, int c, int r) {
		if (c >= 0 && c < COLS && r >= 0 && r < ROWS && entMap[r][c] == null) {
			entMap[r][c] = player;
			playerC = c;
			playerR = r;
		}
	}

	public void movePlayer(char d) {
		if (d == 'n' && playerR-1 >= 0) {
			entMap[playerR-1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR--;			
		} else if (d == 's' && playerR+1 < ROWS) {
			entMap[playerR+1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR++;
		} else if (d == 'e' && playerC+1 < COLS) {
			entMap[playerR][playerC+1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC++;
		} else if (d =='w' && playerC-1 >= 0) {
			entMap[playerR][playerC-1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC--;
		}
	}

	public boolean equals(Map other) {
		return (this.ID == other.ID);
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (entMap[i][j] != null) {
					output += entMap[i][j];
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
