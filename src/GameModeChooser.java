import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameModeChooser {
	int paddingSize = 5;

	private JComboBox gameModeComboBox = new JComboBox();
	private JComboBox playerModeComboBox = new JComboBox();

	public GameModeChooser() {
		//GameModeChooser frame properties
		final JFrame gameModeChooserFrame = new JFrame();
		gameModeChooserFrame.setSize(300, 220);
		gameModeChooserFrame.setBackground(Color.white);
		gameModeChooserFrame.setTitle("Game Mode Chooser");
		gameModeChooserFrame.setResizable(false);
		gameModeChooserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameModeChooserFrame.setLocationRelativeTo(null);

		//panel properties
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
		masterPanel.setSize(300, 220);
		masterPanel.setBorder(new EmptyBorder(10,10,10,10));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));

		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		middlePanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));


		//gameModeLabel properties
		JLabel gameModeLabel = new JLabel();
		gameModeLabel.setText("Choose your preferred game mode");
		gameModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//playerModeLabel properties
		JLabel playerModeLabel = new JLabel();
		playerModeLabel.setText("Choose who should go first");
		playerModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		//gameModeComboBox properties
		//selected option is processed when user presses start
		String[] gameModeOptions = {"Player vs Player", "Player vs AI"};
		gameModeComboBox = new JComboBox(gameModeOptions);


		//playerModeComboBox properties
		String[] playerModeOptions = {"Player 1", "Player 2 (or AI)"};
		playerModeComboBox = new JComboBox(playerModeOptions);


		//Buttons
		JButton startButton = new JButton();
		startButton.setBackground(Color.white);
		startButton.setText("Start");
		//Captures IOException
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Assign combobox values to main variables governing the game
					Main.gameMode = (String) gameModeComboBox.getSelectedItem();
					Main.playerMode = (String) playerModeComboBox.getSelectedItem();
					gameModeChooserFrame.dispose();
					Main.startApplication();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton helpButton = new JButton();
		helpButton.setBackground(Color.white);
		helpButton.setText("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.showHelp();
			}
		});

		JButton quitButton = new JButton();
		quitButton.setBackground(Color.white);
		quitButton.setText("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.shutdownApplication();
			}
		});

		//Add items to the main panel
		topPanel.add(gameModeLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(gameModeComboBox);
		middlePanel.add(playerModeLabel);
		middlePanel.add(Box.createVerticalStrut(10));
		middlePanel.add(playerModeComboBox);
		bottomPanel.add(startButton);
		bottomPanel.add(helpButton);
		bottomPanel.add(quitButton);

		masterPanel.add(topPanel);
		masterPanel.add(middlePanel);
		masterPanel.add(bottomPanel);

		topPanel.setVisible(true);
		middlePanel.setVisible(true);
		bottomPanel.setVisible(true);
		masterPanel.setVisible(true);
		gameModeChooserFrame.setVisible(true);
		gameModeChooserFrame.add(masterPanel);
	}
}