package ncg.chimpsahoy.everdark;
import java.io.*;
import java.util.Scanner;

public class Player extends Entity {

	private static final int MAX_HP = 100;

	public Player(String name, int maxHp, int hp, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, int id) {
		super(name, maxHp, hp, str, endur, dex, swift, iq, wil, charm, intim, perc, appearMod, id);
	}

	public static Player loadFromFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream("saves/" + path);
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
		System.out.println("\tWelcome to EverDark. The Character Creation Process will begin now. You are given 100 total Stat Points to assign amongst your characters stats. The stats will be shown one-by-one, and you can't go back! So, " +
					"plan carefully.\n The available stats are: Strength, Endurance, Dexterity, Swiftness, Intelligence, Willpower, Charisma, Intimidation, and Perception.");
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

		System.out.print("Would you like more info about these stats? (Y/N): ");
		if (in.next().toLowerCase().charAt(0) == 'y') {
			System.out.println("\nStrength - Your character's physical prowess. This is a measure of your short-twitch muscle fibres- quick bursts of force.\n" +
							"Endurance - In contrast to Strength; your character's slow-twitch muscle fibres- how long they can act before becoming fatigued.\n" +
							"Dexterity - The strength of your character's little muscles, rather than the big ones. This is your fine motor skill. It measures how precisely you can perform an action.\n" +
							"Swiftness - This is a measure of how quickly your character can perform an action.\n" +
							"Intelligence - Your character's mental ability. This is their aptitude to learn new things, and apply what they know to solve problems.\n" +
							"Willpower - This is a mental analogue to Endurance. It is a measure of how much mental strain your character can take. Higher willpower also allows you to resist charm and intimidation.\n" +
							"Charisma - Your character's social ability- how easily they get along with people and how much they appeal to them.\n" +
							"Intimidation - While Charisma can allow you to convince people to do things of their own volition, Intimidation is your ability to impose your will on others by force.\n" +
							"Perception - This is a mental analogue to Swiftness and Dexterity. It is your character's precision and speed at observing things in their environment.\n");
		}

		System.out.print("\nPlease name your character: ");
		in.next();
		inName = in.nextLine();

		while (!finished) {
			System.out.print("\nHow many points would you like to put into Strength? (" + availablePoints + " available): ");
			try {
				inStr = in.nextInt();
				if (finished = ((inStr <= availablePoints) && inStr >= 0)) {
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
				if (finished = ((inEndur <= availablePoints) && inEndur >= 0)) {
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
				if (finished = ((inDex <= availablePoints) && inDex >= 0)) {
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
				inSwift = in.nextInt();
				if (finished = ((inSwift <= availablePoints) && inSwift >= 0)) {
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
				if (finished = ((inIq <= availablePoints) && inIq >= 0)) {
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
				if (finished = ((inWil <= availablePoints) && inWil >= 0)) {
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
				if (finished = ((inCharm <= availablePoints) && inCharm >= 0)) {
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
				if (finished = ((inIntim <= availablePoints) && inIntim >= 0)) {
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
				if (finished = ((inPerc <= availablePoints) && inPerc >= 0)) {
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
		System.out.println();
		return new Player(inName, MAX_HP, MAX_HP, inStr, inEndur, inDex, inSwift, inIq, inWil, inCharm, inIntim, inPerc, inAppearance, 0);
	}



}
