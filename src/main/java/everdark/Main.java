package ncg.chimpsahoy.everdark;

public class Main {
	public static void main(String[] args) {
		try {
			for (int i = 0; i <= 5; i++) {
				printMap(i, 3);
				Thread.sleep(1000);
				System.out.print("\n");
			}
		} catch (InterruptedException e) {}	
	}
	public static void printMap(int x, int y) {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				int z = (int)(10*Math.random());
				if (j == 0 || j == 15) { System.out.print("|"); }
				else if (i == 0 || i == 15) { System.out.print("- "); }
				else if (i == y && j == x) { System.out.print("@ "); }
				else if (z%3==0) { System.out.print("' "); }
				else if (z%3==1) { System.out.print(", "); }
				else {
					System.out.print("\" ");
				}
			}
			System.out.print('\n');
		}

	}
}
