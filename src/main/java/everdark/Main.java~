package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("        EverDark       ");
		System.out.println("---A Vile Affliction---");
		System.out.println("                                              v0.0.1");
		int[][] topography = new int[3][3];
		Entity[][] entities = new Entity[3][3];
		char[][] features = new char[3][3];
		Entity player = Player.characterCreation();
		Map m = new Map(0, "test", topography, features, entities, 3, 3);
		m.spawnPlayer(player, 1, 1);
		GameState g = new GameState(m);
		InputHandler IOh = new InputHandler(g);
		Scanner in = new Scanner(System.in);

		while (IOh.acceptingInput()) {
			System.out.println(IOh.handle(in.nextLine()));
		}

	}

}
