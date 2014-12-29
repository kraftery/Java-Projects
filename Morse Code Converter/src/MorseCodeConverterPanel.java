import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Creates a panel that allows a user to convert morse code to english
 * @author Kieran Raftery
 *
 */
public class MorseCodeConverterPanel extends JPanel 
{
	JTextArea morseArea, englishArea;
	JButton exitButton, convertFileButton, convertStringButton;
	
	/**
	 * Create the panel.
	 */
	public MorseCodeConverterPanel() 
	{
		setPreferredSize(new Dimension(450, 300));
		
		JLabel morseLabel = new JLabel("Morse Code (' ' is seperator for letters '/' for words)");
		add(morseLabel);
		
		morseArea = new JTextArea();
		JScrollPane morseScroll = new JScrollPane(morseArea);
		morseScroll.setPreferredSize(new Dimension(400, 100));
		add(morseScroll);
		
		JLabel englishLabel = new JLabel("English Translation");
		add(englishLabel);
		
		englishArea = new JTextArea();
		JScrollPane englishScroll = new JScrollPane(englishArea);
		englishScroll.setPreferredSize(new Dimension(400, 100));
		add(englishScroll);
		
		convertFileButton = new JButton("Convert File");
		convertFileButton.addActionListener(new ButtonListener());
		convertStringButton = new JButton("Convert String");
		convertStringButton.addActionListener(new ButtonListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ButtonListener());
		
		add(convertFileButton);
		add(convertStringButton);
		add(exitButton);
	}
	
	/**
	 * A listener for the buttons
	 * @author Kieran Raftery
	 *
	 */
	private class ButtonListener implements ActionListener
	{
		/**
		 * Responds when a button is clicked
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) 
		{
			Object source = e.getSource();
			
			if(source == convertFileButton)
			{
				JFileChooser chooser = new JFileChooser();
				
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						File file = chooser.getSelectedFile();
						String morse = new Scanner(file).useDelimiter("\\Z").next();
						String english = MorseCodeConverter.convertToEnglish(file);
						
						morseArea.setText(morse);
						englishArea.setText(english);
					}
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else if(source == convertStringButton)
			{
				String english = MorseCodeConverter.convertToEnglish(morseArea.getText());
				englishArea.setText(english);
			}
			else if(source == exitButton)
			{
				System.exit(0);
			}
		}
	}
}
