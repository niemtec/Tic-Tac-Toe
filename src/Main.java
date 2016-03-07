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
	public static String gameMode = null;

	public static void main(String[] args) throws IOException {
		//Ask user to select game mode
		gameMode = GUI.selectGameMode();



		//Create the app window
		new AppWindow();
		TurnIndicator.setTurnIndicatorLabel("Press Start When Ready");
		AppWindow.clearBoardIcons();

		//TODO Randomly choose who goes first? Check specification
		chooseFirstPlayer();

		//Populate grid with dummy data to avoid null pointer exceptions
		Arrays.fill(gameGrid, "N");
		System.out.println("MC = " +movementCount);
	}

	public static void chooseFirstPlayer() {
		//Generate random number to determine who goes first
		Random rand = new Random();
		currentPlayer = rand.nextInt(2) + 1;
		JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " you are starting the game as X.",
				  "Player " + currentPlayer, JOptionPane.INFORMATION_MESSAGE);
		TurnIndicator.updateTurnIndicatorLabel();
	}

	public static void shutdownApplication() {
		//TODO Fix the shutdown?
		System.out.println("I tried to quit!");
		System.exit(0);
	}
}