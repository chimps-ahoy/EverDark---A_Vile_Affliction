package ncg.chimpsahoy.everdark;

import java.io.*;
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
	private int inStr = 0;
	private int inEndur = 0;
	private int inDex = 0;
	private int inSwift = 0;
	private int inIq = 0;
	private int inWil = 0;
	private int inCharm = 0;
	private int inPerc = 0;
	private int inIntim = 0;
	private String inName = "";
	private char inAppear = 'c';

	public GameState(Map startingLocation) {
		location = startingLocation;
		this.player = null;
		
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

	public String ini(String response) throws LoadFromFileException {//This all feels really hack-y to me and im not super secure about it, but i fiddled with it so long and it feels cleaner than what i had before
		String output = "";
		GameState finalState = null;
		File[] fileList = (new File(Config.SAVE_PATH)).listFiles();
		if (iniStage == 0) {//would you like to load from file?
			if (response.toLowerCase().charAt(0) != 'y') {
				output = "\nWelcome to EverDark. The Character Creation Process will begin now. You are given " + availablePoints + " total Stat Points to assign" + 
				" amongst your characters stats. The stats will be shown one-by-one, and you can't go back! So, " +
				"plan carefully.\nThe available stats are: Strength, Endurance, Dexterity, Swiftness, Intelligence, Willpower, Charisma, Intimidation, and Perception." +
				"\nWould you like to know more about these stats? (Y/N): ";
				iniStage = 1;
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
	"Perception - This is a mental analogue to Swiftness and Dexterity. It is your character's precision and speed at observing things in their environment.\n";
			}
			output += "How many points would you like to put into Strength? (Remaining: " + availablePoints + ")";;
			iniStage = 2;
		} else if (iniStage == 2) {
			try { 
				inStr = Integer.parseInt(response);
				if (inStr >= 0 && inStr <= availablePoints) {
					availablePoints -= inStr;
					output = "How many points would you like to put into Endurance? (Remaining: " + availablePoints + ")";
					iniStage = 3;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 3) {
			try { 
				inEndur = Integer.parseInt(response);
				if (inEndur >= 0 && inEndur <= availablePoints) {
					availablePoints -= inEndur;
					output = "How many points would you like to put into Dexterity? (Remaining: " + availablePoints + ")";
					iniStage = 4;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 4) {
			try { 
				inDex = Integer.parseInt(response);
				if (inDex >= 0 && inDex <= availablePoints) {
					availablePoints -= inDex;
					output = "How many points would you like to put into Intelligence? (Remaining: " + availablePoints + ")";
					iniStage = 5;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 5) {
			try { 
				inIq = Integer.parseInt(response);
				if (inIq >= 0 && inIq <= availablePoints) {
					availablePoints -= inIq;
					output = "How many points would you like to put into Willpower? (Remaining: " + availablePoints + ")";
					iniStage = 6;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 6) {
			try { 
				inWil = Integer.parseInt(response);
				if (inWil >= 0 && inWil <= availablePoints) {
					availablePoints -= inWil;
					output = "How many points would you like to put into Charisma? (Remaining: " + availablePoints + ")";
					iniStage = 7;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 7) {
			try { 
				inCharm = Integer.parseInt(response);
				if (inCharm >= 0 && inCharm <= availablePoints) {
					availablePoints -= inCharm;
					output = "How many points would you like to put into Perception? (Remaining: " + availablePoints + ")";
					iniStage = 8;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 8) {
			try { 
				inPerc = Integer.parseInt(response);
				if (inPerc >= 0 && inPerc <= availablePoints) {
					availablePoints -= inPerc;
					output = "How many points would you like to put into Intimidation? (Remaining: " + availablePoints + ")";
					iniStage = 9;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 9) {
			try { 
				inIntim = Integer.parseInt(response);
				if (inIntim >= 0 && inIntim <= availablePoints) {
					availablePoints -= inIntim;
					output = "What would you like to name your character?";
					iniStage = 10;
				} else { 
					output = "You don't have enough points for that.";
				}
			} catch (Exception e) {
				output = "Please make sure your input is an integer.";	
			}
		} else if (iniStage == 10) {
				inName = response;
				output = "How would you like to appear? Please choose an alphabetic ASCII character. Case does not matter. If multiple characters are input, the first will be used.";
				iniStage = 11;
		} else if (iniStage == 11) {
				inAppear = response.toLowerCase().charAt(0);
				if (inAppear < 'a' || inAppear > 'z') {
					output = "Invalid input. Try again.";
				} else {
					player = new Player(inName, inStr, inEndur, inDex, inSwift, inIq, inWil, inCharm, inPerc, inIntim, inAppear, 0);
					output = this.changeLocation(Config.WW, 6, 6);
				}
		} else if (iniStage == 100) {
			String loadMessage = "";
			try {
				finalState = GameState.loadFromFile(fileList[Integer.parseInt(response)]);
				loadMessage = finalState.getLocationDesc();
				throw new LoadFromFileException(finalState, loadMessage);
			} catch (IOException|ClassNotFoundException|RuntimeException ex) {
				output = "Save could not be found.";
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
			output = ml.getMessage() + this.changeLocation(ml.getDestination(), ml.getEndR(), ml.getEndC()) + '\n';
		}
		return output;
	}

	public Entity getInterlocutor() {
		return interlocutor;
	}

	public String beginDialogue(char d) {
		interlocutor = location.beginDialogue(d);
		String output = "There's no one to talk to there.";
		try { 
			output = interlocutor.beginDialogue();
		} catch (EndOfDialogueException eode) {
			output = eode.getMessage();
			interlocutor = null;
		} catch (Exception e) {

		}
		return output;
	}

	public void endDialogue() {
		interlocutor = null;
	}

	public String getLocationDesc() {
		return location.getDesc();
	}
	
	public String changeLocation(Map m, int playerR, int playerC) {
		this.stopMusic();
		location = m;
		location.spawnPlayer(player, playerR, playerC);
		try {
			music = new File(Config.MUSIC_PATH + location.getName() + ".wav");
			as = AudioSystem.getAudioInputStream(music);
			clip = AudioSystem.getClip();
			clip.open(as);
			this.playMusic();
		} catch (Exception e) {
			//we have no music but everything still works
		}
		return location.getDesc();
	} 

	public void changePlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
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
		clip.stop();
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
