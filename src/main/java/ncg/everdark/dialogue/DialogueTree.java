package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.NPC;
import ncg.everdark.events.EndOfDialogueEvent;
import ncg.everdark.events.Event;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class DialogueTree implements Serializable {

	private Node head;
	private Node curr;

	public DialogueTree() {
		head = new Node("They don't have anything to say to you.", "", 0, (p,q) -> true, (p,q) -> {}, null);
		curr = head;
	}

	public String beginDialogue(Player p, NPC q) throws Event {
		return progress(p, q, q.curStage());
	}

	public String progress(Player p, NPC q, int response) throws Event {
		String output = "";
		boolean responseFound = false;

		if (curr.children.isEmpty()) {
			output = curr.dialogue;
			curr = head;
			throw new EndOfDialogueEvent(output);
		} 

		for (Node child : curr.children) {
			if (child.i == response && child.req.test(p,q)) {
				curr = child;
				responseFound = true;
				break;
			}
		}

		if (curr.children.isEmpty()) {
			output = curr.dialogue;
			curr = head;
			throw new EndOfDialogueEvent(output);
		} 
		if (responseFound) {
			output = curr.dialogue;
		} else {
			output = "Invalid Selection.";
		}

		for (Node child : curr.children) {
			output += "\n" + child.i + " - " + child.response;
		}
		return output;

	}

	public DialogueTree add(int[] path, String response, String dialogue, Requirement req, Effect localEffect, Event globalEffect) {
		curr = traverse(head, path);
		curr.children.add(new Node(dialogue, response, path[path.length-1], req, localEffect, globalEffect));
		curr = head;
		return this;
	}
	
	public DialogueTree add(int[] path, String response, String dialogue, Requirement req, Effect localEffect) {
		curr = traverse(head, path);
		curr.children.add(new Node(dialogue, response, path[path.length-1], req, localEffect, null));
		curr = head;
		return this;
	}

	public DialogueTree add(int[] path, String response, String dialogue, Requirement req, Event globalEffect) {
		curr = traverse(head, path);
		curr.children.add(new Node(dialogue, response, path[path.length-1], req, (p,q) -> {}, globalEffect));
		curr = head;
		return this;
	}

	public DialogueTree add(int[] path, String response, String dialogue, Requirement req) {
		curr = traverse(head, path);
		curr.children.add(new Node(dialogue, response, path[path.length-1], req, (p,q) -> {}, null));
		curr = head;
		return this;
	}

	public DialogueTree add(int[] path, String response, String dialogue) {
		curr = traverse(head,path);
		curr.children.add(new Node(dialogue, response, path[path.length-1], (p,q) -> true, (p,q) -> {}, null));
		curr = head;
		return this;
	}
	
	private Node traverse(Node start, int[] path) {
		for (int i = 0; i < path.length-1; i++) {
			int dir = path[i];
			start = start.children.get(dir);
		}
		return start;
	}

	private static class Node implements Serializable {
		public List<Node> children;
		public String dialogue;
		public String response;
		public int i;
		public Requirement req;
		public Effect localEffect;
		public Event globalEffect;
			
		public Node(String dialogue, String response, int i, Requirement req, Effect localEffect, Event globalEffect) {
			this.children = new ArrayList<Node>();
			this.dialogue = dialogue;
			this.response = response;
			this. i = i;
			this.req = req;
			this.localEffect = localEffect;
			this.globalEffect = globalEffect;
		}
	}
}
