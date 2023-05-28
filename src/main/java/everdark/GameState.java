package ncg.chimpsahoy.everdark;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class GameState { //UPDATE MAY2023: after thinking about it this may not even be needed and all the info could simply be stored in InputHandler (and furthermore, InputHandler could be a method in Main),
			 //but I'm definitely keeping it because it's a good way to abstract it in my mind and I'd rather have the architecture and not need it than refactor everything down the line

	//private Player player <- if entMap[playerR][playerC] ever gets SLOW or if the code is too messy to get player info, it makes sense to have a pointer to the player
	//in the GameState
	private Map location;
	private Graph<Map, DefaultEdge> world;//IMPORTANT: THIS MAY NOT BE NEEDED!? with how I'm envisioning using Portals/Events/MapLinks to connect Maps, the Graph structure is not needed, and all info about the world
					      //is stored in the Maps and between them. This is similar to how we handle the player in entMap with playerR and playerC, and we just need to make sure portals are always updated so the 
					      //gameworld doesn't break
						  
	private File music;
	private AudioInputStream as;
	private Clip clip;
	private static final String MUSIC_PATH = "global/music/";

	public GameState(Map startingLocation) {
		world = new SimpleGraph(DefaultEdge.class);
		addLocation(startingLocation);
		location = startingLocation;
		
		try {
			music = new File(MUSIC_PATH + location.getName() + ".wav");
			as = AudioSystem.getAudioInputStream(music);
			clip = AudioSystem.getClip();
			clip.open(as);
		} catch (Exception e) {
			//we have no music but everything still works
		}
		this.playMusic();
	}

	public boolean addLocation(Map m) {
		return world.addVertex(m);
	}

	public void addConnection(Map m1, Map m2) {
		world.addEdge(m1,m2);
	}

	public String movePlayer(String direction) {
		return location.movePlayer(direction.charAt(0));
	}

	public String getLocationDesc() {
		return location.getDesc();
	}
	
	public void changeLocation(Map m) {
		this.stopMusic();
		location = m;
		try {
			music = new File(MUSIC_PATH + location.getName() + ".wav");
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
