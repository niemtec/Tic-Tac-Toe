import java.util.Objects;

/**
 * This class contains all validation rules which determine the current state and/or result of the game.
 */

class  Validation {
	static void checkMove(String lastIcon) {
		if (checkWin(lastIcon)) {
			//Indicate the game winner
			GameWindow.setIndicatorLabel(lastIcon + " won the game!");
			GameWindow.winnerDetectedMessage(lastIcon);
		} else {
			Validation.checkDraw();
			TicTacToe.movementCount++;
			GameWindow.updateTurnIndicatorLabel();
		}
	}

	private static boolean checkWin(String value) {
		boolean winDetected;
		//Check all possible winning conditions and return true if a win is detected
		winDetected = (TicTacToe.gameGrid[0].contains(value) && TicTacToe.gameGrid[1].contains(value) && TicTacToe.gameGrid[2].contains(value))
				  || (TicTacToe.gameGrid[3].contains(value) && TicTacToe.gameGrid[4].contains(value) && TicTacToe.gameGrid[5].contains(value))
				  || (TicTacToe.gameGrid[6].contains(value) && TicTacToe.gameGrid[7].contains(value) && TicTacToe.gameGrid[8].contains(value))
				  || (TicTacToe.gameGrid[0].contains(value) && TicTacToe.gameGrid[3].contains(value) && TicTacToe.gameGrid[6].contains(value))
				  || (TicTacToe.gameGrid[1].contains(value) && TicTacToe.gameGrid[4].contains(value) && TicTacToe.gameGrid[7].contains(value))
				  || (TicTacToe.gameGrid[2].contains(value) && TicTacToe.gameGrid[5].contains(value) && TicTacToe.gameGrid[8].contains(value))
				  || (TicTacToe.gameGrid[6].contains(value) && TicTacToe.gameGrid[7].contains(value) && TicTacToe.gameGrid[8].contains(value))
				  || (TicTacToe.gameGrid[0].contains(value) && TicTacToe.gameGrid[4].contains(value) && TicTacToe.gameGrid[8].contains(value))
				  || (TicTacToe.gameGrid[2].contains(value) && TicTacToe.gameGrid[4].contains(value) && TicTacToe.gameGrid[6].contains(value));
		return(winDetected);
	}

	private static void checkDraw() {
		int filledIndexCount = 0;
		for (int i = 0; i < TicTacToe.gameGrid.length; i++) {
			if (!Objects.equals(TicTacToe.gameGrid[i], ".")) {
				filledIndexCount++;
			}
			if (filledIndexCount == 9) {
				GameWindow.drawDetectedMessage();
			}
		}
	}
}
