package ncg.chimpsahoy.everdark;
import java.util.Scanner;

public class InputHandler { 

	private GameState state;
	private boolean acceptingInput;

	public InputHandler(GameState state) { 
		this.state = state;
		acceptingInput = true;
	}

	public boolean acceptingInput() {
		return acceptingInput;
	}

	public String handle(String input) {

		Scanner tokenizer = new Scanner(input.toLowerCase());
		String leadingCommand =(tokenizer.hasNext()) ? tokenizer.next() : "placeholder";
		String output = "Command not recognized.";

		if (leadingCommand.equals("placeholder")) { 
			output = "this is a placeholder";
		} else if (leadingCommand.equals("move")) {
			if (tokenizer.hasNext()) {
				state.movePlayer(tokenizer.next());
				output = state.getMapString();
			} else {
				output = "Please include a direction after the move command.";
			}
		} else if (leadingCommand.equals("exit")) {
			acceptingInput = false;
			output = "Bye!";
		}

		return output;
	}

}
