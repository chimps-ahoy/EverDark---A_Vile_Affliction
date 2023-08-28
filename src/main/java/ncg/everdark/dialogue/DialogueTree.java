package ncg.everdark.dialogue;

import ncg.everdark.entities.Player;
import ncg.everdark.entities.NPC;
import ncg.everdark.events.Event;
import ncg.everdark.events.Consequence;

import java.io.Serializable;
import java.util.List;
import java.util.Deque;
import java.util.ArrayList;

public class DialogueTree implements Serializable {

	private Node head;
	private Node curr;

	public DialogueTree() {
		head = new Node("They don't have anything to say to you.", "", 0, null, null, null);
		curr = head;
	}

	public String beginDialogue(Player p, NPC q) throws Event {
		return progress(p, q, q.curStage(), null);
	}

	public String progress(Player p, NPC q, int response, Deque<Character> args) throws Event {
		String output = "";
		boolean responseFound = false;
		boolean lying = (args != null && args.contains('l'));

		if (curr.children.isEmpty()) {
			this.throwExits(lying);//exits early if we're at an endpoint
		}

		for (Node child : curr.children) {
			if (responseFound = child.i == response && child.req.test(p,q)) {
				curr = child;
				break;
			}
		}

		if (curr.children.isEmpty()) {
			this.throwExits(lying);//^^^
		}

		output = (responseFound) ? curr.dialogue : "Invalid Selection.";

		Node prev = new Node("","",-1,null,null,null);
		for (Node child : curr.children) {
			output += (child.i == prev.i || !child.req.test(p,q)) ? "" : "\n" + child.i + " - " + child.response;
			prev = child;
		}

		if (curr.extraEffect != null && !args.contains('l')) {
			throw new Event(output, curr.extraEffect, curr.defaultEffect);
		} else if (curr.defaultEffect != null) {
			throw new Event(output, curr.defaultEffect);
		} else {
			return output;
		}
	}

	private void throwExits(boolean lying) throws Event {
			Event end;
			if (curr.extraEffect != null && !lying) {
				end = new Event(curr.dialogue, curr.defaultEffect, curr.extraEffect, (state) -> {state.endDialogue();
																															return "";
																														  });
			} else if (curr.defaultEffect != null) {
				end = new Event(curr.dialogue, curr.defaultEffect, (state) -> {state.endDialogue();
																									return "";
																								  });
			} else {
				end = new Event(curr.dialogue, (state) -> {state.endDialogue();
																		 return "";
																	   });
			}
			curr = head;
			throw end; 
	}

	public DialogueTree add(int[] path, String response, String dialogue, Requirement req, Consequence defaultEffect, Consequence extraEffect) {
		curr = traverse(head, path);
		curr.children.add(new Node(dialogue, response, path[path.length-1], req, defaultEffect, extraEffect));
		curr = head;
		return this;
	}

	public DialogueTree add(int[] path, String response, String dialogue, Consequence defaultEffect, Consequence extraEffect) {
		return this.add(path, response, dialogue, (p,q) -> true, defaultEffect, extraEffect);
	}
	
	public DialogueTree add(int[] path, String response, String dialogue, Requirement req, Consequence defaultEffect) {
		return this.add(path, response, dialogue, req, defaultEffect, null);
	}

	public DialogueTree add(int[] path, String response, String dialogue, Requirement req) {
		return this.add(path, response, dialogue, req, null, null);
	}

	public DialogueTree add(int[] path, String response, String dialogue, Consequence defaultEffect) {
		return this.add(path, response, dialogue, (p,q) -> true, defaultEffect, null);
	}

	public DialogueTree add(int[] path, String response, String dialogue) {
		return this.add(path, response, dialogue, (p,q) -> true, null, null);
	}

	public DialogueTree add(String dialogue, Requirement req, Consequence defaultEffect) {//adding a start with a requirement and effect
		return this.add(new int[] {0}, "", dialogue, req, defaultEffect, null);
	}

	public DialogueTree add(String dialogue, Requirement req) {//adding a start with requirement
		return this.add(new int[] {0}, "", dialogue, req, null, null);
	}

	public DialogueTree add(String dialogue, Consequence defaultEffect) {//adding a start iwht effect
		return this.add(new int[] {0}, "", dialogue, (p,q) -> true, defaultEffect, null);
	}

	public DialogueTree add(String dialogue) {//adding a basic start
		return this.add(new int[] {0}, "", dialogue, (p,q) -> true, null, null);
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
		public Consequence defaultEffect;
		public Consequence extraEffect;

		public Node(String dialogue, String response, int i, Requirement req, Consequence defaultEffect, Consequence extraEffect) {
			this.children = new ArrayList<Node>();
			this.dialogue = dialogue;
			this.response = response;
			this.i = i;
			this.req = req;
			this.defaultEffect = defaultEffect;
			this.extraEffect = extraEffect;		
		}
	}
}
