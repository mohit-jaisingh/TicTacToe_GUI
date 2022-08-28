import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{
	
	Random random = new Random();
	
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textField = new JLabel();
	
	JPanel gameEnd_panel = new JPanel();
	JButton yes_button = new JButton("Yes");
	JButton no_button = new JButton("No");
	JLabel gameEnd_field = new JLabel();
	JPanel question_panel = new JPanel();
	
	JButton[] buttons = new JButton[9];
	
	boolean player1_turn;
	
	int filled = 0;
	
	TicTacToe(){
		
		//Main frame.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		//Text Field for Title panel
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(25,255,0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER); //Center in Border Layout.
//		textField.setText("Tic-Tac-Toe");
		textField.setOpaque(true);
		
		//Game end Text for gameEnd panel;
		gameEnd_field.setBackground(new Color(255,200,30));
		gameEnd_field.setForeground(new Color(0,0,0));
		gameEnd_field.setFont(new Font("Ink Free", Font.BOLD, 75));
		gameEnd_field.setHorizontalAlignment(JLabel.CENTER); //Center in Border Layout.
		gameEnd_field.setText("New Game?");
		gameEnd_field.setOpaque(true);
		
		//Title panel
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		//Adding text field to the title panel
		title_panel.add(textField);
		
		//Game end Panel
		gameEnd_panel.setLayout(new BorderLayout());
		gameEnd_panel.setBounds(0,700,800,75);
		gameEnd_panel.add(gameEnd_field,BorderLayout.NORTH);
		
		question_panel.setLayout(new GridLayout(1,2));
		question_panel.setBackground(new Color(25,75,75));
		question_panel.add(yes_button);
		question_panel.add(no_button);
		yes_button.addActionListener(this);
		no_button.addActionListener(this);
		gameEnd_panel.add(question_panel,BorderLayout.SOUTH);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		//Adding the title panel to the main Frame's North panel.
		frame.add(title_panel,BorderLayout.NORTH);
		
		frame.add(button_panel,BorderLayout.CENTER);
		
		frame.add(gameEnd_panel,BorderLayout.SOUTH);
		
		setBoard();
		firstTurn();
	}
	
	public void setBoard() {
		
		gameEnd_panel.setVisible(false);
		filled = 0;
		for(int i=0; i<9; i++) {
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setBackground(new Color(240,240,240));
		}
		textField.setText("Tic-Tac-Toe");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("action performed");
		
		for(int i=0; i<9; i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText().equals("")) {
						filled++;
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn = false;
						textField.setText("O Turn");
					}
				}
				else {
						if(buttons[i].getText().equals("")) {
							filled++;
							buttons[i].setForeground(new Color(0,0,255));
							buttons[i].setText("O");
							player1_turn = true;
							textField.setText("X Turn");
						}
				}
			}
		}
		

		if(e.getSource()==yes_button) {
			System.out.println("Game restarts");
			setBoard();
		}else check();
		
	}
	
	public void firstTurn()  {
		
		try {
			System.out.println("sleeping");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(random.nextInt(2)==0) {
			player1_turn = true;
			textField.setText("X turn");
		}else {
			player1_turn = false;
			textField.setText("O turn");
		}
	}
	
	public void check() {
		//check X win conditions
		if( (buttons[0].getText().equals("X")) && //rows
			(buttons[1].getText().equals("X")) &&
			(buttons[2].getText().equals("X"))) {
			
			xWins(0,1,2);
		}
		if( 	(buttons[3].getText().equals("X")) && 
				(buttons[4].getText().equals("X")) &&
				(buttons[5].getText().equals("X"))) {
				
				xWins(3,4,5);
			}
		if( 	(buttons[6].getText().equals("X")) && 
				(buttons[7].getText().equals("X")) &&
				(buttons[8].getText().equals("X"))) {
				
				xWins(6,7,8);
			}
		if( 	(buttons[0].getText().equals("X")) && //backward diag
				(buttons[4].getText().equals("X")) &&
				(buttons[8].getText().equals("X"))) {
				
				xWins(0,4,8);
			}
		if( 	(buttons[2].getText().equals("X")) && //forward diag
				(buttons[4].getText().equals("X")) &&
				(buttons[6].getText().equals("X"))) {
				
				xWins(2,4,6);
			}
		if( 	(buttons[0].getText().equals("X")) && //cols
				(buttons[3].getText().equals("X")) &&
				(buttons[6].getText().equals("X"))) {
				
				xWins(0,3,6);
			}
		if( 	(buttons[1].getText().equals("X")) && //cols
				(buttons[4].getText().equals("X")) &&
				(buttons[7].getText().equals("X"))) {
				
				xWins(1,4,7);
			}
		if( 	(buttons[2].getText().equals("X")) && //cols
				(buttons[5].getText().equals("X")) &&
				(buttons[8].getText().equals("X"))) {
				
				xWins(2,5,8);
			}
		
		
		
		//checl Y win conditions
		if( (buttons[0].getText().equals("O")) && 
				(buttons[1].getText().equals("O")) &&
				(buttons[2].getText().equals("O"))) {
				
				oWins(0,1,2);
			}
			if( 	(buttons[3].getText().equals("O")) && 
					(buttons[4].getText().equals("O")) &&
					(buttons[5].getText().equals("O"))) {
					
					oWins(3,4,5);
				}
			if( 	(buttons[6].getText().equals("O")) && 
					(buttons[7].getText().equals("O")) &&
					(buttons[8].getText().equals("O"))) {
					
					oWins(6,7,8);
				}
			if( 	(buttons[0].getText().equals("O")) && 
					(buttons[4].getText().equals("O")) &&
					(buttons[8].getText().equals("O"))) {
					
					oWins(0,4,8);
				}
			if( 	(buttons[2].getText().equals("O")) && 
					(buttons[4].getText().equals("O")) &&
					(buttons[6].getText().equals("O"))) {
					
					oWins(2,4,6);
				}
			if( 	(buttons[0].getText().equals("O")) && //cols
					(buttons[3].getText().equals("O")) &&
					(buttons[6].getText().equals("O"))) {
					
					oWins(0,3,6);
				}
			if( 	(buttons[1].getText().equals("O")) && //cols
					(buttons[4].getText().equals("O")) &&
					(buttons[7].getText().equals("O"))) {
					
					oWins(1,4,7);
				}
			if( 	(buttons[2].getText().equals("O")) && //cols
					(buttons[5].getText().equals("O")) &&
					(buttons[8].getText().equals("O"))) {
					
					oWins(2,5,8);
				}
		
			if(filled == 9) draw();
			
			System.out.println("filled = " + filled);
		
	}
	
	public void xWins(int a, int b, int c) {
		
		buttons[a].setBackground(new Color(0,255,0));
		buttons[b].setBackground(new Color(0,255,0));
		buttons[c].setBackground(new Color(0,255,0));
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText("X Wins");
		gameEnd_panel.setVisible(true);
	}
	
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(new Color(0,255,0));
		buttons[b].setBackground(new Color(0,255,0));
		buttons[c].setBackground(new Color(0,255,0));
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText("O Wins");
		gameEnd_panel.setVisible(true);
	}
	
	public void draw() {
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText("It is a Draw");
		gameEnd_panel.setVisible(true);
	}

}
