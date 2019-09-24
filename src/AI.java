import java.util.Random;

public class AI {
	/**
	 * Class containing all strategy parameters for the computer to make the move.
	 */
	public static String aiSymbol;
	public static void aiMove() {
		//String aiSymbol;
		if (TicTacToe.movementCount % 2 == 1) {
			aiSymbol = "X";
		} else {
			aiSymbol = "O";
		}

		//If the central positon of the grid is empty, use that if you can
		if (TicTacToe.gameGrid[4].equals(".")) {
			setAIIcon(4, aiSymbol);
		} else {
			//If the preferred position is taken, try a random one
			Random rand = new Random();
			int randomPosition = rand.nextInt(8);
			if(TicTacToe.gameGrid[randomPosition].equals(".")) {
				setAIIcon(randomPosition, aiSymbol);
			} else {
				//If random move is unavailable, try to fill in the nearest unavailable square
				for (int i = 0; i < TicTacToe.gameGrid.length; i++) {
					if (TicTacToe.gameGrid[i].equals(".")) {
						setAIIcon(i, aiSymbol);
						break;
					}
				}
			}
		}
	}

	//Method setting
	private static void setAIIcon(int arrayIndex, String symbol) {
		if (symbol.equals("X")) {
			GameWindow.button[arrayIndex].setIcon(XOButton.iconX);
		} else {
			GameWindow.button[arrayIndex].setIcon(XOButton.iconO);
		}

		XOButton.lastIconCheck = aiSymbol;
		TicTacToe.gameGrid[arrayIndex] = aiSymbol;
		XOButton.disableButton(arrayIndex);
	}
}