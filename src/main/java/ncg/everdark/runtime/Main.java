package ncg.everdark.runtime;

import ncg.everdark.ui.*;

import javax.swing.SwingUtilities;

public class Main {	
	public static void main(String[] args) {
		
		boolean legacy = (args != null && args.length > 0);
		try {
			CFG.ini("config.txt", legacy);
		} catch (Exception e) {
			e.printStackTrace();
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
		/*for (int i = 0; i <= Config.HEIGHT; i++) {
			menu += '\n';
		}*/
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
