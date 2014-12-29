import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Creates the sudoku board and provides a way for the user to enter values
 * @author Kieran Raftery
 *
 */
public class SudokuBoardPanel extends JPanel
{
	JTextField board[][];
	JLabel enterRowLabel, enterColumnLabel, enterValueLabel, possibleRowLabel, possibleColumnLabel;
	JTextField enterRowText, enterColumnText, enterValueText, possibleRowText, possibleColumnText, possibleValuesText;
	JButton enterButton, possibleValuesButton, newGameButton, exitButton;
	BoardManager manager;
	/**
	 * Constructor for SudokuBoardPanel
	 */
	public SudokuBoardPanel()
	{
		setPreferredSize(new Dimension(600, 600));
		
		//creating the board
		board = new JTextField[9][9];
		for(int i = 0; i<9; i++)
		{
			for(int j = 0; j<9; j++)
			{
				board[i][j] = new JTextField(3);
				board[i][j].setEditable(false);
				
				//the background color on some textfields is changed to show the different areas
				if((i==0 || i==1 || i==2) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8))
				{
					board[i][j].setBackground(Color.YELLOW);
				}
				if((i==3 || i==4 || i==5) && (j==3 || j==4 || j==5))
				{
					board[i][j].setBackground(Color.YELLOW);
				}
				if((i==6 || i==7 || i==8) && (j==0 || j==1 || j==2 || j==6 || j==7 || j==8))
				{
					board[i][j].setBackground(Color.YELLOW);
				}
			}
		}
		
		//create sub panels to display the board
		JPanel boardAndLabelsPanel = new JPanel();
		boardAndLabelsPanel.setPreferredSize(new Dimension(600, 350));
		
		JPanel columnsPanel = new JPanel();
		columnsPanel.setPreferredSize(new Dimension(600, 30));
		columnsPanel.setLayout(new BoxLayout(columnsPanel, BoxLayout.X_AXIS));
		JLabel column1 = new JLabel("1");
		JLabel column2 = new JLabel("2");
		JLabel column3 = new JLabel("3");
		JLabel column4 = new JLabel("4");
		JLabel column5 = new JLabel("5");
		JLabel column6 = new JLabel("6");
		JLabel column7 = new JLabel("7");
		JLabel column8 = new JLabel("8");
		JLabel column9 = new JLabel("9");
		
