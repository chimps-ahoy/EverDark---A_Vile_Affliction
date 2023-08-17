package ncg.everdark.entities;

import ncg.everdark.dialogue.DialogueTree;

public class Pirate extends NPC {
		
		public Pirate(int lvl) { 
			super("pirate", lvl*2, lvl*3, lvl, lvl, 2, 2, 2, lvl*3, 2, 'p', Entity.Race.HUMAN);
			this.setDialogue(new DialogueTree().add("\"Yar, mate!\"", (p,q) -> p.getOrigin() == Player.Origin.TANIERE)
					.add("\"Yer far from home, landlubber!\""));
		}

	}

