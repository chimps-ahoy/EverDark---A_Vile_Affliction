package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Player extends Entity {

	private static final int MAX_HP = 100;

	public Player(String name, int maxHp, int hp, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, int id) {
		super(name, maxHp, hp, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, id);
	}

	public static Player loadFromFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Player loaded = (Player)(ois.readObject());
		ois.close();
		fis.close();
		return loaded;

	}

	public void saveToFile() throws FileNotFoundException, IOException {

		String fileName = super.getName() + ".ed";
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
		fos.close();	

	}

	public static Player characterCreation() { //TODO: this is so ugly
		Scanner in = new Scanner(System.in);
		int availablePoints = 100;
		System.out.println("Welcome to EverDark. The Character Creation Process will begin now. Please refer to any available manuals and documentation to aid you during this process. " +
				"You can't go back! So, make sure you know where you're putting your points before you begin.\n");
		String inName = "";
		int inStr = 0;
		int inEndur = 0;
		int inDex = 0;
		int inSwift = 0;
		int inIq = 0;
		int inWil = 0;
		int inCharm = 0;
		int inIntim = 0;
		int inPerc= 0;
		char inAppearance = 'c';
		boolean finished = false;
		
		System.out.print("Please name your character: ");
		inName = in.nextLine();

		while (!finished) {
			System.out.print("\nHow many points would you like to put into Strength? (" + availablePoints + " available): ");
			try {
				inStr = in.nextInt();
				if (finished = (inStr <= availablePoints)) {
					availablePoints -= inStr;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Endurance? (" + availablePoints + " available): ");
			try {
				inEndur = in.nextInt();
				if (finished = (inEndur <= availablePoints)) {
					availablePoints -= inEndur;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}	
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Dexterity? (" + availablePoints + " available): ");
			try {
				inDex = in.nextInt();
				if (finished = (inDex <= availablePoints)) {
					availablePoints -= inDex;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Swiftness? (" + availablePoints + " available): ");
			try {
				inStr = in.nextInt();
				if (finished = (inSwift <= availablePoints)) {
					availablePoints -= inSwift;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Intelligence? (" + availablePoints + " available): ");
			try {
				inIq = in.nextInt();
				if (finished = (inIq <= availablePoints)) {
					availablePoints -= inIq;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Willpower? (" + availablePoints + " available): ");
			try {
				inWil = in.nextInt();
				if (finished = (inWil <= availablePoints)) {
					availablePoints -= inWil;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Charisma? (" + availablePoints + " available): ");
			try {
				inCharm = in.nextInt();
				if (finished = (inCharm <= availablePoints)) {
					availablePoints -= inCharm;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Intimidation? (" + availablePoints + " available): ");
			try {
				inIntim = in.nextInt();
				if (finished = (inIntim <= availablePoints)) {
					availablePoints -= inIntim;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}
		finished = false;
		while (!finished) {
			System.out.print("\nHow many points would you like to put into Perception? (" + availablePoints + " available): ");
			try {
				inPerc = in.nextInt();
				if (finished = (inPerc <= availablePoints)) {
					availablePoints -= inPerc;
				} else {
					System.out.println("You don't have enough points for that!");
				}
			} catch (Exception e) {
				System.out.println("Please make sure your input is an integer.");
				in.next();
			}
		}	
		finished = false;
		while (!finished) {
			System.out.print("\nFinally, what letter would you like to represent you? Please choose a letter from the standard ASCII Latin alphabet. Case does not matter: ");
			inAppearance = in.next().charAt(0);
			if (!(finished = (Character.toUpperCase(inAppearance) >= 'A' && Character.toUpperCase(inAppearance) <= 'Z'))) {
				System.out.println("That is not a valid character.");
			} 
		}
		return new Player(inName, MAX_HP, MAX_HP, inStr, inEndur, inDex, inSwift, inIq, inWil, inCharm, inIntim, inPerc, inAppearance, 0);
	}



}
