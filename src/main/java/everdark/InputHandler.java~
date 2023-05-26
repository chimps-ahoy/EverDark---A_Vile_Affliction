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
		String output = "Command not recognized";

		if (leadingCommand.equals("placeholder")) { 
			output = "this is a placeholder";
		} else if (leadingCommand.equals("move")) {
			output = (tokenizer.hasNext()) ? "" : "Please include a direction after the move command.\n";
			while (tokenizer.hasNext()) {
				output += state.movePlayer(tokenizer.next());
			}
			output += state.getMapString();
		} else if (leadingCommand.equals("look")) {
			output = state.getLocationDesc();
		} else if (leadingCommand.equals("survey")) {
			output = "You survey the area for changes in elevation.\n" + state.getTopoMapString();
		} else if (leadingCommand.equals("save")) {
			//output = state.save();
		} else if (leadingCommand.equals("exit")) {
			acceptingInput = false;
			state.stopMusic();
			state.closeMusic();
			output = "Bye!\n";
		}

		return output;
	}

}
