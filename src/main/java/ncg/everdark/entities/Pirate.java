package ncg.everdark.entities;

import ncg.everdark.dialogue.DialogueTree;

public class Pirate extends NPC {
		
		private static int count = 2000;
		private static final int RANGE = 2000;

		public Pirate(int lvl) { 
			super("pirate", lvl*10, lvl*10, lvl*2, lvl*3, lvl, lvl, 2, 2, 2, lvl*3, 2, 'p', (count++%RANGE) + RANGE);
			this.setDialogue(new DialogueTree().add("\"Yar, mate!\"", (p,q) -> p.getOrigin() == Player.Origin.TANIERE)
					.add("\"Yer far from home, landlubber!\""));
		}

	}

