package ncg.chimpsahoy.everdark;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class GameState {
	private Entity player;
	private Entity interlocutor;//the person currently in dialogue with
	//private Entity combatant - for combat?
	private Map location;
	private File music;
	private AudioInputStream as;
	private Clip clip;

	public GameState(Map startingLocation, Entity player) {
		location = startingLocation;
		this.player = player;
		
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

	public String movePlayer(String direction) throws MapLink {
		return location.movePlayer(direction.charAt(0));
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

	public void changePlayer(Entity player) {
		this.player = player;
	}

	public void playMusic() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic() {
		clip.stop();
	}
	
	public void close() {
		clip.stop();
		try {
			clip.close();
			as.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public String getMapString() {
		return location.toString();
	}

	public String getTopoMapString() {
		return location.getTopoMapString();
	}
	

}
