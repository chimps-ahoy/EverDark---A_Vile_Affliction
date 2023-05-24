package ncg.chimpsahoy.everdark;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GameState { 

	private Map location;
	private Graph<Map, DefaultEdge> world; 

	public GameState(Map startingLocation) {
		world = new SimpleGraph(DefaultEdge.class);
		addLocation(startingLocation);
		location = startingLocation;
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

	public void playMusic() {
		location.playMusic();
	}

	public void stopMusic() {
		location.stopMusic();
	}
	
	public void closeMusic() {
		location.closeMusic();
	}

	public String getMapString() {
		return location.toString();
	}
	
	public void changeLocation(Map m) {
		location.stopMusic();
		location = m;
		this.playMusic();
	} 

}
