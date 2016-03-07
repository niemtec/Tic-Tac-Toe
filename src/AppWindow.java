import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * Class governing the main application window.
 */

public class AppWindow {
	int paddingSize = 5;

	//Main app window frame containing all the elements
	private JFrame appWindowFrame = new JFrame();
	//Top panel
	private JPanel topPanel = new JPanel();
	//Central panel containing the game grid
	private JPanel gameGridPanel = new JPanel();
	//Bottom panel
	private JPanel bottomPanel = new JPanel();
	//Create gameGrid buttons
	public static XOButton button[] = new XOButton[9];
	//Turn indicator label
	public static JLabel turnIndicatorLabel = new JLabel();
	//bottomPanel buttons
	private JButton startButton = new JButton();
	private JButton centreButton = new JButton("Centre");
	private JButton resetButton = new JButton("Right");

	//JFrame Constructor
	public AppWindow() throws IOException {
		//appWindowFrame Properties, main window containing all elements
		appWindowFrame.setVisible(true);
		appWindowFrame.setSize(410, 510);
		appWindowFrame.setBackground(Color.white);
		appWindowFrame.setTitle("TicTacToe");
		appWindowFrame.setResizable(false);
		appWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindowFrame.setLocationRelativeTo(null);	//centers the window

		//topPanel Properties
		topPanel.setVisible(true);
		topPanel.setSize(400, 50);
		topPanel.add(TurnIndicator.getTurnIndicatorLabel());
		topPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));

		//gameGridPanel Properties
		gameGridPanel.setVisible(true);
		gameGridPanel.setSize(400, 400);
		gameGridPanel.setLayout(new GridLayout(3, 3));
		gameGridPanel.setBorder(new EmptyBorder(0, paddingSize, 0, paddingSize));

		//Populate the game grid with XO buttons
		for (int i = 0; i < 9; i++) {
			button[i] = new XOButton();
			gameGridPanel.add(button[i]);
		}

		//bottomPanel Properties
		bottomPanel.setVisible(true);
		bottomPanel.setSize(400, 50);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
		bottomPanel.setBorder(new EmptyBorder(paddingSize, 0, paddingSize, 0));
		//bottomPanel.add(startButton);
		bottomPanel.add(centreButton);
		bottomPanel.add(resetButton);

		//TurnIndicator Label
		//TODO Create a background?
		turnIndicatorLabel.setFont(turnIndicatorLabel.getFont().deriveFont(20.0f));

		//Add stuff to the frame
		appWindowFrame.setLayout(new BorderLayout(paddingSize, paddingSize));
		appWindowFrame.add(topPanel, BorderLayout.NORTH);
		appWindowFrame.add(gameGridPanel, BorderLayout.CENTER);
		appWindowFrame.add(bottomPanel, BorderLayout.SOUTH);

		//Button Action Listeners
		//Reset Button
		resetButton.setBackground(Color.white);
		resetButton.setText("Reset");

		//Reset button uses lambda pointing right to the action instead of creating a new instance the long way
		resetButton.addActionListener(e -> resetGame());
	}

	/**
	 * Method which resets the entire game back to its orignal state. Clearing all icons and values from the game grid.
	 **/
	public static void resetGame() {
		clearGameGridArray();
		clearBoardIcons();
		enableGridButtons();
		//TODO add current player to the label
		//TurnIndicator.setTurnIndicatorLabel("Press Start When Ready");
		Main.movementCount = 1;
		Main.chooseFirstPlayer();

		//TODO Remove debugging when ready
		//Debugging
		System.out.println("--------------");
		System.out.println("Game Restarted");
		System.out.println("MC = " + Main.movementCount);
	}

	//Clear all saved records from the gameGrid
	public static void clearGameGridArray() {
		Arrays.fill(Main.gameGrid, "N");
	}

	//Clear all icons from the game grid
	public static void clearBoardIcons() {
		for (int i = 0; i < 9; i++) {
			button[i].setIcon(null);
		}
	}

	//Enables all action listeners on the game grid
	public static void enableGridButtons() {
		for (int i = 0; i < 9; i++) {
			//AppWindow.button[i].addActionListener(new XOButton());
			AppWindow.button[i].setEnabled(true);
		}
	}
}