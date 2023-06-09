package ncg.chimpsahoy.everdark;
import java.util.LinkedList;
import java.io.*;

public abstract class Entity implements Serializable{

	private String name;
	//Physical stats TODO: hp -> bodypart system
	private int maxHp;
	private int hp;
	private int str;
	private int endur;
	private int dex;
	private int swift;
	//Mental stats
	private int iq; //couldnt call it int because that's a protected keyword. this is intelligence
	private int wil;
	private int charm;
	private int intim;//intimidation
	private int perc;//perception
	//----------------------------
	private final char APPEAR_MOD; //the appearance modifier. the basic char for their appearance before it is affected by stuff like starvation
	private char appearance;
	//private ??? origin, the place they came from PROBABLY stored as Enum or String, but Enum >> faster
	
	private int dialogueStage;

	private final int ID;//each subclass of Entity will contain a static variable to count the instances of the class and automatically assign IDs. Unique NPCs
			     //which have their own class, will have a set ID, but this must be decided so we don't get bad overlap. I think the player will have ID = 0,
			     //and for NPCs we can count up from there. For generic enemies which will have a static counter, we can have that counter begin at 1k for one Entity
			     //and 2k for the next etc.
	public Entity(String name, int maxHp, int hp, int str, int endur, int dex, int swift, int iq, int wil, int charm, int intim, int perc, char appearMod, int id) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = hp;
		this.str = str;
		this.endur = endur;
		this.dex = dex;
		this.swift = swift;
		this.iq = iq;
		this.wil = wil;
		this.charm = charm;
		this.intim = intim;
		this.perc = perc;
		APPEAR_MOD = appearMod;
		ID = id;
		dialogueStage = 0;
		appearance = (str + endur >= dex + swift) ? (Character.toUpperCase(APPEAR_MOD)) : (Character.toLowerCase(APPEAR_MOD));

	} 

	public String beginDialogue() throws EndOfDialogueException {
		throw new EndOfDialogueException("They don't have anything to say to you.");
	}

	public String talk(int response, LinkedList<Character> args, Entity player) throws EndOfDialogueException {
		throw new EndOfDialogueException("They don't have anything to say to you.");
	}

	public boolean equals(Entity other) {
		return other != null && this.ID == other.ID;
	}

	public String toString() {
		return "" + ((str + endur >= dex + swift) ? (Character.toUpperCase(APPEAR_MOD)) : (Character.toLowerCase(APPEAR_MOD)));
	}

	public String stats() {
		return "Name: " + name + " - " + this + "\nStr: " + str + "\nEndur: " + endur + "\nDex; " + dex + "\nSwift: " + swift + "\nInt: " + iq + "\nWill: " + wil
		       + "\nCharm: " + charm + "\nIntim: " + intim + "\nPerc: " + perc;	
	}

	public String getName() {
		return name;
	}
	
	public int getWil() {
		return wil;
	}

	public int getCharm() {
		return charm;
	}

	public int getPerc() {
		return perc;
	}

	public int curStage() {
		return dialogueStage;
	}

	public void setStage(int stage) {
		dialogueStage = stage;
	}
}
