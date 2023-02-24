package noughtsAndCrosses;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class NoughtsAndCrossesGame implements ActionListener {

	// variable to track if it's player one's turn.
	private boolean p1Turn;
	// array of button objects for the grid
	private JButton buttons[];
	//
	private JLabel title_Label;
	
	public NoughtsAndCrossesGame(){
		// Creating the Canvas (Frame)
		JFrame game_Frame = new JFrame();
		game_Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		game_Frame.setSize(800, 800);
		game_Frame.setVisible(true);
		game_Frame.setLayout(new BorderLayout());


		// Creating the title bar with header text
		JPanel title_Panel = new JPanel();
		title_Panel.setBackground(Color.BLACK);
		title_Panel.setPreferredSize(new Dimension(800, 100));
		title_Label = new JLabel();
		title_Label.setFont(new Font("Arial",Font.BOLD, 75));
		title_Label.setForeground(Color.WHITE);
		title_Label.setBackground(Color.BLACK);
		title_Label.setPreferredSize(new Dimension(800,100));
		title_Label.setText("Noughts And Crosses");
		title_Label.setHorizontalAlignment(SwingConstants.CENTER);
		title_Panel.add(title_Label);
		game_Frame.add(title_Panel, BorderLayout.NORTH);

		// Create a new panel made up of a grid of buttons
		JPanel button_Panel = new JPanel();
		button_Panel.setLayout(new GridLayout(3,3,0,0));
		button_Panel.setPreferredSize(new Dimension(800, 600));
		buttons = new JButton[9];
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
		}
		for (JButton i: buttons) {
			button_Panel.add(i);
			i.setFocusable(false);
			i.addActionListener(this);
			i.setBackground(Color.DARK_GRAY);
		}
		game_Frame.add(button_Panel);
		game_Frame.pack();
		
		startingPlayer();
	}
	
	
	
	// method to randomise the starting player.
	public void startingPlayer(){
			
		Random random = new Random();
		int player = random.nextInt(2)+1;
		if (player == 1) {
			p1Turn = true;
			title_Label.setText("X's Turn");
		} else {
			p1Turn = false;
			title_Label.setText("O's turn");
		}
		
	}

	
	public void winChecker() {
		for (int i = 0; i < 2; i++) {
			String player;
			if (i == 0) {
				player = "O";
			} else {
				player = "X";
			}
			if(
					(buttons[0].getText()==player) &&
					(buttons[1].getText()==player) &&
					(buttons[2].getText()==player)
					) {
				winner(player,0,1,2);
			}
			if(
					(buttons[3].getText()==player) &&
					(buttons[4].getText()==player) &&
					(buttons[5].getText()==player)
					) {
				winner(player,3,4,5);
			}
			if(
					(buttons[6].getText()==player) &&
					(buttons[7].getText()==player) &&
					(buttons[8].getText()==player)
					) {
				winner(player,6,7,8);
			}
			if(
					(buttons[0].getText()==player) &&
					(buttons[3].getText()==player) &&
					(buttons[6].getText()==player)
					) {
				winner(player,0,3,6);
			}
			if(
					(buttons[1].getText()==player) &&
					(buttons[4].getText()==player) &&
					(buttons[7].getText()==player)
					) {
				winner(player,1,4,7);
			}
			if(
					(buttons[2].getText()==player) &&
					(buttons[5].getText()==player) &&
					(buttons[8].getText()==player)
					) {
				winner(player,2,5,8);
			}
			if(
					(buttons[0].getText()==player) &&
					(buttons[4].getText()==player) &&
					(buttons[8].getText()==player)
					) {
				winner(player,0,4,8);
			}
			if(
					(buttons[2].getText()==player) &&
					(buttons[4].getText()==player) &&
					(buttons[6].getText()==player)
					) {
				winner(player,2,4,6);
			}
		}
	}
	
	public void winner(String winner, int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		for (JButton i : buttons) {
			i.setEnabled(false);
		}
		title_Label.setText(winner + " Wins!");
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// check which button was pressed
		for (JButton i : buttons) {
			if (e.getSource() == i) {
				// check if the button is already occupied
				if (i.getText() == "") {
					// check who pressed the button
					if (p1Turn) {
						// assign text to the button -> O or X
						i.setText("O");
						i.setFont(new Font("Arial", Font.BOLD, 130));
						i.setForeground(Color.RED);
						// next player
						p1Turn = false;
						//	check for a winner 
						title_Label.setText("X Turn");
						winChecker();
					} else {
						i.setText("X");
						i.setFont(new Font("Arial", Font.BOLD, 130));
						i.setForeground(Color.YELLOW);
						p1Turn = true;
						title_Label.setText("O Turn");
						winChecker();
					}
				}
			}
		}
	}
	

}
