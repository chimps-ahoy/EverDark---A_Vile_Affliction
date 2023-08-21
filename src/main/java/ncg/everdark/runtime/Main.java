package ncg.everdark.runtime;

import ncg.everdark.global.Config;
import ncg.everdark.ui.*;

import javax.swing.SwingUtilities;

public class Main {	
	public static void main(String[] args) {
	
		UI ui = null;
		if (args != null && args.length > 0) {
			System.out.println(drawMenu());
			ui = new TUI();	
		} else {
			ui = new GUI();
		}
		SwingUtilities.invokeLater(ui::start);	
	}

	public static String drawMenu() {
		String menu = "";
		String title = "EverDark";
		String subtitle = "---A Vile Affliction---";
		/*for (int i = 0; i <= Config.HEIGHT; i++) {
			menu += '\n';
		}*/
		for (int i = 0; i < Config.HEIGHT-2; i++) {
			for (int j = 0; j < Config.WIDTH; j++) {
				if (i == (Config.HEIGHT-2)/2 && j == (Config.WIDTH-title.length())/2) {
					menu += title;
					j += title.length()-1;
				} else if (i == (Config.HEIGHT-2)/2 + 1 && j == (Config.WIDTH-subtitle.length())/2) {
					menu += subtitle;
					j += subtitle.length()-1;
				} else if (i == Config.HEIGHT-3 && j == Config.WIDTH-Config.VERS.length()-1) {
					menu += Config.VERS;
					j += Config.VERS.length()-1;
				} else if (i == 0 || (i == Config.HEIGHT-3 && j > 0 && j < Config.WIDTH-1)) {
					menu += '_';
				} else if (j == 0 || j == Config.WIDTH-1) {
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
