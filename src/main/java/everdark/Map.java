package ncg.chimpsahoy.everdark;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class Map {
	
	//private static int mapCount = 0; ? dunno if i wanna do it this way because could lead to issues with importing from file
	private final int ID;
	private String name;
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

	public Map(int ID, String name, int[][] topoMap, char[][] featMap, Entity[][] entMap, int rows, int cols) throws UnsupportedAudioFileException, IOException, LineUnavailableException {//Event[][] evntMap
		this.ID = ID;
		this.name = name;
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

	public String movePlayer(char d) {
		
		final int CLIMBING_FACTOR = 1;//the difference of elevation the player can traverse
		String output = "";
		
		if ((d == 'n' && playerR == 0) || (d == 's' && playerR == 15) || (d == 'e' && playerC == 15) || (d == 'w' && playerC == 0)) {
			output = "You can't walk there.";
		} else if (d == 'n' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR-1][playerC]) <= CLIMBING_FACTOR && featMap[playerR-1][playerC] != 'T' && featMap[playerR-1][playerC] != '#') {
			entMap[playerR-1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR--;			
			output = "You take a step to the North.";
		} else if (d == 's' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR+1][playerC]) <= CLIMBING_FACTOR && featMap[playerR+1][playerC] != 'T' && featMap[playerR+1][playerC] != '#') {
			entMap[playerR+1][playerC] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerR++;
			output = "You take a step to the South.";
		} else if (d == 'e' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC+1]) <= CLIMBING_FACTOR && featMap[playerR][playerC+1] != 'T' && featMap[playerR][playerC+1] != '#') {
			entMap[playerR][playerC+1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC++;
			output = "You take a step to the East.";
		} else if (d =='w' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC-1]) <= CLIMBING_FACTOR && featMap[playerR][playerC-1] != 'T' && featMap[playerR][playerC-1] != '#') {
			entMap[playerR][playerC-1] = entMap[playerR][playerC];
			entMap[playerR][playerC] = null;
			playerC--;
			output = "You take a step to the West.";
		} else if ((d == 'n' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR-1][playerC]) > CLIMBING_FACTOR) || (d == 's' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR+1][playerC]) > CLIMBING_FACTOR)
					|| (d == 'e' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC+1]) > CLIMBING_FACTOR) || (d == 'w' && Math.abs(topoMap[playerR][playerC] - topoMap[playerR][playerC-1]) > CLIMBING_FACTOR)) {
			output = "The change in elevation is too great.";
		} else {
			output = "An obstacle blocks your path.";
		}
		return output + "\n" + this;
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