		columnsPanel.add(Box.createRigidArea(new Dimension(100,0)));
		columnsPanel.add(column1);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column2);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column3);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column4);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column5);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column6);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column7);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column8);
		columnsPanel.add(Box.createRigidArea(new Dimension(47,0)));
		columnsPanel.add(column9);
		
		JPanel rowPanel = new JPanel();
		rowPanel.setPreferredSize(new Dimension(45, 315));
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
		JLabel row1 = new JLabel("Row 1");
		JLabel row2 = new JLabel("Row 2");
		JLabel row3 = new JLabel("Row 3");
		JLabel row4 = new JLabel("Row 4");
		JLabel row5 = new JLabel("Row 5");
		JLabel row6 = new JLabel("Row 6");
		JLabel row7 = new JLabel("Row 7");
		JLabel row8 = new JLabel("Row 8");
		JLabel row9 = new JLabel("Row 9");
		
		rowPanel.add(Box.createRigidArea(new Dimension(0,10)));
		rowPanel.add(row1);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row2);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row3);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row4);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row5);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row6);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row7);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row8);
		rowPanel.add(Box.createRigidArea(new Dimension(0,17)));
		rowPanel.add(row9);
		
		JPanel boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(500, 320));
		for(int i = 0; i<9; i++)
		{
			for(int j = 0; j<9; j++)
			{
				boardPanel.add(board[i][j]);
			}
		}
		
		boardAndLabelsPanel.add(columnsPanel);
		boardAndLabelsPanel.add(rowPanel);
		boardAndLabelsPanel.add(boardPanel);
		
		//create sub panels to display the area for entering values and possible values
		JPanel valuesPanel = new JPanel();
		valuesPanel.setPreferredSize(new Dimension(600, 200));
		
		JPanel enterValuePanel = new JPanel();
		enterValuePanel.setPreferredSize(new Dimension(290, 190));
		enterValuePanel.setBorder(BorderFactory.createTitledBorder("Enter Value"));
		enterRowLabel = new JLabel("Row");
		enterColumnLabel = new JLabel("Column");
		enterValueLabel = new JLabel("Value");
		enterRowText = new JTextField(17);
		enterRowText.setMaximumSize(enterRowText.getPreferredSize());
		enterColumnText = new JTextField(17);
		enterColumnText.setMaximumSize(enterColumnText.getPreferredSize());
		enterValueText = new JTextField(17);
		enterValueText.setMaximumSize(enterValueText.getPreferredSize());
		enterButton = new JButton("Enter");
		enterButton.addActionListener(new ButtonListener());
		
		enterValuePanel.add(enterRowLabel);
		enterValuePanel.add(enterRowText);
		enterValuePanel.add(enterColumnLabel);
		enterValuePanel.add(enterColumnText);
		enterValuePanel.add(enterValueLabel);
		enterValuePanel.add(enterValueText);
		enterValuePanel.add(enterButton);
		
		JPanel possibleValuePanel = new JPanel();
		possibleValuePanel.setPreferredSize(new Dimension(300, 190));
		possibleValuePanel.setBorder(BorderFactory.createTitledBorder("Display Possible Values"));
		possibleRowLabel = new JLabel("Row");
		possibleColumnLabel = new JLabel("Column");
		possibleRowText = new JTextField(16);
		possibleColumnText = new JTextField(16);
		possibleValuesText = new JTextField(17);
		possibleValuesText.setEditable(false);
		possibleValuesButton = new JButton("Display Possible Values");
		possibleValuesButton.addActionListener(new ButtonListener());
		
		possibleValuePanel.add(possibleRowLabel);
		possibleValuePanel.add(possibleRowText);
		possibleValuePanel.add(possibleColumnLabel);
		possibleValuePanel.add(possibleColumnText);
		possibleValuePanel.add(possibleValuesText);
		possibleValuePanel.add(possibleValuesButton);
		
		valuesPanel.add(enterValuePanel);
		valuesPanel.add(possibleValuePanel);
		
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ButtonListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ButtonListener());
		
		//add all the sub panels to the panel
		add(boardAndLabelsPanel);
		add(valuesPanel);
		add(newGameButton);
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
			
			if(source == enterButton)//if the enter button is clicked
			{
				int row, column, value;
				
				row = Integer.parseInt(enterRowText.getText());
				column = Integer.parseInt(enterColumnText.getText());
				value = Integer.parseInt(enterValueText.getText());
				
				manager.setValueAt(row, column, value);
				
				board[row-1][column-1].setText(String.valueOf(manager.getValueAt(row, column)));
				
			}
			else if(source == possibleValuesButton)//if the possible values button is clicked
			{
				int row, column;
				int[] possibleValues = new int[9];
				row = Integer.parseInt(possibleRowText.getText());
				column = Integer.parseInt(possibleColumnText.getText());	
				String possibleValuesString = "";
				
				possibleValues = manager.displayPossibleValues(row, column);
				
				for(int i = 0; i<9; i++)
				{
					if(possibleValues[i] != 0)
					{
						possibleValuesString += String.valueOf(possibleValues[i]) + " ";
					}
				}
				
				possibleValuesText.setText(possibleValuesString);
			}
			else if(source == newGameButton)//if the new game button is clicked
			{
				manager = new BoardManager();
				JFileChooser chooser = new JFileChooser();
				FileReader reader = null;
				int[][] values = new int[9][9];
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = chooser.getSelectedFile();
					manager.newGame(selectedFile);
					try {
						reader = new FileReader(selectedFile);
						Scanner in = new Scanner(reader);
						in.useDelimiter(",|\\n");
						
						for(int i = 0; i<9; i++)
						{
							for(int j = 0; j<9; j++)
							{
								try
								{
									values[i][j] = Integer.parseInt(in.next());
								}
								catch (NumberFormatException ex) 
								{
									  continue;
								}
							}
						}
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					for(int i = 0; i<9; i++)
					{
						for(int j = 0; j<9; j++)
						{
							if(values[i][j]==0)
							{
								board[i][j].setText(" ");
							}
							else	
							{
								board[i][j].setText(String.valueOf(values[i][j]));
							}
						}	
					}
				}
			}
			else if(source == exitButton)//if the exit button is clicked
			{
				System.exit(0);
			}
		}
		
	}
}


