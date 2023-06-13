package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Config {

	private static final int n = 4;
	private static String[] values = new String[n];
	public static final String MUSIC_PATH;
	public static final String SAVE_PATH;
	public static final int WIDTH;
	public static final int HEIGHT;

	static  {
		File f = null;
		Scanner lineScanner = null;
		try {
			f = new File("config.txt");
			lineScanner = new Scanner(f);
			for (int i = 0; i<n; i++) {
				Scanner valueGetter = new Scanner(lineScanner.nextLine());
				valueGetter.useDelimiter(": ");
				valueGetter.next();
				values[i] = valueGetter.next();
			}
		} catch (Exception e) {
			values[0] = "20";
			values[1] = "20";
			values[2] = "saves/";
			values[3] = "global/music";
		}
		WIDTH = Integer.parseInt(values[0]);
		HEIGHT = Integer.parseInt(values[1]);
		SAVE_PATH = values[2];
		MUSIC_PATH = values[3];
	}

}
