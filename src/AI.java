public class AI {
	/**
	 * Class containing all strategy parameters for the computer to make the move.
	 */
	public static void aiBasicMove() {
		String aiSymbol;
		if (Main.movementCount%2 == 1) {
			aiSymbol = "X";
		} else {
			aiSymbol = "O";
		}

		//Progress through the grid and find the next available empty spot
		for (int i = 0; i < Main.gameGrid.length; i++) {
			//If empty slot is detected - "." slot
			if (Main.gameGrid[i].equals(".")) {
				//Set icon by AI
				if (aiSymbol.equals("X")) {
					AppWindow.button[i].setIcon(XOButton.iconX);
				} else {
					AppWindow.button[i].setIcon(XOButton.iconO);
				}
				XOButton.lastIconCheck = aiSymbol;
				Main.gameGrid[i] = aiSymbol;
				XOButton.disableButton(i);
				break;
			}
		}
	}
}
