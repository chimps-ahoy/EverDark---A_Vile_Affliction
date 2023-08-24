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
		
		UI ui = null;
		if (legacy) {
			System.out.println(drawMenu());
			ui = new TUI();	
		} else {
			ui = new GUI(CFG.getWidth(), CFG.getHeight());
		}
		SwingUtilities.invokeLater(ui::start);	
	}

	public static String drawMenu() {
		String menu = "";
		String title = "EverDark";
		String subtitle = "---A Vile Affliction---";
		String VERS = "v0.1.4G";
		int WIDTH = CFG.getWidth();
		int HEIGHT = CFG.getHeight();
		for (int i = 0; i < HEIGHT-2; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (i == (HEIGHT-2)/2 && j == (WIDTH-title.length())/2) {
					menu += title;
					j += title.length()-1;
				} else if (i == (HEIGHT-2)/2 + 1 && j == (WIDTH-subtitle.length())/2) {
					menu += subtitle;
					j += subtitle.length()-1;
				} else if (i == HEIGHT-3 && j == WIDTH-VERS.length()-1) {
					menu += VERS;
					j += VERS.length()-1;
				} else if (i == 0 || (i == HEIGHT-3 && j > 0 && j < WIDTH-1)) {
					menu += '_';
				} else if (j == 0 || j == WIDTH-1) {
					menu += '|';
				} else {
					menu += " ";
				}
			}
			menu += '\n';
		}
		return menu + "\nWould you like to load from file? (Y/N): ";
	}
}
