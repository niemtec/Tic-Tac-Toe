import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class XOButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 6167021308779841017L;

	static ImageIcon iconX, iconO;

	public XOButton() {
		this.setBackground(Color.white);
		try {
			iconX = new ImageIcon(ImageIO.read(AppWindow.class.getResource("resources/X.png")));
			iconO = new ImageIcon(ImageIO.read(AppWindow.class.getResource("resources/O.png")));
		} catch (IOException e) {
			System.out.println("One or more of the icon images is missing from the resources folder.");
			e.printStackTrace();
		}
		//Add action listener to the button
		this.addActionListener(this);
	}

	//Checks which gird button has been pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Remove
		System.out.println("Count of listeners: " + ((JButton) e.getSource()).getActionListeners().length);
		//Get source of the click (which button has been clicked on?)
		Object click = e.getSource();
		String lastIconCheck = null;

		for (int i = 0; i < AppWindow.button.length; i++) {
			if (click == AppWindow.button[i]) {
				if (Main.movementCount % 2 == 1) {
					AppWindow.button[i].setIcon(XOButton.iconX);
					Main.gameGrid[i] = "X";
					lastIconCheck = "X";
				} else {
					AppWindow.button[i].setIcon(XOButton.iconO);
					Main.gameGrid[i] = "O";
					lastIconCheck = "O";
				}
				disableButton(i);
				//AppWindow.button[i].removeActionListener(this);

				//TODO Remove debugging when ready
				//System.out.print(Main.iconTurn);
				System.out.println(Main.movementCount);
				break;
			}
		}

		if (Validation.checkWin(lastIconCheck) == true) {
			//Indicate the game winner
			TurnIndicator.setTurnIndicatorLabel(lastIconCheck + " won the game!");
			GUI.winnerDetectedMessage(lastIconCheck);
			//JOptionPane.showMessageDialog(null, lastIconCheck, "Winner", JOptionPane.INFORMATION_MESSAGE);
			//AppWindow.resetGame();
		} else {
			Validation.checkDraw();
			Main.movementCount++;
			TurnIndicator.updateTurnIndicatorLabel();
		}
	}

private void disableButton(int buttonIndex) {
	if (Main.gameGrid[buttonIndex].contains("X")) {
		AppWindow.button[buttonIndex].setDisabledIcon(iconX);
	} else if (Main.gameGrid[buttonIndex].contains("O")) {
		AppWindow.button[buttonIndex].setDisabledIcon(iconO);
	}
	AppWindow.button[buttonIndex].setEnabled(false);
}
//	//Disables all action listeners on the game grid
//	public void disableGridButtons() {
//		for (int i = 0; i < 9; i++) {
//			AppWindow.button[i].removeActionListener(this);
//		}
//	}
}
