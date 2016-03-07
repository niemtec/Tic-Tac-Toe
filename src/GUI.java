import javax.swing.*;

/**
 * Contains all the graphical elements outside of the main app window.
 **/
public class GUI {

	public static boolean selectGameMode() {
		String gameMode = null;
		boolean aiStatus = false;

		Object[] options = {"Player vs Player", "Challenge the AI"};

		int reply = JOptionPane.showOptionDialog(null, "What mode would you like to play?", "Choose Game Mode",
				  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (reply == JOptionPane.YES_OPTION) {
			gameMode = "PlayerVPlayer";
			aiStatus = false;
		} else if (reply == JOptionPane.NO_OPTION) {
			gameMode = "PlayerVAI";
			aiStatus = true;
		}
		System.out.println(gameMode);
		return aiStatus;
	}

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
			AppWindow.resetGame();
		} else {
			Main.shutdownApplication();
		}
	}

	public static void showHelp() {
		JOptionPane.showMessageDialog(null,
				  "The Rules: \n" +
				 	"- The game is played on a grid that's 3x3 \n" +
					 "- The computer randomly chooses who goes first \n" +
					 "- The player to go first, begins the game as X \n" +
					 "- You alternatively place X or O until one of you wins! \n" +
					 "\nWinning Conditions: \n" +
					 "The first player to get 3 of their marks in a row \n" +
					 "(up, down, across or diagonally) is the winner, \n" +
					 "if all 9 squares are filled and no one has won, you draw."
				  ,"Help", JOptionPane.INFORMATION_MESSAGE);
	}
}

