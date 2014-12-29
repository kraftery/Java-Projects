import java.awt.*; //Color and GridLayout
import java.awt.event.*;
import java.io.*; //DataInputStream & DataOutputStream
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*; //JPanel & JPanel
import javax.swing.border.LineBorder;

/**
 * This is the Main Panel for the TicTacToe Client.
 * It uses a displayBoard of Cell objects to display the TicTacToe board
 * @author Professor Myers
 *
 */
public class TicTacToeClientPanel extends JPanel implements Runnable{
	// instance variables and constants
	private Cell displayBoard[][] = new Cell[3][3];
	JLabel playerLabel, statusLabel;
	Boolean myTurn, waiting, gameNotOver;
	char mySymbol;
	int rowSelected, columnSelected;
	Scanner fromServer;
	PrintWriter toServer;
	/**
	 * Constructor for the TicTacToePanel
	 */
	public TicTacToeClientPanel()
	{
		myTurn=waiting=gameNotOver= true;
		rowSelected=columnSelected=0;
		setPreferredSize(new Dimension(300, 350));
		
		playerLabel = new JLabel();
		add(playerLabel);
		
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(3,3));
		boardPanel.setPreferredSize(new Dimension(290, 300));
		
		for(int i = 0; i<3; i++)
		{
			for(int j = 0; j<3; j++)
			{
				displayBoard[i][j] = new Cell(i, j);
				boardPanel.add(displayBoard[i][j]);
			}
		}
		add(boardPanel);
		
		statusLabel = new JLabel();
		add(statusLabel);

		connectToServer();
	}
	/**
	 * Connects to the server and sets up a Scanner and a PrintWriter
	 */
	private void connectToServer()
	{
		try
		{
			// create socket
			// set up Scanner and PrintWriter
			Socket client = new Socket("localhost", 8800);
			InputStream instream = client.getInputStream();
			OutputStream outstream = client.getOutputStream();
			fromServer = new Scanner(instream);
			toServer = new PrintWriter(outstream);
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		//start the thread
		Thread thread = new Thread(this);
		thread.start();
	}
	/**
	 * Runs the game and communicates with TicTacToeService
	 */
	public void run()
	{
		try {
			int player = Integer.parseInt(fromServer.nextLine()); //Begin the game 

			//set up symbol
			//keep track of who's turn it is
			//Display the player number and symbol
			//Display the status of the player
			if(player == 1)
			{
				mySymbol = 'X';
				playerLabel.setText("Player 1 with symbol'X'");
			}
			else if(player == 2)
			{
				mySymbol = 'O';
				playerLabel.setText("Player 2 with symbol'O'");
				statusLabel.setText("Waiting for the other player to move");
			}
			
			int status, row, column;
			status=row=column=0;
			
			while(gameNotOver)
			{
				if(player == 1)
				{
					status = Integer.parseInt(fromServer.nextLine());
					
					if(status == 1)
					{
						statusLabel.setText("I won");
						gameNotOver = false;
					}
					else if(status == 2 || status == 3)
					{
						row = Integer.parseInt(fromServer.nextLine());
						column = Integer.parseInt(fromServer.nextLine());
						displayBoard[row][column].setSymbol('O');
						if(status == 2)
						{
							statusLabel.setText("Player 2 won!");
						}
						else
						{
							statusLabel.setText("Game is over, no winner");
						}
						gameNotOver = false;
					}
					else if(status == 4)
					{
						row = Integer.parseInt(fromServer.nextLine());
						column = Integer.parseInt(fromServer.nextLine());
						displayBoard[row][column].setSymbol('O');
						
						myTurn = true;
						statusLabel.setText("My Turn");
						Thread.sleep(7000);
						
						displayBoard[rowSelected][columnSelected].setSymbol('X');
						toServer.print("" + rowSelected + "\n");
						toServer.flush();
						toServer.print("" + columnSelected + "\n");
						toServer.flush();
						
						myTurn = false;
					}
					else if(status == 5)
					{
						statusLabel.setText("My Turn");
						myTurn = true;
						Thread.sleep(7000);
						
						displayBoard[rowSelected][columnSelected].setSymbol('X');
						toServer.print("" + rowSelected + "\n");
						toServer.flush();
						toServer.print("" + columnSelected + "\n");
						toServer.flush();
						
						myTurn = false;
					}
				}
				else if(player == 2)
				{
					status = Integer.parseInt(fromServer.nextLine());
					
					if(status == 1 || status == 3)
					{
						row = Integer.parseInt(fromServer.nextLine());
						column = Integer.parseInt(fromServer.nextLine());
						displayBoard[row][column].setSymbol('X');
						if(status == 1)
						{
							statusLabel.setText("Player 1 won!");
						}
						else
						{
							statusLabel.setText("Game is over, no winner");
						}
						gameNotOver = false;
					}
					else if(status == 2)
					{
						statusLabel.setText("I won");
						gameNotOver = false;
					}
					else if(status == 4)
					{
						row = Integer.parseInt(fromServer.nextLine());
						column = Integer.parseInt(fromServer.nextLine());
						displayBoard[row][column].setSymbol('X');
						
						myTurn = true;
						statusLabel.setText("My Turn");
						Thread.sleep(7000);
						
						displayBoard[rowSelected][columnSelected].setSymbol('O');
						toServer.print("" + rowSelected + "\n");
						toServer.flush();
						toServer.print("" + columnSelected + "\n");
						toServer.flush();
						
						myTurn = false;
					}
				}
			}
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * Class for the cells that make up the TicTacToe Board
	 * @author Professor Myers
	 *
	 */
	public class Cell extends JPanel
	{
		int row;
		int column;
		private char symbol;
		/**
		 * Constructor for the Cell
		 * @param r the row
		 * @param c the column
		 */
		public Cell(int r, int c)
		{
			row = r;
			column = c;
			symbol = ' ';
			setBorder(new LineBorder(Color.black,1));
			addMouseListener(new ClickListener());
		}
		/**
		 * Sets the symbol is to appear in this cell
		 * @param c the symbol
		 */
		public void setSymbol(char c)
		{
			symbol = c;
			repaint();
		}
		/**
		 * Gets the symbol in this cell
		 * @return symbol
		 */
		public char getSymbol()
		{
			return symbol;
		}
		/**
		 * Paints the cell with a symbol
		 */
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			if(symbol == 'X')
			{
				g.drawLine(10, 10, getWidth()-10, getHeight()-10);
				g.drawLine(getWidth()-10, 10, 10, getHeight()-10);
			}
			else if(symbol == 'O')
			{
				g.drawOval(10, 10, getWidth()-20, getHeight()-20);
			}                	
		}

		/**
		 * Click Listener for the cells
		 * @author Professor Myers
		 *
		 */
		private class ClickListener extends MouseAdapter
		{
			/**
			 * Responds if the cell is clicked
			 */
			public void mouseClicked(MouseEvent e)
			{

				if(symbol == ' ' && myTurn)
				{
					setSymbol(mySymbol);
					myTurn = false;
					rowSelected = row;
					columnSelected = column;
					statusLabel.setText("Waiting for the other player to move");
					waiting=false;
				}
			}
		}
	}
}
