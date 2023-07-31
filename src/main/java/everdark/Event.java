package ncg.chimpsahoy.everdark;

public abstract class Event extends Throwable {
	
	private String message;

	public Event(String message) {
		super(message);
		this.message = message;
	}

	public Event() {
		super();
	}

	public void setMessage(String message) {
		this.message = message; 
	}

	public String getMessage() {
		return message;
	}
	public abstract String update(GameState state);


}
