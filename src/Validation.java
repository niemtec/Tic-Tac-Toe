public class  Validation {
	public static void validateMove(String lastIcon) {
		if (checkWin(lastIcon)) {
			//Indicate the game winner
			TurnIndicator.setTurnIndicatorLabel(lastIcon + " won the game!");
			GUI.winnerDetectedMessage(lastIcon);
		} else {
			Validation.checkDraw();
			Main.movementCount++;
			TurnIndicator.updateTurnIndicatorLabel();
		}
	}

	public static boolean checkWin(String value) {
		boolean winDetected;

		//Check for Horizontal wins
		if ((Main.gameGrid[0].contains(value) && Main.gameGrid[1].contains(value) && Main.gameGrid[2].contains(value))
				  || (Main.gameGrid[3].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[5].contains(value))
				  || (Main.gameGrid[6].contains(value) && Main.gameGrid[7].contains(value) && Main.gameGrid[8].contains(value))) {
			winDetected = true;
		}
		//Check for vertical win
		 else if ((Main.gameGrid[0].contains(value) && Main.gameGrid[3].contains(value) && Main.gameGrid[6].contains(value))
				  || (Main.gameGrid[1].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[7].contains(value))
				  || (Main.gameGrid[2].contains(value) && Main.gameGrid[5].contains(value) && Main.gameGrid[8].contains(value))) {
			winDetected = true;
		}
		//Check for diagonal win
		else if ((Main.gameGrid[6].contains(value) && Main.gameGrid[7].contains(value) && Main.gameGrid[8].contains(value))
				  || (Main.gameGrid[0].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[8].contains(value))
				  || (Main.gameGrid[2].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[6].contains(value))) {
			winDetected = true;
		} else {
			winDetected = false;
		}
		return(winDetected);
	}

	public static void checkDraw() {
		//Check if all boxes are filled and no win is present
		int filledIndexCount = 0;
		for (int i = 0; i < Main.gameGrid.length; i++) {
			if (Main.gameGrid[i] != ".") {
				filledIndexCount++;
			}
			if (filledIndexCount == 9) {
				GUI.drawDetectedMessage();
			}
		}
	}
}
