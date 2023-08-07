package ncg.everdark.runtime;

import ncg.everdark.global.Config;
import ncg.everdark.gamedata.GameState;
import ncg.everdark.events.Event;
import ncg.everdark.events.LoadFromFileException;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Deque;
import java.util.ArrayDeque;

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
		Deque<Character> args = new ArrayDeque<Character>();
		while (tokenizer.hasNext()) {
			args.add(tokenizer.next().toLowerCase().charAt(0));	
		}
		String output = "Command not recognized.";

		try {
			if (!state.initialized()) {
				try {
					output = state.ini(input);
				} catch (LoadFromFileException lfle) {
					state.stopMusic();
					state = lfle.getState();
					state.startMusic();
					output = state.getLocationDesc();
				}
			} else if (state.inDialogue()) { 
				try {
					output = state.talk(Integer.parseInt(leadingCommand), args);
				} catch (InputMismatchException|IllegalArgumentException ex) {
					output = "Invalid Selection.";
				}
			} else if (leadingCommand.equals("move")) {
				output = (args.isEmpty()) ? "Please include a direction after the move command.\n" : "";
				for (char d : args) {
					output += state.movePlayer(d);
				}
				output += state.getMapString();
			} else if (leadingCommand.equals("speak")) {
				output = "Please include a direction after the speak command.";
				if (!args.isEmpty()) {
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
				output = state.getPlayerStats();
			} else if (leadingCommand.equals("save")) {
				output = state.save();
			} else if (leadingCommand.equals("exit")) {
				acceptingInput = false;
				state.close();
				output = "Bye!";
			}
		} catch (Event e) {
			output = e.update(state);
		}
		return output + '\n';
	}

}
