
public class  Validation {
	public static boolean checkWin(String value) {
		boolean winDetected;

		//Check for Horizontal wins
		if ((Main.gameGrid[0].contains(value) && Main.gameGrid[1].contains(value) && Main.gameGrid[2].contains(value))
				  || (Main.gameGrid[3].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[5].contains(value))
				  || (Main.gameGrid[6].contains(value) && Main.gameGrid[7].contains(value) && Main.gameGrid[8].contains(value))) {
			System.out.println(value + " Won");
			winDetected = true;
			//GUI.winnerDetectedMessage(value);
		}
		//Check for vertical win
		 else if ((Main.gameGrid[0].contains(value) && Main.gameGrid[3].contains(value) && Main.gameGrid[6].contains(value))
				  || (Main.gameGrid[1].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[7].contains(value))
				  || (Main.gameGrid[2].contains(value) && Main.gameGrid[5].contains(value) && Main.gameGrid[8].contains(value))) {
			System.out.println(value + " Won");
			winDetected = true;
			//GUI.winnerDetectedMessage(value);
		}
		//Check for diagonal win
		else if ((Main.gameGrid[6].contains(value) && Main.gameGrid[7].contains(value) && Main.gameGrid[8].contains(value))
				  || (Main.gameGrid[0].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[8].contains(value))
				  || (Main.gameGrid[2].contains(value) && Main.gameGrid[4].contains(value) && Main.gameGrid[6].contains(value))) {
			System.out.println(value + " Won");
			winDetected = true;
			//GUI.winnerDetectedMessage(value);
		} else {
			winDetected = false;
		}
		//TODO what happens when nothing is detected
		return(winDetected);
	}

	public static void checkDraw() {
		//Check if all boxes are filled and no win is present
		//TODO Display Draw, Offer reset of game
		int filledIndexCount = 0;
		for (int i = 0; i < Main.gameGrid.length; i++) {
			if (Main.gameGrid[i] != "N") {
				filledIndexCount++;
			}
			if (filledIndexCount == 9) {
				GUI.drawDetectedMessage();
			}
		}
	}

	public static void clearGameGrid() {
      /* TODO empty the game array. Check if there is a better way to do that rather than setting 0 */
		for (int i = 0; i < 9; i++) {
			Main.gameGrid[i] = null;
		}
	}
}
