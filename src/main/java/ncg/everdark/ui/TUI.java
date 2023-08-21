package ncg.everdark.ui;

import ncg.everdark.global.Config;
import ncg.everdark.gamedata.GameState;
import ncg.everdark.events.Event;
import ncg.everdark.events.LoadFromFileException;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Deque;
import java.util.ArrayDeque;

public class TUI extends UI { 

	public TUI() { 
		super();
	}

	public void handle(String input) {

		Scanner tokenizer = new Scanner(input.toLowerCase());
		String leadingCommand = (tokenizer.hasNext()) ? tokenizer.next() : "placeholder";
		Deque<Character> args = new ArrayDeque<Character>();
		while (tokenizer.hasNext()) {
			args.add(tokenizer.next().toLowerCase().charAt(0));	
		}
		String output = "Command not recognized.";

		try {
			if (!super.state.initialized()) {
				try {
					output = super.state.ini(input);
				} catch (LoadFromFileException lfle) {
					super.state.stopMusic();
					super.state = lfle.getState();
					super.state.startMusic();
					output = super.state.getLocationDesc();
				}
			} else if (super.state.inDialogue()) { 
				try {
					output = super.state.talk(Integer.parseInt(leadingCommand), args);
				} catch (InputMismatchException|IllegalArgumentException ex) {
					output = "Invalid Selection.";
				}
			} else if (leadingCommand.equals("move")) {
				output = (args.isEmpty()) ? "Please include a direction after the move command.\n" : "";
				for (char d : args) {
					output += super.state.movePlayer(d);
				}
				output += super.state.getMapString();
			} else if (leadingCommand.equals("speak")) {
				output = "Please include a direction after the speak command.";
				if (!args.isEmpty()) {
					output = super.state.beginDialogue(args.pop());
				}
			} else if (leadingCommand.equals("look")) {
				output = super.state.getLocationDesc();
			} else if (leadingCommand.equals("survey")) {
				output = "You survey the area for changes in elevation.\n" + super.state.getTopoMapString();
			} else if (leadingCommand.equals("clear")) {
				output = "";
				for (int i = 0; i < Config.HEIGHT; i++) {
					output += '\n';
				}
			} else if (leadingCommand.equals("stats")) {
				output = super.state.getPlayerStats();
			} else if (leadingCommand.equals("stuff")) {
				output = super.state.getInv();
			} else if (leadingCommand.equals("save")) {
				output = super.state.save();
			} else if (leadingCommand.equals("exit")) {
				acceptingInput = false;
				super.state.close();
				output = "Bye!";
			}
		} catch (Event e) {
			output = e.update(super.state);
		}
		System.out.println(output + '\n');
	}

	public void start() {
		Scanner in = new Scanner(System.in);
		while (super.acceptingInput) {
			handle(in.nextLine());
		}
		System.exit(0);
	}

}
