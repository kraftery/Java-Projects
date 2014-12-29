import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Connects two players and keeps track of the board
 * @author Kieran Raftery
 *
 */
public class TicTacToeService implements Runnable
{
	int board[][];
	Scanner player1Input, player2Input;
	PrintWriter player1Output, player2Output;
	int count;
	/**
	 * Constructor for TicTacToeService
	 * @param s1 player1
	 * @param s2 player2
	 */
	public TicTacToeService(Socket s1, Socket s2)
	{
		board = new int[3][3];
		count = 0;
		
		try 
		{
			DataInputStream player1InputStream = new DataInputStream(s1.getInputStream());
			DataOutputStream player1OutputStream = new DataOutputStream(s1.getOutputStream());

			DataInputStream player2InputStream = new DataInputStream(s2.getInputStream());
			DataOutputStream player2OutputStream = new DataOutputStream(s2.getOutputStream());

			player1Input = new Scanner(player1InputStream);
			player2Input = new Scanner(player2InputStream);

			player1Output = new PrintWriter(player1OutputStream);
			player2Output = new PrintWriter(player2OutputStream);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	/**
	 * Starts the game and continues playing until the game is over
	 */
	public void run()
	{
		//Tells the clients which players they are
		player1Output.print("1" + "\n");
		player1Output.flush();
		player2Output.print("2" + "\n");
		player2Output.flush();
		//starts the game
		player1Output.print("5" + "\n");
		player1Output.flush();
		
		int row, column, status;
		row=column=status=0;
		
		while(true)
		{
			row = Integer.parseInt(player1Input.nextLine());
			column = Integer.parseInt(player1Input.nextLine());
			board[row][column] = 1;
			
			status = this.checkBoard();
			if(status == 1 || status == 2 || status == 3)
			{
				player1Output.print("" + status + "\n");
				player1Output.flush();
				player1Output.print("" + row + "\n");
				player1Output.flush();
				player1Output.print("" + column + "\n");
				player1Output.flush();
				player2Output.print("" + status + "\n");
				player2Output.flush();
				player2Output.print("" + row + "\n");
				player2Output.flush();
				player2Output.print("" + column + "\n");
				player2Output.flush();
				break;
			}
			else if(status == 4)
			{
				player2Output.print("" + status + "\n");
				player2Output.flush();
				player2Output.print("" + row + "\n");
				player2Output.flush();
				player2Output.print("" + column + "\n");
				player2Output.flush();
				
				row = Integer.parseInt(player2Input.nextLine());
				column = Integer.parseInt(player2Input.nextLine());
				board[row][column] = 2;
				
				status = this.checkBoard();
				if(status == 2)
				{
					player1Output.print("" + status + "\n");
					player1Output.flush();
					player1Output.print("" + row + "\n");
					player1Output.flush();
					player1Output.print("" + column + "\n");
					player1Output.flush();
					player2Output.print("" + status + "\n");
					player2Output.flush();
					break;
				}
				else if(status == 4)
				{
					player1Output.print("" + status + "\n");
					player1Output.flush();
					player1Output.print("" + row + "\n");
					player1Output.flush();
					player1Output.print("" + column + "\n");
					player1Output.flush();
				}
			}
		}
	}
	/**
	 * Checks the board to see if the game is over
	 * @return status 1 if player 1 has won, 2 if player 2 has won, 3 if the board is full, 4 if the game is not over
	 */
	public int checkBoard()
	{
		int status = 0;
	
		count++;
		if(count == 9)
		{
			return 3;//board is full
		}
		
		for(int i = 0; i<3; i++)
		{
			if((board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1) || (board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1) || (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) || (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1))
			{
				status = 1;//player 1 wins
				break;
			}
			else if((board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 2) || (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2) || (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) || (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2))
			{
				status = 2;//player 2 wins
				break;
			}
			else
			{
				status = 4;//continue
			}
		}
		
		return status;
	}
}
