package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("_______________________________________________________");
		System.out.println("|                        EverDark                     |");
		System.out.println("|                 ---A Vile Affliction---             |");
		System.out.println("|______________________________________________v0.0.2_|");
		int[][] topography = new int[16][16];
		Entity[][] entities = new Entity[16][16];
		char[][] features = new char[16][16];
		Entity player = Player.characterCreation();
		Map m = new Map(0, "test", topography, features, entities, 16, 16);
		m.spawnPlayer(player, 5, 5);
		GameState g = new GameState(m);
		InputHandler IOh = new InputHandler(g);
		Scanner in = new Scanner(System.in);

		while (IOh.acceptingInput()) {
			System.out.println(IOh.handle(in.nextLine()));
		}

	}

}
