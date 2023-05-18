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
	
	private int playerX;
	private int playerY;
	private final int ROWS;
	private final int COLS;

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
			}
			output += '\n';
		}
		return output;
	}










}
