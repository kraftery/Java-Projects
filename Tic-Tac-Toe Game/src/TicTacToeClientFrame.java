import javax.swing.JFrame;

/**
 * Creates a frame and displays the panel
 * @author Kieran Raftery
 *
 */
public class TicTacToeClientFrame
{

	/**
	 * Creates a frame and displays the panel
	 * @param args 
	 */
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame ("Tic Tac Toe");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		TicTacToeClientPanel panel = new TicTacToeClientPanel();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
	}

}