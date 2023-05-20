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
	}

	public boolean addLocation(Map m) {
		return world.addVertex(m);
	}

	public void addConnection(Map m1, Map m2) {
		world.addEdge(m1,m2);
	}

	public void movePlayer(String direction) {
		location.movePlayer(direction.charAt(0));
	}

	public String getMapString() {
		return location.toString();
	}
	//public boolean changeLocation(--what to put here?--) {} 

}
