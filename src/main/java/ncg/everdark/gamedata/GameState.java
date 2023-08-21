package ncg.everdark.gamedata;

import ncg.everdark.events.Event;
import ncg.everdark.events.LoadFromFileException;
import ncg.everdark.entities.Entity;
import ncg.everdark.entities.NPC;
import ncg.everdark.entities.Player;
import ncg.everdark.global.Config;
import ncg.everdark.ui.CFG;

import java.io.*;
import java.util.Deque;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameState implements Serializable {

	private Player player;
	private Entity interlocutor;//the person currently in dialogue with
	//private Entity combatant - for combat?
	private Map location;
	private transient File music;
	private transient AudioInputStream as;
	private transient Clip clip;

//----ini stuff------------------
	private int iniStage = 0;
	private int availablePoints = 30;
	private String inName = "";
	private char inAppear = 'c';
	private int[] stats = new int[9];
//---------------------------------

	public GameState(Map startingLocation) {
		location = startingLocation;
		player = null;
		startMusic();	
		save("new game");
	}

	public String ini(String response) throws LoadFromFileException {
		String output = "";
		GameState finalState = null;
		File[] fileList = (new File(CFG.getSavePath())).listFiles();

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
			output += "How many points would you like to put into " + Entity.Stat.values()[iniStage-1] +"? (Remaining: " + availablePoints + ")";
			iniStage++;
		} else if (iniStage >= 2 && iniStage <= 9) {
			try { 
				stats[iniStage-2] = Integer.parseInt(response);
				if (stats[iniStage-2] >= 0 && stats[iniStage-2] <= availablePoints) {
					availablePoints -= stats[iniStage-2];
					output = "How many points would you like to put into " + Entity.Stat.values()[iniStage-1] + "? (Remaining: " + availablePoints + ")";
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
					player = new Player(inName, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5], stats[6], stats[7], stats[8], inAppear);
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
				//ex.printStackTrace();
			}
		}
		return output;
	}
	
	public boolean initialized() {
		return player != null;
	}
	
	public String save() {
		String output = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uu-MM-dd_HH-mm-ss");
		return save(player.getName() + LocalDateTime.now().format(dtf));
	}

	public String save(String name) {
		String output = "";
		try {
			String fileName = CFG.getSavePath() + name + ".ed";
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

	public String movePlayer(char d) throws Event {
		return location.movePlayer(d);
	}

	public String setPlayerOrigin(Player.Origin origin) {
		player.setOrigin(origin);	
		return "\n(What you said has set a fact about your life into stone.)";
	}

	public String setInterOpinion(NPC.Opinion opinion) {
		NPC inter = (NPC)interlocutor;
		inter.setOpinion(opinion);
		return "\n(What you said has changed their thoughts about you.)";
	}

	public boolean inDialogue() {
		return interlocutor != null;
	}

	public void endDialogue() {
		interlocutor = null;
	}

	public String talk(int response, Deque<Character> args) throws Event {
		return interlocutor.talk(response, args, player); 
	}

	public String beginDialogue(char d) throws Event {
		interlocutor = location.getNeighbor(d);
		String output = (interlocutor != null) ? interlocutor.beginDialogue(player) : "There's no one to talk to there.";
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
		location.spawnEntity(player, playerR, playerC);
		return location.getDesc();
	} 

	public String getPlayerStats() {
		return player.stats();
	}

	public String getInv() {
		return player.stuff();
	}
	
	public void startMusic() {//this is ONLY needed to start the music upon loading a save, because the music objects are not serializable so they break upon exiting and reloading.
		try {
			music = new File(CFG.getMusicPath() + location.getName() + ".wav");
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
