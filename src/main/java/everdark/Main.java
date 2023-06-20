package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Main {	
	public static void main(String[] args) {
	
		System.out.print(drawMenu());
		
		//game runtime
		GameState g = new GameState(Config.MAIN_MENU);
		InputHandler ioHandler = new InputHandler(g);
		Scanner in = new Scanner(System.in);
		while (ioHandler.acceptingInput()) {
			System.out.println(ioHandler.handle(in.nextLine()));
		}
		System.exit(0);
	}

	public static String drawMenu() {
		String menu = "";
		String title = "EverDark";
		String subtitle = "---A Vile Affliction---";
		for (int i = 0; i <= Config.HEIGHT; i++) {
			menu += '\n';
		}
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
