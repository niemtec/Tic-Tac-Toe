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
		}
	}


	public static JLabel getTurnIndicatorLabel() {
		return AppWindow.turnIndicatorLabel;
	}

	public static void setTurnIndicatorLabel(String label) {
		AppWindow.turnIndicatorLabel.setText(String.valueOf(label));
	}
}