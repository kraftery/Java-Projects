import javax.swing.JFrame;

/**
 * Creates a frame and displays the panel
 * @author Kieran Raftery
 *
 */
public class MorseCodeConverterFrame
{

	/**
	 * Creates a frame and displays the panel
	 * @param args 
	 */
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame ("Morse Code Converter");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		MorseCodeConverterPanel panel = new MorseCodeConverterPanel();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
	}

}