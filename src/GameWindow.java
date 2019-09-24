import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class GameWindow {
	static XOButton[] button = new XOButton[9];
	private static JLabel indicatorLabel = new JLabel();

	//JFrame Constructor
	GameWindow() {
		//windowFrame Properties, main window containing all elements
		JFrame windowFrame = new JFrame();
		windowFrame.setSize(410, 510);
		windowFrame.setBackground(Color.white);
		windowFrame.setResizable(false);
		windowFrame.setLocationRelativeTo(null);	//centers the window
		windowFrame.setTitle("TicTacToe");
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setVisible(true);

		//indicatorPanel Properties (will contain turn indicator)
		JPanel indicatorPanel = new JPanel();
		indicatorPanel.setSize(400, 50);
		//Padding for some of the board elements (for easier modification)
		byte paddingSize = 5;
		indicatorPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));
		indicatorPanel.add(getIndicatorLabel());
		indicatorPanel.setVisible(true);

		//centralGamePanel Properties (will contain grid buttons)
		JPanel centralGamePanel = new JPanel();
		centralGamePanel.setSize(400, 400);
		centralGamePanel.setLayout(new GridLayout(3, 3));
		centralGamePanel.setBorder(new EmptyBorder(0, paddingSize, 0, paddingSize));
		centralGamePanel.setVisible(true);

		//Populate the game grid with XO buttons
		for (int i = 0; i < 9; i++) {
			button[i] = new XOButton();
			centralGamePanel.add(button[i]);
		}

		//southButtonPanel Properties (will contain various buttons)
		JPanel southButtonPanel = new JPanel();
		southButtonPanel.setSize(400, 50);
		southButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
		southButtonPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));

		JButton helpButton = new JButton();
		JButton resetButton = new JButton();
		JButton exitButton = new JButton();

		southButtonPanel.add(helpButton);
		southButtonPanel.add(resetButton);
		southButtonPanel.add(exitButton);

		southButtonPanel.setVisible(true);

		//TurnIndicator label properties
		indicatorLabel.setFont(indicatorLabel.getFont().deriveFont(20.0f));

		//Add all the components to the frame (border layout)
		windowFrame.setLayout(new BorderLayout(paddingSize, paddingSize));
		windowFrame.add(indicatorPanel, BorderLayout.NORTH);
		windowFrame.add(centralGamePanel, BorderLayout.CENTER);
		windowFrame.add(southButtonPanel, BorderLayout.SOUTH);

		//Button Action Listeners
		//Help Button
		helpButton.setBackground(Color.white);
		helpButton.setText("Help");
		helpButton.addActionListener(e -> displayHelp());

		//Reset Button
		resetButton.setBackground(Color.white);
		resetButton.setText("Reset");
		resetButton.addActionListener(e -> resetGame());

		//Exit Button
		exitButton.setBackground(Color.white);
		exitButton.setText("Exit");
		exitButton.addActionListener(e -> TicTacToe.shutdownApplication());
	}

	/**
	 * Method which resets the entire game back to its original state. Clearing all icons and values from the game grid.
	 * Next player is then generated randomly if the game is continued.
	 **/

	private static void resetGame() {
		try {
			clearGameGridArray();
			clearBoardIcons();
			enableGridButtons();
			setIndicatorLabel("Press Start When Ready");
			TicTacToe.movementCount = 1;
			TicTacToe.chooseFirstPlayer();
			//If computer is on and its turn is due, make the move
			if (TicTacToe.computerPlayerEnabled && TicTacToe.currentPlayer == 2) {
				ComputerPlayer.computerMove();
				TicTacToe.currentPlayer++;
				Validation.checkMove(XOButton.lastIconCheck);
			}
		} catch (NullPointerException ignore) {}
	}

	//Clear all saved records from the gameGrid (restore them back to original "." state)
	private static void clearGameGridArray() {
		Arrays.fill(TicTacToe.gameGrid, ".");
	}

	static void clearBoardIcons() {
		for (int i = 0; i < 9; i++) {
			button[i].setIcon(null);
		}
	}

	private static void enableGridButtons() {
		for (int i = 0; i < 9; i++) {
			GameWindow.button[i].setEnabled(true);
		}
	}

	static void updateTurnIndicatorLabel() {
		String secondPlayerName;
		int previousPlayer = TicTacToe.currentPlayer%2;

		//Determine what to call the second player based on the game mode
		if (TicTacToe.computerPlayerEnabled) {
			secondPlayerName = "Computer";
		} else {
			secondPlayerName = "Player 2";
		}

		if (previousPlayer == 1) {
			setIndicatorLabel("Current Turn: Player 1");
		} else if (previousPlayer == 0) {
			setIndicatorLabel("Current Turn: " + secondPlayerName);
		}
	}

	private static JLabel getIndicatorLabel() {
		return GameWindow.indicatorLabel;
	}

	static void setIndicatorLabel(String label) {
		GameWindow.indicatorLabel.setText(String.valueOf(label));
	}

	/**
	 * Contains all the graphical elements outside of the main app window and game chooser (popups, messages etc)
	 **/
	static void drawDetectedMessage() {
		int reply = JOptionPane.showConfirmDialog(null, "It's a draw! \n" +
				  "\n Would you like to play again?", "Draw", JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			GameWindow.resetGame();
			TicTacToe.movementCount = 0;	//prevents issues with O being set
		} else {
			TicTacToe.shutdownApplication();
		}
	}

	static void winnerDetectedMessage(String winnerName) {
		int reply = JOptionPane.showConfirmDialog(null, "Whoa! What a game! " + winnerName + " won the game! \n" +
				  "\n Would you like to play again?", "Win", JOptionPane.YES_NO_OPTION);

		//If the user chooses Yes, reset the game
		if (reply == JOptionPane.YES_OPTION) {
			try {
				GameWindow.resetGame();
			} catch (NullPointerException ignored) {}
		} else {
			TicTacToe.shutdownApplication();
		}
	}

	static void displayHelp() {
		JOptionPane.showMessageDialog(null,
				  "The Rules: \n" +
							 "- The game is played on a grid that's 3x3 \n" +
							 "- You can choose who goes first when you first start \n" +
							 "- The player to go first, begins the game as X \n" +
							 "- You alternatively place X or O until one of you wins! \n" +
							 "\nWinning Conditions: \n" +
							 "The first player to get 3 of their marks in a row \n" +
							 "(up, down, across or diagonally) is the winner, \n" +
							 "if all 9 squares are filled and no one has won, you draw."
				  ,"Help", JOptionPane.INFORMATION_MESSAGE);
	}

	static void missingResourcesError() {
		JOptionPane.showMessageDialog(null, "One or more image files is missing from the \n" +
				  "'/resources' folder. The application will now close", "Missing Files", JOptionPane.WARNING_MESSAGE);
	}
}