import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Server for the TicTacToe game
 * @author Kieran Raftery
 *
 */
public class TicTacToeServer 
{	
	/**
	 * Runs the server
	 * @param args
	 */
	public static void main(String args[]) 
	{
		JFrame frame = new JFrame ("Tic Tac Toe Server");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 200));
		
		JTextArea serverText = new JTextArea();
		
		frame.add(serverText);
		
		frame.pack();
		frame.setVisible(true);
		
		try 
		{
			
			ServerSocket server = new ServerSocket(8800);
			serverText.setText("Server started at socket 8800\nWait for players to join session");

			while(true)
			{
				Socket client1 = server.accept();
				serverText.setText(serverText.getText() + "\nPlayer1 joined session");
				Socket client2 = server.accept();
				serverText.setText(serverText.getText() + "\nPlayer2 joined session");

				serverText.setText(serverText.getText() + "\nStart a thread for the session");
				TicTacToeService session = new TicTacToeService(client1, client2);
				Thread thread = new Thread(session);
				thread.start();
			}
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}
}
