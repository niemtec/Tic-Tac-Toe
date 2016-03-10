import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AppWindow {
	//Padding for some of the board elements (for easier modification)
	final byte paddingSize = 5;
	//Create gameGrid buttons
	public static XOButton button[] = new XOButton[9];
	//Turn indicator label
	public static JLabel turnIndicatorLabel = new JLabel();

	//JFrame Constructor
	public AppWindow() throws IOException {
		//appWindowFrame Properties, main window containing all elements
		JFrame appWindowFrame = new JFrame();
		appWindowFrame.setVisible(true);
		appWindowFrame.setSize(410, 510);
		appWindowFrame.setBackground(Color.white);
		appWindowFrame.setTitle("TicTacToe");
		appWindowFrame.setResizable(false);
		appWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindowFrame.setLocationRelativeTo(null);	//centers the window

		//topPanel Properties (will contain turn indicator)
		JPanel topPanel = new JPanel();
		topPanel.setVisible(true);
		topPanel.setSize(400, 50);
		topPanel.add(GUI.getTurnIndicatorLabel());
		topPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));

		//gameGridPanel Properties (will contain grid buttons)
		JPanel gameGridPanel = new JPanel();
		gameGridPanel.setVisible(true);
		gameGridPanel.setSize(400, 400);
		gameGridPanel.setLayout(new GridLayout(3, 3));
		gameGridPanel.setBorder(new EmptyBorder(0, paddingSize, 0, paddingSize));

		//Populate the game grid with XO buttons
		for (int i = 0; i < 9; i++) {
			button[i] = new XOButton();
			gameGridPanel.add(button[i]);
		}

		//bottomPanel Properties (will contain various buttons)
		JPanel bottomPanel = new JPanel();
		bottomPanel.setVisible(true);
		bottomPanel.setSize(400, 50);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
		bottomPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));
		//Add buttons to the bottom panel
		JButton helpButton = new JButton();
		bottomPanel.add(helpButton);
		JButton resetButton = new JButton();
		bottomPanel.add(resetButton);
		JButton exitButton = new JButton();
		bottomPanel.add(exitButton);

		//TurnIndicator Label
		turnIndicatorLabel.setFont(turnIndicatorLabel.getFont().deriveFont(20.0f));

		//Add all the components to the frame (border layout)
		appWindowFrame.setLayout(new BorderLayout(paddingSize, paddingSize));
		appWindowFrame.add(topPanel, BorderLayout.NORTH);
		appWindowFrame.add(gameGridPanel, BorderLayout.CENTER);
		appWindowFrame.add(bottomPanel, BorderLayout.SOUTH);

		//Button Action Listeners
		//Help Button
		helpButton.setBackground(Color.white);
		helpButton.setText("Help");
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.showHelp();	
			}
		});

		//Reset Button
		resetButton.setBackground(Color.white);
		resetButton.setText("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetGame();
			}
		});

		//Exit Button
		exitButton.setBackground(Color.white);
		exitButton.setText("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.shutdownApplication();
			}
		});
	}

	/**
	 * Method which resets the entire game back to its original state. Clearing all icons and values from the game grid.
	 * Next player is then generated randomly if the game is continued.
	 **/
	public static void resetGame() {
		try {
			clearGameGridArray();
			clearBoardIcons();
			enableGridButtons();
			GUI.setTurnIndicatorLabel("Press Start When Ready");
			Main.movementCount = 1;
			Main.chooseFirstPlayer();
			//If AI is on and its turn is due, make the move
			if (Main.aiEnabled && Main.currentPlayer == 2) {
				AI.aiBasicMove();
				Main.currentPlayer++;
				Validation.validateMove(XOButton.lastIconCheck);
			}
		} catch (NullPointerException ignore) {}
	}

	//Clear all saved records from the gameGrid (restore them back to original "." state)
	public static void clearGameGridArray() {
		Arrays.fill(Main.gameGrid, ".");
	}

	//Clear all icons from the game grid
	public static void clearBoardIcons() {
		for (int i = 0; i < 9; i++) {
			button[i].setIcon(null);
		}
	}

	//Enables all action listeners on the game grid
	public static void enableGridButtons() {
		for (int i = 0; i < 9; i++) {
			AppWindow.button[i].setEnabled(true);
		}
	}
}