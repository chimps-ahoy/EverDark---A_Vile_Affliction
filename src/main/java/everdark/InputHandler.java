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
		String output = "Command not recognized.\n";

		if (leadingCommand.equals("placeholder")) { 
			output = "this is a placeholder";
		} else if (leadingCommand.equals("move")) {
			if (tokenizer.hasNext()) {
				output = state.movePlayer(tokenizer.next());
			} else {
				output = "Please include a direction after the move command.\n";
			}
		} else if (leadingCommand.equals("exit")) {
			acceptingInput = false;
			state.stopMusic();
			state.closeMusic();
			output = "Bye!\n";
		}

		return output;
	}

}
