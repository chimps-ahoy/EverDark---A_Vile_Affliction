package ncg.chimpsahoy.everdark;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GameState { //UPDATE MAY2023: after thinking about it this may not even be needed and all the info could simply be stored in InputHandler (and furthermore, InputHandler could be a method in Main),
			 //but I'm definitely keeping it because it's a good way to abstract it in my mind and I'd rather have the architecture and not need it than refactor everything down the line

	//private Player player <- if entMap[playerR][playerC] ever gets SLOW or if the code is too messy to get player info, it makes sense to have a pointer to the player
	//in the GameState
	private Map location;
	private Graph<Map, DefaultEdge> world;//IMPORTANT: THIS MAY NOT BE NEEDED!? with how I'm envisioning using Portals/Events/MapLinks to connect Maps, the Graph structure is not needed, and all info about the world
					      //is stored in the Maps and between them. This is similar to how we handle the player in entMap with playerR and playerC, and we just need to make sure portals are always updated so the 
					      //gameworld doesn't break

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

	public String getLocationDesc() {
		return location.getDesc();
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

	public String getTopoMapString() {
		return location.getTopoMapString();
	}
	
	public void changeLocation(Map m) {
		location.stopMusic();
		location = m;
		this.playMusic();
	} 

}
