package gamedevelopment;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public  class TicTacToe extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int BOARD_SIZE=3;
	
	public static enum GameStatus{
		Incomplete,Zwins,Xwins,Tie;
	}
	
	private JButton[][] buttons=new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn=true;
	public TicTacToe() {
		super.setTitle("TicTacToe");
		super.setSize(800, 700);
		GridLayout grid=new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font =new Font("Comic Sans", 1, 150);
		for(int row=0;row<BOARD_SIZE;row++) {
			for(int col=0;col<BOARD_SIZE;col++) {
				JButton button = new JButton("");
				buttons[row][col]=button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
			
		}
		super.setResizable(false);
		super.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedbutton=(JButton)e.getSource();
		makeMove(clickedbutton);
		GameStatus gs=this.getGameStatus();
		if(gs==GameStatus.Incomplete) {
			return;
		}
		
		declareWinner(gs);
		int choice=JOptionPane.showConfirmDialog(this,"Let's play again?");
		if(choice==JOptionPane.YES_OPTION) {
			for(int row=0;row<BOARD_SIZE;row++) {
				for(int col=0;col<BOARD_SIZE;col++) {
					buttons[row][col].setText("");
				}
			}
			crossTurn=true;
		}else {
			super.dispose();
		}
	}
	
	
	private void makeMove(JButton clickedbutton) {
		// TODO Auto-generated method stub
		String btntext=clickedbutton.getText();
		if(btntext.length()>0) {
			JOptionPane.showMessageDialog(this,"Invalid Move");
		
		}else {
			if(crossTurn) {
				clickedbutton.setText("X");
			}else {
				clickedbutton.setText("O");
			}
			crossTurn=!crossTurn;
		}
		
	}
	private GameStatus getGameStatus() {
		String text1="",text2="";
		int row=0, col=0;
		while(row<BOARD_SIZE) {
			col=0;
			while(col<BOARD_SIZE-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row][col+1].getText();
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				col++;
			}
			if(col==BOARD_SIZE-1) {
				if(text1.equals("X")) {
					return GameStatus.Xwins;
				}else {
					return GameStatus.Zwins;
				}
			}
			row++;
		}
		
		col=0;
		while(col<BOARD_SIZE) {
			row=0;
			while(row<BOARD_SIZE-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row+1][col].getText();
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				row++;
			}
			if(row==BOARD_SIZE-1) {
				if(text1.equals("X")) {
					return GameStatus.Xwins;
				}else {
					return GameStatus.Zwins;
				}
			}
			col++;
		}
		
		row=0;col=0;
		while(row<BOARD_SIZE-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row+1][col+1].getText();
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				col++;
				row++;
			}
			if(row==BOARD_SIZE-1) {
				if(text1.equals("X")) {
					return GameStatus.Xwins;
				}else {
					return GameStatus.Zwins;
				}
			}
			
			
			row=0;col=BOARD_SIZE-1;
			while(row<BOARD_SIZE-1) {
					text1=buttons[row][col].getText();
					text2=buttons[row+1][col-1].getText();
					if(!text1.equals(text2) || text1.length()==0) {
						break;
					}
					col--;
					row++;
				}
				if(row==BOARD_SIZE-1) {
					if(text1.equals("X")) {
						return GameStatus.Xwins;
					}else {
						return GameStatus.Zwins;
					}
				}
			
			String txt="";
			for(row=0;row<BOARD_SIZE;row++) {
				for(col=0;col<BOARD_SIZE;col++) {
					txt=buttons[row][col].getText();
					if(txt.length()==0) {
						return GameStatus.Incomplete;
					}
				}
		}
			
			return GameStatus.Tie;
		
	}
	private void declareWinner(GameStatus gs) {
		if(gs==GameStatus.Xwins) {
			JOptionPane.showMessageDialog(this, "!!!X Wins!!!");
		}else if(gs==GameStatus.Zwins) {
			JOptionPane.showMessageDialog(this, "!!!Y Wins!!!");
		}else {
			JOptionPane.showMessageDialog(this, "It's a TIE");
		}
		
	}

}
