import java.util.Random;

public class AI {
	/**
	 * Class containing all strategy parameters for the computer to make the move.
	 */
	public static String aiSymbol;
	public static void aiMove() {
		//String aiSymbol;
		if (Main.movementCount % 2 == 1) {
			aiSymbol = "X";
		} else {
			aiSymbol = "O";
		}

		//If the central positon of the grid is empty, use that if you can
		if (Main.gameGrid[4].equals(".")) {
			setAIIcon(4, aiSymbol);
		} else {
			Random rand = new Random();
			int randomPosition = rand.nextInt(8) + 0;

			if(Main.gameGrid[randomPosition].equals(".")) {
				setAIIcon(randomPosition, aiSymbol);
			} else {
				for (int i = 0; i < Main.gameGrid.length; i++) {
					if (Main.gameGrid[i].equals(".")) {
						setAIIcon(i, aiSymbol);
						break;
					}
				}
			}
		}
	}


	private static void setAIIcon(int arrayIndex, String symbol) {
		if (symbol.equals("X")) {
			AppWindow.button[arrayIndex].setIcon(XOButton.iconX);
		} else {
			AppWindow.button[arrayIndex].setIcon(XOButton.iconO);
		}

		XOButton.lastIconCheck = aiSymbol;
		Main.gameGrid[arrayIndex] = aiSymbol;
		XOButton.disableButton(arrayIndex);
	}
}