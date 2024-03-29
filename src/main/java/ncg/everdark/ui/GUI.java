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
	private Font font;

	private boolean topographicView;

	private static final Color EVER = new Color(118, 118, 118);
	private static final Color DARK = new Color(25, 25, 30);

	public GUI() {
		super();
		topographicView = false;

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(CFG.getGlobalPath() + "VT323-Regular.ttf")).deriveFont(12f);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
		} catch (IOException|FontFormatException ex) {
			System.out.println("The font could not be found. Please make sure the font file provided with the game is within your set 'global' directory. Quitting...");
			System.exit(1);
		}

		frame = new JFrame();
		frame.setLayout(new BorderLayout(10, 5));
		frame.setTitle("EverDark - A Vile Affliction");
		frame.setIconImage(new ImageIcon(CFG.getGlobalPath() + "logo.png").getImage());
		frame.setPreferredSize(new Dimension(CFG.getWidth(), CFG.getHeight()));
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		map = new JEditorPane("text/html", "");
		map.setEditable(false);
		prep(map, 32);

		status = new JEditorPane("text/html", "");
		status.setEditable(false);
		prep(status, 40);

		inv = new JEditorPane("text/html", "");
		inv.setEditable(false);
		prep(inv, 40);

		StringBuilder buffer = new StringBuilder(99);;
		for (int i = 0; i < 99; i++) {
			buffer.append('\n');
		}

		console = new JEditorPane("plain", "Welcome to EverDark.\nWould you like to load from file? (Y/N)" + buffer);
		console.setEditable(false);
		prep(console, 40);
		JScrollPane consoleHost = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		in = new JTextField("What do you do?");
		prep(in, 40);

		in.addFocusListener(new FocusListener() {
											public void focusGained(FocusEvent e) {
												in.setText("");
											}
											public void focusLost(FocusEvent e) {
												in.setText("What do you do?");
											}
										});

		in.addActionListener(ae -> handle(in.getText()));

		

		add(map, panel, gbc, 0, 0, 1, 1, 0.35, 0.25);
		add(status, panel, gbc, 1, 0 ,1 ,1, 0.25, 0.5);
		add(inv, panel, gbc, 2, 0, 1, 1, 0.37, 0.5);
		add(consoleHost, panel, gbc, 0, 1, 5, 1, 1, 1);
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
		j.setFont(font.deriveFont((float)(CFG.getHeight()/fontSize)));
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
		topographicView = false;

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
				topographicView = true;
			} else if (leadingCommand.equals("drop")) {
				output = (args.isEmpty()) ? "What do you want to drop?" : "";	
				for (char c : args) {
					output += super.state.removeFromPlayer(Character.digit(c,10));
				}
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
			//e.printStackTrace();
		}
		console.setText(console.getText() + input + '\n' + output + '\n');
		updateDisplay();
	}

	private void updateDisplay() {
		status.setText(formatHTML(super.state.getPlayerStats(), CFG.getHeight() / 40));
		inv.setText(formatHTML(super.state.getInv(), CFG.getHeight() / 40));
		if (topographicView) {
			map.setText(formatHTML(super.state.getTopoMapString(), CFG.getHeight() / 32));
		} else {
			map.setText(formatHTML(super.state.getMapString(), CFG.getHeight() / 32));
		}
		in.setText("");
	}
	
	private static String formatHTML(String s, int size) {
		return "<html><body><p style =\"font-family: 'VT323', monospace; font-size:" + size +
		  ";color:#FFFFFF;\">" + s.replace("\n", "<br>") + "</p></body></html>";
	}

}
