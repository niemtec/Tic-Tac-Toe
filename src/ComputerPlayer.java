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

		//If the central positon of the grid is empty, use that if you can
		if (TicTacToe.gameGrid[4].equals(".")) {
			setComputerSymbol(4, computerSymbol);
		} else {
			//If the preferred position is taken, try a random one
			Random rand = new Random();
			int randomPosition = rand.nextInt(8);
			if(TicTacToe.gameGrid[randomPosition].equals(".")) {
				setComputerSymbol(randomPosition, computerSymbol);
			} else {
				//If random move is unavailable, try to fill in the nearest unavailable square
				for (int i = 0; i < TicTacToe.gameGrid.length; i++) {
					if (TicTacToe.gameGrid[i].equals(".")) {
						setComputerSymbol(i, computerSymbol);
						break;
					}
				}
			}
		}
	}

	//Method setting
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