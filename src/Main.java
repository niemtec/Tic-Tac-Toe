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
	public static byte movementCount = 1;
	public static int currentPlayer;
	public static boolean aiEnabled;
	public static String gameMode = null;
	public static String playerMode = null;

	public static void main(String[] args) {
		//Display primary choice panel. Ask the user about game settings.
		new GameModeChooser();
	}

	public static void startApplication() throws IOException {
		if (gameMode.equals("Player vs Player")) {
			aiEnabled = false;
		} else {
			aiEnabled = true;
		}

		try {
			//Create the game app window
			new AppWindow();
			GUI.setTurnIndicatorLabel("Press Start When Ready");
			//Remove all icons (Xs and Os)
			AppWindow.clearBoardIcons();
			//Populate grid with dummy data to avoid null pointer exceptions
			Arrays.fill(gameGrid, ".");
		} catch (IllegalArgumentException e) {   //Catch missing image files
			GUI.missingResourcesError();
			System.out.println("One or more image files is missing from the resource directory");
			shutdownApplication();
		}
		//Choose the first player based on user input or random chance (if game is reset)
		chooseFirstPlayer();

		//If AI is on and its turn is due, make the first move
		if (aiEnabled && currentPlayer == 2) {
			AI.aiBasicMove();
			currentPlayer++;
			Validation.validateMove(XOButton.lastIconCheck);
		}
	}


	public static void chooseFirstPlayer() {
		//Generate random number to determine who goes first (for reset function)
		Random rand = new Random();
		currentPlayer = rand.nextInt(2) + 1;

		//Setup the game for first start, otherwise use random generation
		if (playerMode.equals("Player 1")) {
			currentPlayer = 1;
		} else if (playerMode.equals("Player 2 (or AI)")) {
			currentPlayer = 2;
		}

		//Announce who will be making the first move
		if (aiEnabled && currentPlayer == 2) {
			JOptionPane.showMessageDialog(null, "AI will now make the move", "AI Engaged", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " you are starting the game as X.",
					  "Player " + currentPlayer, JOptionPane.INFORMATION_MESSAGE);
		}

		//Update turn indicator with new player information
		GUI.updateTurnIndicatorLabel();
		//Clear player mode for future use
		playerMode = null;
	}

	public static void shutdownApplication() {
		System.exit(0);
	}
}