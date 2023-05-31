package ncg.chimpsahoy.everdark;

public class MapLink extends Exception {

	private Map source;
	private Map destination;
	private String message;
	private int endR;
	private int endC;

	public MapLink(Map source, Map destination) {
		super();
		this.source = source;
		this.destination = destination;
	}

	public Map getDestination() {
		return destination;
	}

	public Map getSource() {
		return source;
	}

	public void setEndPoint(int r, int c) {
		endR = r;
		endC = c;
	}

	public int getEndR() {
		return endR;
	}

	public int getEndC() {
		return endC;
	}

	public void setMessage(String message) {
		this.message = message; 
	}

	public String getMessage() {
		return message;
	}

}
