package ncg.everdark.runtime;

import ncg.everdark.gamedata.*;
import ncg.everdark.ui.*;

import javax.swing.SwingUtilities;

public class Main {	
	public static void main(String[] args) {

		GameBuilder.buildGame();//remove from release
										
		boolean legacy = false;
		String config = "config.txt";
		String gamefile = "EverDark.game";
		
		if (args != null && args.length > 0) {
			legacy = (args[0].toLowerCase().equals("tui"));
			config = (args.length > 1) ? args[1] : "config.txt";
			gamefile = (args.length > 2) ? args[2] : "EverDark.game";
		}

		try {
			CFG.ini(config, legacy);
		} catch (Exception e) {
			System.out.println("Config file not found or formatted incorrectly. Please check your config.");
			System.exit(-1);
		}
		try {
			GameState.loadGame(CFG.getGlobalPath() + gamefile);
		} catch (Exception e) {
			System.out.println("Game data could not be loaded. Please ensure your Global directory is set correctly in config.txt and that it contains the .game file");
			//e.printStackTrace();
			System.exit(-1);
		}
		UI ui = (legacy) ? new TUI() : new GUI(CFG.getWidth(), CFG.getHeight());
		SwingUtilities.invokeLater(ui::start);	
	}
}
