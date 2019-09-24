import java.util.Random;

class ComputerPlayer {
	/**
	 * Class containing all strategy parameters for the computer to make the move.
	 */
	private static String computerSymbol;
	static void computerMove() {
		if (TicTacToe.movementCount % 2 == 1) {
			computerSymbol = "X";
		} else {
			computerSymbol = "O";
		}

		if (TicTacToe.gameGrid[4].equals(".")) {
			setComputerSymbol(4, computerSymbol);
		} else {
			Random rand = new Random();
			int randomPosition = rand.nextInt(8);
			if(TicTacToe.gameGrid[randomPosition].equals(".")) {
				setComputerSymbol(randomPosition, computerSymbol);
			} else {
				for (int i = 0; i < TicTacToe.gameGrid.length; i++) {
					if (TicTacToe.gameGrid[i].equals(".")) {
						setComputerSymbol(i, computerSymbol);
						break;
					}
				}
			}
		}
	}

	private static void setComputerSymbol(int arrayIndex, String symbol) {
		if (symbol.equals("X")) {
			GameWindow.button[arrayIndex].setIcon(XOButton.iconX);
		} else {
			GameWindow.button[arrayIndex].setIcon(XOButton.iconO);
		}

		XOButton.lastIconCheck = computerSymbol;
		TicTacToe.gameGrid[arrayIndex] = computerSymbol;
		XOButton.disableButton(arrayIndex);
	}
}