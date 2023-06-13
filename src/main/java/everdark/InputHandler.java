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

		if (state.getInterlocutor() != null) { 
			try {
				output = state.getInterlocutor().talk(Integer.parseInt(leadingCommand));
			} catch (EndOfDialogueException eode) {
				output = eode.getMessage();
				state.endDialogue();
			} catch (Exception e) {
				output = "Please choose a valid response.";
			}
		} else if (leadingCommand.equals("move")) {
			boolean leftMap = false;
			output = (tokenizer.hasNext()) ? "" : "Please include a direction after the move command.";
			while (tokenizer.hasNext() && !leftMap) {
				try {
					output += state.movePlayer(tokenizer.next());
				} catch (MapLink ml) {
					output = ml.getMessage() + state.changeLocation(ml.getDestination(), ml.getEndR(), ml.getEndC());
					leftMap = true;
				}
			}
			output += state.getMapString();
		} else if (leadingCommand.equals("speak")) {
			output = "Please include a direction after the speak command.";
			if (tokenizer.hasNext()) {
				output = state.beginDialogue(tokenizer.next().charAt(0));
			}
		} else if (leadingCommand.equals("look")) {
			output = state.getLocationDesc();
		} else if (leadingCommand.equals("survey")) {
			output = "You survey the area for changes in elevation." + state.getTopoMapString();
		} else if (leadingCommand.equals("clear")) {
			for (int i = 0; i < Config.HEIGHT; i++) {
				output += '\n';
			}
		} else if (leadingCommand.equals("save")) {
			//output = state.save();
		} else if (leadingCommand.equals("exit")) {
			acceptingInput = false;
			state.close();
			output = "Bye!";
		}

		return output + '\n';
	}

}
