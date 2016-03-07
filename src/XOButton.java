import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class XOButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 6167021308779841017L;
	public static String lastIconCheck = null;
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
		//Get source of the click (which button has been clicked on?)
		Object click = e.getSource();
		//String lastIconCheck = null;

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
				break;
			}
		}
		//TODO make the ai move return to here
		Validation.validateMove(lastIconCheck);
		//TODO check if AI should move after click?
	}

	public static void disableButton(int i) {
		if (Main.gameGrid[i].contains("X")) {
			AppWindow.button[i].setDisabledIcon(iconX);
		} else if (Main.gameGrid[i].contains("O")) {
			AppWindow.button[i].setDisabledIcon(iconO);
		}
		AppWindow.button[i].setEnabled(false);
	}
}
