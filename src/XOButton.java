import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class XOButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 6167021308779841017L;
	static String lastIconCheck = null;
	static ImageIcon iconX, iconO;

	XOButton() {
		this.setBackground(Color.white);
		try {
			iconX = new ImageIcon(ImageIO.read(GameWindow.class.getResource("resources/X.png")));
			iconO = new ImageIcon(ImageIO.read(GameWindow.class.getResource("resources/O.png")));
		} catch (IOException e) {
			System.out.println("One or more of the icon images is missing from the resources folder.");
		}
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object click = e.getSource();

		for (int i = 0; i < GameWindow.button.length; i++) {
			if (click == GameWindow.button[i]) {
				if (TicTacToe.movementCount%2 == 1) {
					GameWindow.button[i].setIcon(XOButton.iconX);
					TicTacToe.gameGrid[i] = "X";	//record current value to the game grid
					lastIconCheck = "X";
				} else {
					GameWindow.button[i].setIcon(XOButton.iconO);
					TicTacToe.gameGrid[i] = "O";	//record current value to the game grid
					lastIconCheck = "O";
				}

				disableButton(i);
				break;
			}
		}

		Validation.checkMove(lastIconCheck);
		if (TicTacToe.computerPlayerEnabled) {
			ComputerPlayer.computerMove();
		} else {
			TicTacToe.movementCount++;
		}
		Validation.checkMove(lastIconCheck);
		TicTacToe.currentPlayer++;
	}

	static void disableButton(int i) {
		if (TicTacToe.gameGrid[i].contains("X")) {
			GameWindow.button[i].setDisabledIcon(iconX);
		} else if (TicTacToe.gameGrid[i].contains("O")) {
			GameWindow.button[i].setDisabledIcon(iconO);
		}
		GameWindow.button[i].setEnabled(false);
	}
}
