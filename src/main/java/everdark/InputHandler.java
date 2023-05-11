public class InputHandler { 

	private GameState state;

	public InputHandler(GameState state) { 
		this.state = state;
	}

	public String handles(String input) {
		String output = "Command not recognized.";
		if (input.equals("placeholder")) { 
			output = "this is a placeholder";
		} 
		return output;
	}

}
