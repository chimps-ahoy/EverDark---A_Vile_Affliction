package ncg.chimpsahoy.everdark;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.util.Scanner;

public class Map {
	
	//private static int mapCount = 0; ? dunno if i wanna do it this way because could lead to issues with importing from file
	private final int ID;
	private String name;
	private String loDesc;
	private String medDesc;
	private String hiDesc;
	private File track;
	private int[][] topoMap;
	private char[][] featMap;
	private Entity[][] entMap;
	//private Event[][] evntMap;
	
	private int playerC;//player's current column position
	private int playerR;//player's current row position
	private final int ROWS;//# of rows in the Map
	private final int COLS;//# of columns in the Map

	private AudioInputStream as;
	private Clip clip;

	public Map(int ID, String name, String loDesc, String medDesc, String hiDesc, int[][] topoMap, char[][] featMap, Entity[][] entMap, int rows, int cols) throws UnsupportedAudioFileException, IOException, LineUnavailableException {//Event[][] evntMap
		this.ID = ID;
		this.name = name;
		this.loDesc = loDesc;
		this.medDesc = medDesc;
		this.hiDesc = hiDesc;
		track = new File("global/music/" + name + ".wav");
		this.topoMap = topoMap;
		this.featMap = featMap;
		this.entMap = entMap;
		//this.evntMap = evntMap;
		ROWS = rows;
		COLS = cols;

		as = AudioSystem.getAudioInputStream(track);
		clip = AudioSystem.getClip();
		clip.open(as);
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

	public void playMusic() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic() {
		clip.stop();
	}
	
	public void closeMusic() {
		try {
			clip.close();
			as.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String movePlayer(char d) {//TODO: look into cleaning this up. 
		
		final int CLIMBING_FACTOR = 1;//the difference of elevation the player can traverse
		String output = "";
		
		if ((d == 'n' && playerR == 0) || (d == 's' && playerR == 15) || (d == 'e' && playerC == 15) || (d == 'w' && playerC == 0)) {//checking if we're on the edge and we try walking off
			output = "You can't walk there.";
		} else if (d == 'n' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR-1][playerC]) <= CLIMBING_FACTOR && featMap[playerR-1][playerC] != 'T' && featMap[playerR-1][playerC] != '#') {//the Good case for north
			entMap[playerR-1][playerC] = entMap[playerR][playerC];//makes sure change in elevation is good and there are no obstacles
			entMap[playerR][playerC] = null;
			playerR--;			
			output = "You take a step to the North.";
		} else if (d == 's' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR+1][playerC]) <= CLIMBING_FACTOR && featMap[playerR+1][playerC] != 'T' && featMap[playerR+1][playerC] != '#') {//same for south
			entMap[playerR+1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR++;
			output = "You take a step to the South.";
		} else if (d == 'e' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC+1]) <= CLIMBING_FACTOR && featMap[playerR][playerC+1] != 'T' && featMap[playerR][playerC+1] != '#') {//same for east
			entMap[playerR][playerC+1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC++;
			output = "You take a step to the East.";
		} else if (d =='w' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC-1]) <= CLIMBING_FACTOR && featMap[playerR][playerC-1] != 'T' && featMap[playerR][playerC-1] != '#') {//same for west
			entMap[playerR][playerC-1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC--;
			output = "You take a step to the West.";
		} else if ((d == 'n' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR-1][playerC]) > CLIMBING_FACTOR) || (d == 's' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR+1][playerC]) > CLIMBING_FACTOR)
					|| (d == 'e' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC+1]) > CLIMBING_FACTOR) || (d == 'w' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC-1]) > CLIMBING_FACTOR)) {
			output = "The change in elevation is too great.";//if the good case doesn't work then we know it's bad. this checks if the elevation is WHY it's bad.
		} else {
			output = "An obstacle blocks your path.";//assume otherwise that it's an obstacle. There would be WAY too many checks otherwise.
		}//TODO: ideally we'd want "you can't walk there" as the 'else' condition, so look into a way of doing that without having a MASSIVE else-if checking for obstacles
		return output + "\n";
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

	public boolean equals(Map other) {
		return (this.ID == other.ID);
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
