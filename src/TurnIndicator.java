import javax.swing.*;

public class TurnIndicator {
	public static void updateTurnIndicatorLabel() {
		String secondPlayerName;
		int previousPlayer = Main.currentPlayer%2;

		//Determine what to call the second player based on the game mode
		if (Main.aiEnabled == true) {
			secondPlayerName = "AI";
		} else {
			secondPlayerName = "Player 2";
		}

		if (previousPlayer == 1) {
			setTurnIndicatorLabel("Current Turn: Player 1");
		} else if (previousPlayer == 0) {
			setTurnIndicatorLabel("Current Turn: " + secondPlayerName);
			/* TODO when Jarvis is playing, disable the game grid so that the user can't click. Unless this will happen
			 * too fast anyway?
			 */
		}
	}


	public static JLabel getTurnIndicatorLabel() {
		return AppWindow.turnIndicatorLabel;
	}

	public static void setTurnIndicatorLabel(String label) {
		AppWindow.turnIndicatorLabel.setText(String.valueOf(label));
	}
}

//TODO Add flashing function to capture users' attention