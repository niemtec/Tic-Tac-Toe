import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * CS1702: Introductory Programming Coursework
 * Brunel University London 2015/16
 * Jakub Adrian Niemiec (1500408)
 */

public class Main {
	//gameGrid array stores all of the inputs from the user
	public static String[] gameGrid = new String[9];
	//public static int iconTurn = 1;
	public static int movementCount = 1;
	public static int currentPlayer;
	public static boolean aiEnabled;

	public static void main(String[] args) throws IOException {
		//Ask user to select game mode
		aiEnabled = GUI.selectGameMode();


		try {
			//Create the app window
			new AppWindow();
			TurnIndicator.setTurnIndicatorLabel("Press Start When Ready");
			AppWindow.clearBoardIcons();
			//Populate grid with dummy data to avoid null pointer exceptions
			Arrays.fill(gameGrid, ".");
		} catch (IllegalArgumentException e) {	//Catch missing image files
			GUI.missingResourcesError();
			System.out.println("One or more image files is missing from the resource directory");
			shutdownApplication();
		}

		chooseFirstPlayer();

		//If AI is on and its turn is due, make the move
		if (aiEnabled == true && currentPlayer == 2) {
			AI.aiBasicMove();
			currentPlayer++;
			Validation.validateMove(XOButton.lastIconCheck);
		}
	}

	public static void chooseFirstPlayer() {
		//Generate random number to determine who goes first
		Random rand = new Random();
		currentPlayer = rand.nextInt(2) + 1;
		if (aiEnabled == true && currentPlayer == 2) {
			JOptionPane.showMessageDialog(null, "AI will now make the move", "AI Engaged", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " you are starting the game as X.",
					  "Player " + currentPlayer, JOptionPane.INFORMATION_MESSAGE);
		}
		TurnIndicator.updateTurnIndicatorLabel();
	}

	public static void shutdownApplication() {
		System.exit(0);
	}
}