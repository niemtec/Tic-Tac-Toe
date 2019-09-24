import java.util.Objects;

/**
 * This class contains all validation rules which determine the current state and/or result of the game.
 */

class  Validation {
	//Method used to validate each move, checking if the winning condition is met, it announces win otherwise it checks for draw
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

	//Method which checks for a win from the passed value (either X or O), if 3 in a row are found then a win is returned
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

	//Method which checks for a draw scenario based on the game array contents
	private static void checkDraw() {
		//Check if all boxes are filled and no win is present
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
