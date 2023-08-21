package ncg.everdark.ui;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.events.Event;
import ncg.everdark.events.LoadFromFileException;
import ncg.everdark.global.Config;

import java.util.Scanner;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.ArrayDeque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class GUI extends UI {

	private JFrame frame;
	private JEditorPane map;
	private JEditorPane status;
	private JEditorPane inv;
	private JEditorPane console;
	private JTextField in;

	private boolean topographicView;

	private static final Color EVER = new Color(118, 118, 118);
	private static final Color DARK = new Color(25, 25, 30);

	public GUI() {//TODO: HTML and COLOURS!!!
					  //will need to edit Config to include html color tags
		super();
		topographicView = false;

		frame = new JFrame();
		frame.setLayout(new BorderLayout(10, 5));
		frame.setTitle("EverDark - A Vile Affliction " + Config.VERS);
		frame.setIconImage(new ImageIcon("global/logo.png").getImage());//TODO: FIX THIS FILEPATH!!!! ARGHH!!!!!
		frame.setPreferredSize(new Dimension(1000, 800));
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		map = new JEditorPane("plain", "");
		map.setEditable(false);
		prep(map, 20);

		status = new JEditorPane("plain", "");
		status.setEditable(false);
		prep(status, 12);

		inv = new JEditorPane("plain", "");
		inv.setEditable(false);
		prep(inv, 12);

		StringBuilder buffer = new StringBuilder(99);;
		for (int i = 0; i < 99; i++) {
			buffer.append('\n');
		}
		console = new JEditorPane("plain", "Welcome to EverDark.\nWould you like to load from file? (Y/N)" + buffer);
		console.setEditable(false);
		prep(console, 12);
		JScrollPane consoleHost = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		in = new JTextField("What do you do?");
		prep(in, 12);

		in.addFocusListener(new FocusListener() {
											public void focusGained(FocusEvent e) {
												in.setText("");
											}
											public void focusLost(FocusEvent e) {
												in.setText("What do you do?");
											}
										});

		in.addActionListener(ae -> handle(in.getText()));

		add(map, panel, gbc, 0, 0, 1, 1, 0.5, 0.5);
		add(status, panel, gbc, 1, 0 ,1 ,1, 0.25, 0.5);
		add(inv, panel, gbc, 2, 0, 1, 1, 0.25, 0.5);
		add(consoleHost, panel, gbc, 0, 1, 5, 1, 1, 0.7);
		add(in, panel, gbc, 0, 2, 5, 1, 1, 0.005);

		panel.setBackground(EVER);

		frame.add(panel, BorderLayout.CENTER);
	}

	private void add(JComponent j, JPanel p, GridBagConstraints gbc, int x, int y, int width, int height, double weightx, double weighty) {
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		p.add(j, gbc);
	}

	private void prep(JComponent j, int fontSize) {
		j.setOpaque(true);
		j.setBackground(DARK);
		j.setForeground(Color.WHITE);
		j.setFont(new Font("Courier New", Font.PLAIN, fontSize));
	}

	public void start() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void handle(String input) {

		Scanner tokenizer = new Scanner(input.toLowerCase());
		String leadingCommand = (tokenizer.hasNext()) ? tokenizer.next() : "placeholder";
		Deque<Character> args = new ArrayDeque<Character>();
		while (tokenizer.hasNext()) {
			args.add(tokenizer.next().toLowerCase().charAt(0));	
		}
		String output = "Command not recognized.";

		try {
			if (!super.state.initialized()) {
				try {
					output = super.state.ini(input);
				} catch (LoadFromFileException lfle) {
					super.state.stopMusic();
					super.state = lfle.getState();
					super.state.startMusic();
					output = super.state.getLocationDesc();
				}
			} else if (super.state.inDialogue()) { 
				try {
					output = super.state.talk(Integer.parseInt(leadingCommand), args);
				} catch (InputMismatchException|IllegalArgumentException ex) {
					output = "Invalid Selection.";
				}
			} else if (leadingCommand.equals("move")) {
				output = (args.isEmpty()) ? "Please include a direction after the move command.\n" : "";
				for (char d : args) {
					output += super.state.movePlayer(d);
				}
			} else if (leadingCommand.equals("speak")) {
				output = "Please include a direction after the speak command.";
				if (!args.isEmpty()) {
					output = super.state.beginDialogue(args.pop());
				}
			} else if (leadingCommand.equals("look")) {
				output = super.state.getLocationDesc();
			} else if (leadingCommand.equals("survey")) {
				output = "You survey the area for changes in elevation.\n";
				map.setText(super.state.getTopoMapString());
			} else if (leadingCommand.equals("save")) {
				output = super.state.save();
			} else if (leadingCommand.equals("exit")) {
				super.acceptingInput = false;
				super.state.close();
				output = "Bye!";
				System.exit(0);
			}
		} catch (Event e) {
			output = e.update(super.state);
		}
		console.setText(console.getText() + output + '\n');
		if (super.state.initialized()) {
			status.setText(super.state.getPlayerStats());
			inv.setText(super.state.getInv());
		}
		if (topographicView) {
			map.setText(super.state.getTopoMapString());
		} else {
			map.setText(super.state.getMapString());
		}
		in.setText("");
	}


}
