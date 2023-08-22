package ncg.everdark.ui;

import ncg.everdark.gamedata.GameState;
import ncg.everdark.events.Event;
import ncg.everdark.events.LoadFromFileException;

import java.util.Scanner;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.ArrayDeque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;

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

	public GUI(int x, int y) {
		super();
		topographicView = false;

		frame = new JFrame();
		frame.setLayout(new BorderLayout(10, 5));
		frame.setTitle("EverDark - A Vile Affliction");
		frame.setIconImage(new ImageIcon(CFG.getGlobalPath() + "logo.png").getImage());
		frame.setPreferredSize(new Dimension(x, y));
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		map = new JEditorPane("text/html", "");
		map.setEditable(false);
		prep(map, 24);

		status = new JEditorPane("text/html", "");
		status.setEditable(false);
		prep(status, 20);

		inv = new JEditorPane("text/html", "");
		inv.setEditable(false);
		prep(inv, 20);

		StringBuilder buffer = new StringBuilder(99);;
		for (int i = 0; i < 99; i++) {
			buffer.append('\n');
		}
		console = new JEditorPane("plain", "Welcome to EverDark.\nWould you like to load from file? (Y/N)" + buffer);
		console.setEditable(false);
		prep(console, 20);
		JScrollPane consoleHost = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		in = new JTextField("What do you do?");
		prep(in, 20);

		in.addFocusListener(new FocusListener() {
											public void focusGained(FocusEvent e) {
												in.setText("");
											}
											public void focusLost(FocusEvent e) {
												in.setText("What do you do?");
											}
										});

		in.addActionListener(ae -> handle(in.getText()));

		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, new File(CFG.getGlobalPath() + "VT323-Regular.ttf")).deriveFont(12f);
			System.out.println(f);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
		} catch (IOException|FontFormatException ex) {
			ex.printStackTrace();
			System.exit(1);
		}

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

	private void prep(JComponent j, float fontSize) {
		j.setOpaque(true);
		j.setBackground(DARK);
		j.setForeground(Color.WHITE);
		try {
			j.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(CFG.getGlobalPath() + "VT323-Regular.ttf")).deriveFont(fontSize));
		} catch (IOException|FontFormatException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
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
			status.setText(formatHTML(super.state.getPlayerStats(), 20));
			inv.setText(formatHTML(super.state.getInv(), 20));
		}
		if (topographicView) {
			map.setText(formatHTML(super.state.getTopoMapString(), 24));
		} else {
			map.setText(formatHTML(super.state.getMapString(), 24));
		}
		in.setText("");
	}
	
	private static String formatHTML(String s, int size) {
		return "<html><body><p style =\"font-family: 'VT323', monospace; font-size:" + size +
		  ";color:#FFFFFF;\">" + s.replace("\n", "<br>") + "</p></body></html>";
	}

}
