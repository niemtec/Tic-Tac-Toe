import javax.swing.*;

/**
 * Contains all the graphical elements outside of the main app window.
 **/
public class GUI {

	public static String selectGameMode() {
		String gameMode = null;

		Object[] options = {"Player vs Player", "Challenge the AI"};

		int reply = JOptionPane.showOptionDialog(null, "What mode would you like to play?", "Choose Game Mode",
				  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (reply == JOptionPane.YES_OPTION) {
			gameMode = "PlayerVPlayer";
		} else if (reply == JOptionPane.NO_OPTION) {
			gameMode = "PlayerVAI";
		}

		return gameMode;
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
}

