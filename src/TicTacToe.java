import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

public class TicTacToe {
	static String[] gameGrid = new String[9];
	static byte movementCount = 1;
	static int currentPlayer;
	static boolean computerPlayerEnabled;
	static String gameMode = null;
	static String playerMode = null;

	public static void main(String[] args) {
		//Display primary choice panel. Ask the user about game settings.
		new GameModeChooser();
	}

	static void startApplication() {
		computerPlayerEnabled = !gameMode.equals("Player vs Player");

		try {
			new GameWindow();
			GameWindow.setIndicatorLabel("Press Start When Ready");
			GameWindow.clearBoardIcons();
			Arrays.fill(gameGrid, ".");
		} catch (IllegalArgumentException e) {   //Catch missing image files
			GameWindow.missingResourcesError();
			System.out.println("One or more image files is missing from the resource directory");
			shutdownApplication();
		}

		chooseFirstPlayer();

		if (computerPlayerEnabled && currentPlayer == 2) {
			ComputerPlayer.computerMove();
			currentPlayer++;
			Validation.checkMove(XOButton.lastIconCheck);
		}
	}


	static void chooseFirstPlayer() {
		//Generate random number to determine who goes first (for reset function)
		Random rand = new Random();
		currentPlayer = rand.nextInt(2) + 1;

		//Setup the game for first start, otherwise use random generation
		if (playerMode.equals("Player 1")) {
			currentPlayer = 1;
		} else if (playerMode.equals("Player 2 (or Computer)")) {
			currentPlayer = 2;
		}

		//Announce who will be making the first move
		if (computerPlayerEnabled && currentPlayer == 2) {
			JOptionPane.showMessageDialog(null, "Computer will now make the move", "Computer Engaged", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " you are starting the game as X.",
					  "Player " + currentPlayer, JOptionPane.INFORMATION_MESSAGE);
		}

		GameWindow.updateTurnIndicatorLabel();
		playerMode = null;
	}
	static void shutdownApplication() {
		System.exit(0);
	}
}