public class AI {
	/**
	 * The most basic type of AI: find an empty spot and fill it up
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
			//If empty slot is detected - "N" slot
			if (Main.gameGrid[i].equals("N")) {
				//Set icon by AI
				if (aiSymbol.equals("X")) {
					AppWindow.button[i].setIcon(XOButton.iconX);
				} else {
					AppWindow.button[i].setIcon(XOButton.iconO);
				}
				XOButton.lastIconCheck = aiSymbol;
				//Place move in the game grid
				Main.gameGrid[i] = aiSymbol;
				//Disable the button chosen by the AI
				XOButton.disableButton(i);
				break;
			}
		}
	}
}
