import javax.swing.*;

/**
 * Contains all the graphical elements outside of the main app window.
 **/
public class GUI {

	public static void drawDetectedMessage() {
		int reply = JOptionPane.showConfirmDialog(null, "It's a draw! \n" +
				  "\n Would you like to play again?", "Draw", JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			AppWindow.resetGame();
			Main.movementCount = 0;	//prevents issues with O being set
		} else {
			Main.shutdownApplication();
		}
	}

	public static void winnerDetectedMessage(String winnerName) {
		int reply = JOptionPane.showConfirmDialog(null, "Whoa! What a game! " + winnerName + " won the game! \n" +
				  "\n Would you like to play again?", "Win", JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			try {
				AppWindow.resetGame();
			} catch (NullPointerException ignored) {}
		} else {
			Main.shutdownApplication();
		}
	}

	public static void showHelp() {
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

	public static void missingResourcesError() {
		JOptionPane.showMessageDialog(null, "One or more image files is missing from the \n" +
		"'/resources' folder. The application will now close", "Missing Files", JOptionPane.WARNING_MESSAGE);
	}

	public static void updateTurnIndicatorLabel() {
		String secondPlayerName;
		int previousPlayer = Main.currentPlayer%2;

		//Determine what to call the second player based on the game mode
		if (Main.aiEnabled) {
			secondPlayerName = "AI";
		} else {
			secondPlayerName = "Player 2";
		}

		if (previousPlayer == 1) {
			setTurnIndicatorLabel("Current Turn: Player 1");
		} else if (previousPlayer == 0) {
			setTurnIndicatorLabel("Current Turn: " + secondPlayerName);
		}
	}

	public static JLabel getTurnIndicatorLabel() {
		return AppWindow.turnIndicatorLabel;
	}

	public static void setTurnIndicatorLabel(String label) {
		AppWindow.turnIndicatorLabel.setText(String.valueOf(label));
	}
}

