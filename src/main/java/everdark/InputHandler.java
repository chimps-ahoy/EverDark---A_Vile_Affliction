package ncg.chimpsahoy.everdark;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;

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
		LinkedList<Character> args = new LinkedList<>();
		while (tokenizer.hasNext()) {
			args.add(tokenizer.next().charAt(0));	
		}
		String output = "Command not recognized.";

		if (state.getPlayer() == null) {
			try {
				output = state.ini(leadingCommand);
			} catch (LoadFromFileException lfle) {
				state.stopMusic();
				state = lfle.getState();
				state.startMusic();
				output = lfle.getMessage();
			}
		} else if (state.getInterlocutor() != null) { 
			try {
				output = state.getInterlocutor().talk(Integer.parseInt(leadingCommand), args, state.getPlayer());
			} catch (EndOfDialogueException eode) {
				output = eode.getMessage();
				state.endDialogue();
			} catch (NumberFormatException|InputMismatchException ex) { 
				output = "Please use a numeric input.";
			} catch (IllegalArgumentException iae) {
				output = iae.getMessage();
			}
		} else if (leadingCommand.equals("move")) {
			output = (args.peek() == null) ? "Please include a direction after the move command.\n" : "";
			for (char d : args) {
				output += state.movePlayer(d);
			}
			output += state.getMapString();
		} else if (leadingCommand.equals("speak")) {
			output = "Please include a direction after the speak command.";
			if (args.peek() != null) {
				output = state.beginDialogue(args.pop());
			}
		} else if (leadingCommand.equals("look")) {
			output = state.getLocationDesc();
		} else if (leadingCommand.equals("survey")) {
			output = "You survey the area for changes in elevation.\n" + state.getTopoMapString();
		} else if (leadingCommand.equals("clear")) {
			output = "";
			for (int i = 0; i < Config.HEIGHT; i++) {
				output += '\n';
			}
		} else if (leadingCommand.equals("stats")) {
			output = state.getPlayer().stats();
		} else if (leadingCommand.equals("save")) {
			output = state.save();
		} else if (leadingCommand.equals("exit")) {
			acceptingInput = false;
			state.close();
			output = "Bye!";
		}

		return output + '\n';
	}

}
