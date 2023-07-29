package ncg.chimpsahoy.everdark;

import java.io.*;
import java.util.LinkedList;
import java.util.InputMismatchException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class GameState implements Serializable {

	private Player player;
	private Entity interlocutor;//the person currently in dialogue with
	//private Entity combatant - for combat?
	private Map location;
	private transient File music;
	private transient AudioInputStream as;
	private transient Clip clip;

	private int iniStage = 0;
	private int availablePoints = 20;
	private String inName = "";
	private char inAppear = 'c';
	private int[] stats = new int[9];
	private String[] iniText = {"Stength", "Endurance", "Dexterity", "Swiftness", "Intelligence", "Willpower", "Charisma", "Perception", "Intimidation"};

	public GameState(Map startingLocation) {
		location = startingLocation;
		player = null;
		startMusic();	
		save("new game");
	}

	public String ini(String response) throws LoadFromFileException {
		String output = "";
		GameState finalState = null;
		File[] fileList = (new File(Config.SAVE_PATH)).listFiles();

		if (iniStage == 0) {//would you like to load from file?
			if (response.toLowerCase().charAt(0) != 'y') {
				output = "\nWelcome to EverDark. The Character Creation Process will begin now. You are given " + availablePoints + " total Stat Points to assign" + 
				" amongst your characters stats. The stats will be shown one-by-one, and you can't go back! So, " +
				"plan carefully.\nThe available stats are: Strength, Endurance, Dexterity, Swiftness, Intelligence, Willpower, Charisma, Intimidation, and Perception." +
				"\nWould you like to know more about these stats? (Y/N): ";
				iniStage++;
			} else if (fileList == null) {
				output = "Saves folder could not be found. Please check your config.";
			} else {
				output = "\nPlease select a save:";
				for (int i = 0; i < fileList.length; i++) {
					output += "\n" + i + " - " + fileList[i];
				}
				iniStage = 100;
			}
		} else if (iniStage == 1) {
			if (response.toLowerCase().charAt(0) == 'y') {
			output = "\nStrength - Your character's physical prowess. This is a measure of your short-twitch muscle fibres- quick bursts of force.\n" +
				"Endurance - In contrast to Strength; your character's slow-twitch muscle fibres- how long they can act before becoming fatigued.\n" +
	"Dexterity - The strength of your character's little muscles, rather than the big ones. This is your fine motor skill. It measures how precisely you can perform an action.\n" +
		"Swiftness - This is a measure of how quickly your character can perform an action.\n" +
	"Intelligence - Your character's mental ability. This is their aptitude to learn new things, and apply what they know to solve problems.\n" +
"Willpower - This is a mental analogue to Endurance. It is a measure of how much mental strain your character can take. Higher willpower also allows you to resist charm and intimidation.\n" +
		"Charisma - Your character's social ability- how easily they get along with people and how much they appeal to them.\n" +
	"Intimidation - While Charisma can allow you to convince people to do things of their own volition, Intimidation is your ability to impose your will on others by force.\n" +
	"Perception - This is a mental analogue to Swiftness and Dexterity. It is your character's precision and speed at observing things in their environment.\n\n";
			}
			output += "How many points would you like to put into " + iniText[iniStage-1] +"? (Remaining: " + availablePoints + ")";
			iniStage++;
		} else if (iniStage >= 2 && iniStage <= 9) {
			try { 
				stats[iniStage-2] = Integer.parseInt(response);
				if (stats[iniStage-2] >= 0 && stats[iniStage-2] <= availablePoints) {
					availablePoints -= stats[iniStage-2];
					output = "How many points would you like to put into " + iniText[iniStage-1] + "? (Remaining: " + availablePoints + ")";
					iniStage++;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 10) {
			try { 
				stats[iniStage-2] = Integer.parseInt(response);
				if (stats[iniStage-2] >= 0 && stats[iniStage-2] <= availablePoints) {
					availablePoints -= stats[iniStage-2];
					output = "What would you like to name your character?";
					iniStage++;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 11) {
				inName = response;
				output = "How would you like to appear? Please choose an alphabetic ASCII character. Case does not matter. If multiple characters are input, the first will be used.";
				iniStage++;
		} else if (iniStage == 12) {
				inAppear = (response.length() != 0) ? (response.toLowerCase().charAt(0)) : ('C');
				if (inAppear < 'a' || inAppear > 'z') {
					output = "Invalid input. Try again.";
				} else {
					player = new Player(inName, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5], stats[6], stats[7], stats[8], inAppear, 0);
					output = changeLocation(Config.WW, 6, 6);
				}
		} else if (iniStage == 100) {
			String loadMessage = "";
			try {
				finalState = GameState.loadFromFile(fileList[Integer.parseInt(response)]);
				loadMessage = finalState.getLocationDesc();
				iniStage = 0;
				throw new LoadFromFileException(finalState);
			} catch (IOException|ClassNotFoundException|RuntimeException ex) {
				output = "Save could not be loaded. An invalid option may have been input or the save may be corrupt or outdated. Please try again.";
			}
		}
		return output;
	}
	
	public String save() {
		String output = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uu-MM-dd_HH-mm-ss");
		try {
			String fileName = Config.SAVE_PATH + player.getName() + LocalDateTime.now().format(dtf) + ".ed";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			fos.close();
			output = "Successfully saved.";
		} catch (Exception e) {
			output = "Save unsuccessful. Progress may not be saved.";
		}			
		return output;
	}

	public String save(String name) {
		String output = "";
		try {
			String fileName = Config.SAVE_PATH + name + ".ed";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			fos.close();
			output = "Successfully saved.";
		} catch (Exception e) {
			output = "Save unsuccessful. Progress may not be saved.";
		}			
		return output;
	}

	public static GameState loadFromFile(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		GameState loaded = (GameState)(ois.readObject());
		ois.close();
		fis.close();
		return loaded;
	}

	public String movePlayer(char d) {
		String output = "";
		try {
			output = location.movePlayer(d);
		} catch (MapLink ml) {
			output = ml.getMessage() + changeLocation(ml.getDestination(), ml.getEndR(), ml.getEndC()) + '\n';
		}
		return output;
	}

	public boolean inDialogue() {
		return interlocutor != null;
	}

	public String talk(int response, LinkedList<Character> args) {
		String output = "";
		try {
			output = interlocutor.talk(response, args, player); 
		} catch (EndOfDialogueException eode) {
			output = eode.getMessage();
			interlocutor = null;
		} catch (NumberFormatException|InputMismatchException ex) { 
			output = "Please use a numeric input.";
		} catch (IllegalArgumentException iae) {
			output = iae.getMessage();
		}
		return output;
	}

	public String beginDialogue(char d) {
		interlocutor = location.beginDialogue(d);
		String output = "There's no one to talk to there.";
		try { 
			output = interlocutor.beginDialogue(player);
		} catch (EndOfDialogueException eode) {
			output = eode.getMessage();
			interlocutor = null;
		} catch (Exception e) {

		}
		return output;
	}

	public String getLocationDesc() {
		return location.getDesc();
	}
	
	public String changeLocation(Map m, int playerR, int playerC) {
		if (!location.equals(m)) { 
			stopMusic();
			location = m;
			startMusic();
		}
		location.spawnPlayer(player, playerR, playerC);
		return location.getDesc();
	} 

	public void changePlayer(Player player) {
		this.player = player;
	}

	public String getPlayerStats() {
		return player.stats();
	}

	public boolean initialized() {
		return player != null;
	}
	
	public void startMusic() {//this is ONLY needed to start the music upon loading a save, because the music objects are not serializable so they break upon exiting and reloading.
		try {
			music = new File(Config.MUSIC_PATH + location.getName() + ".wav");
			as = AudioSystem.getAudioInputStream(music);
			clip = AudioSystem.getClip();
			clip.open(as);
			this.playMusic();
		} catch (Exception e) {
			//we have no music but everything still works
		}
	}

	public void playMusic() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic() {
		try {
			clip.stop();
		} catch (Exception e) {
			//this is just to stop the program from crashing if there is no music
		}
	}
	
	public void close() {
		try {
			clip.stop();
			clip.close();
			as.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	} 

	public String getMapString() {
		return location.toString();
	}

	public String getTopoMapString() {
		return location.getTopoMapString();
	}
	

}
