import javax.swing.JFrame;

/**
 * Creates a frame and displays the panel
 * @author Kieran Raftery
 *
 */
public class SudokuBoardFrame 
{

	/**
	 * Creates a frame and displays the panel
	 * @param args 
	 */
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame ("Sudoku");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		SudokuBoardPanel panel = new SudokuBoardPanel();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
	}

}