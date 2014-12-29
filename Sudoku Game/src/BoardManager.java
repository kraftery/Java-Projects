import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * An interface to manage a Sudoku game.  
 * Allows the user to start a new game, enter (set) a value for a row,column combination, 
 * retrieve (get) a value for a row,column combination, return a string representation of the
 * sudoku board and display all valid values for an entry in the puzzle.
 * When entering a value, this class will check the validity of a value at the specified row,column combination,
 * based on the rules of Sudoku.
 * The class that implements this interface will use a Data Structure of a two-dimensional array of 
 * integers to represent the sudoku game.
 * @author Kieran Raftery
 *
 */
public class BoardManager implements SudokuBoardManager
{
	int values[][];
	File board;
	/**
	 * Constructor for the board manager
	 */
	public BoardManager()
	{
		values = new int[9][9];
	}
	/** Set a value (v) at the row (r) and column (c).  If the value is valid for this row and column, 
	 * the value will be placed at that location.  If the value is invalid for this row and column,
	 * the value will not be placed at that location.
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @param v value to place in the row,column
	 * @throws InputOutOfRangeException if the values for the row or column are invalid
	 * @throws ValueNotValidException if the value is invalid for this row,column in the puzzle
	 */
	public void setValueAt(int r, int c, int v) throws InputOutOfRangeException, ValueNotValidException 
	{
		if(r<=9 && r>=1 && c<=9 && c>=1 && v<=9 && v>=1)
		{
			Boolean rowCheck, columnCheck, squareCheck;
			rowCheck=columnCheck=squareCheck=true;
			
			int[] row = new int[9];
			int[] column = new int[9];
			int[][] square = new int[3][3];
			int rowEntered = r-1;
			int columnEntered = c-1;
			int col = 0;
			int sr = 0;
			
			for(int i = 0; i<9; i++)//get the values in the same row
			{
				row[i] = values[rowEntered][i];
			}
			for(int i = 0; i<9; i++)//get the values in the same column
			{
				column[i] = values[i][columnEntered];
			}
			if(r>=1 && r<=3)//located in rows 1-3
			{
				if(c>=1 && c<=3)//located in columns 1-3
				{
					for(int i = 0; i<3; i++)//get the values in the same square
					{
						for(int j = 0; j<3; j++)
						{
							square[i][j] = values[i][j];
						}
					}
				}
				else if(c>=4 && c<=6)//located in columns 4-6
				{
					for(int i = 0; i<3; i++)
					{
						for(int j = 3; j<6; j++)
						{
							square[i][col] = values[i][j];
							col++;
						}
						col = 0;
					}
				}
				else if(c>=7 && c<=9)//located in columns 7-9
				{
					for(int i = 0; i<3; i++)
					{
						for(int j = 6; j<9; j++)
						{
							square[i][col] = values[i][j];
							col++;
						}
						col = 0;
					}
				}
				
			}
			else if(r>=4 && r<=6)//located in rows 4-6
			{
				if(c>=1 && c<=3)//located in columns 1-3
				{
					for(int i = 3; i<6; i++)
					{
						for(int j = 0; j<3; j++)
						{
							square[sr][j] = values[i][j];
							sr++;
						}
						sr = 0;
					}
				}
				else if(c>=4 && c<=6)//located in columns 4-6
				{
					for(int i = 3; i<6; i++)
					{
						for(int j = 3; j<6; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
				else if(c>=7 && c<=9)//located in columns 7-9
				{
					for(int i = 3; i<6; i++)
					{
						for(int j = 6; j<9; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
			}
			else if(r>=7 && r<=9)//located in rows 7-9
			{
				if(c>=1 && c<=3)//located in columns 1-3
				{
					for(int i = 6; i<9; i++)
					{
						for(int j = 0; j<3; j++)
						{
							square[sr][j] = values[i][j];
							sr++;
						}
						sr = 0;
					}
				}
				else if(c>=4 && c<=6)//located in columns 4-6
				{
					for(int i = 6; i<9; i++)
					{
						for(int j = 3; j<6; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
				else if(c>=7 && c<=9)//located in columns 7-9
				{
					for(int i = 6; i<9; i++)
					{
						for(int j = 6; j<9; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
			}
			
			for(int i = 0; i<row.length; i++)//check the row for equal value
			{
				if(row[i] == v)
				{
					rowCheck = false;
				}
			}
			for(int i = 0; i<column.length; i++)//check the column for equal value
			{
				if(column[i] == v)
				{
					columnCheck = false;
				}
			}
			for(int i = 0; i<3; i++)
			{
				for(int j = 0; j<3; j++)
				{
					if(square[i][j] == v)
					{
						squareCheck = false;
					}
				}
			}
			
			if((rowCheck)&&(columnCheck)&&(squareCheck))
			{
				values[rowEntered][columnEntered] = v;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This not a valid entry for this row or column.", "Error", JOptionPane.ERROR_MESSAGE);
				throw new ValueNotValidException("This not a valid entry for this row or column.");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please enter a number 1-9", "Error", JOptionPane.ERROR_MESSAGE);
			throw new InputOutOfRangeException("Please enter a number 1-9");
		}
	}

	/** Get the value at the row (r) and column (c).  
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @throws InputOutOfRangeException if the values for the row or column are invalid
	 * @return the value for this row,column in the puzzle, returns 0 if value was not previously set
	 */	
	public int getValueAt(int r, int c) throws InputOutOfRangeException 
	{
		
		if(r<=9 && r>=1 && c<=9 && c>=1)
		{
			return values[r-1][c-1];
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please enter a number 1-9", "Error", JOptionPane.ERROR_MESSAGE);
			throw new InputOutOfRangeException("Please enter a number 1-9");
		}
		
	}

	/**
	 * Determines all possible values for this row, column combination in the puzzle, based on the
	 * rules of Sudoku
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @return an array of integers representing all possible values for this row,column 
	 * combination in the puzzle in numeric order, i.e. 3 6 9
	 * @throws InputOutOfRangeException if the values for the row or column are invalid
	 */
	public int[] displayPossibleValues(int r, int c) throws InputOutOfRangeException 
	{
		if(r<=9 && r>=1 && c<=9 && c>=1)
		{
			int[] possibleValues = new int[9];
			int rowEntered = r-1;
			int columnEntered = c-1;
			int[] row = new int[9];
			int[] column = new int[9];
			int[][] square = new int[3][3];
			int col = 0;
			int sr = 0;
			
			for(int i = 0; i<9; i++)//get the values in the same row
			{
				row[i] = values[rowEntered][i];
			}
			for(int i = 0; i<9; i++)//get the values in the same column
			{
				column[i] = values[i][columnEntered];
			}
			if(r>=1 && r<=3)//located in rows 1-3
			{
				if(c>=1 && c<=3)//located in columns 1-3
				{
					for(int i = 0; i<3; i++)//get the values in the same square
					{
						for(int j = 0; j<3; j++)
						{
							square[i][j] = values[i][j];
						}
					}
				}
				else if(c>=4 && c<=6)//located in columns 4-6
				{
					for(int i = 0; i<3; i++)
					{
						for(int j = 3; j<6; j++)
						{
							square[i][col] = values[i][j];
							col++;
						}
						col = 0;
					}
				}
				else if(c>=7 && c<=9)//located in columns 7-9
				{
					for(int i = 0; i<3; i++)
					{
						for(int j = 6; j<9; j++)
						{
							square[i][col] = values[i][j];
							col++;
						}
						col = 0;
					}
				}
				
			}
			else if(r>=4 && r<=6)//located in rows 4-6
			{
				if(c>=1 && c<=3)//located in columns 1-3
				{
					for(int i = 3; i<6; i++)
					{
						for(int j = 0; j<3; j++)
						{
							square[sr][j] = values[i][j];
							sr++;
						}
						sr = 0;
					}
				}
				else if(c>=4 && c<=6)//located in columns 4-6
				{
					for(int i = 3; i<6; i++)
					{
						for(int j = 3; j<6; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
				else if(c>=7 && c<=9)//located in columns 7-9
				{
					for(int i = 3; i<6; i++)
					{
						for(int j = 6; j<9; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
			}
			else if(r>=7 && r<=9)//located in rows 7-9
			{
				if(c>=1 && c<=3)//located in columns 1-3
				{
					for(int i = 6; i<9; i++)
					{
						for(int j = 0; j<3; j++)
						{
							square[sr][j] = values[i][j];
							sr++;
						}
						sr = 0;
					}
				}
				else if(c>=4 && c<=6)//located in columns 4-6
				{
					for(int i = 6; i<9; i++)
					{
						for(int j = 3; j<6; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
				else if(c>=7 && c<=9)//located in columns 7-9
				{
					for(int i = 6; i<9; i++)
					{
						for(int j = 6; j<9; j++)
						{
							square[sr][col] = values[i][j];
							sr++;
							col++;
						}
						col = 0;
						sr = 0;
					}
				}
			}
			
			int count = 0;
			boolean notInRow, notInColumn, notInSquare;
			notInRow=notInColumn=notInSquare=true;
			for(int i = 1; i<10; i++)//check each number 1-9
			{
				for(int j =0; j<9; j++)//check for the number in the row
				{
					if(row[j] == i)
					{
						notInRow = false;
						break;
					}
					notInRow = true;
				}
				for(int j =0; j<9; j++)//check for the number in the column
				{
					if(column[j] == i)
					{
						notInColumn = false;
						break;
					}
					notInColumn = true;
				}
				outerloop:
				for(int j =0; j<3; j++)//check for the number in the square
				{
					for(int k =0; k<3; k++)
					{
						if(square[j][k] == i)
						{
							notInSquare = false;
							break outerloop;
						}
						notInSquare = true;
					}
				}
				
				if(notInRow && notInColumn && notInSquare)
				{
					possibleValues[count] = i;
					count++;
				}
			}
			
			return possibleValues;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please enter a number 1-9", "Error", JOptionPane.ERROR_MESSAGE);
			throw new InputOutOfRangeException("Please enter a number 1-9");
		}
	}

	/**
	 * 
	 * @param gameFile the File with the representation of a new sudoku game.
	 * File will contain representation of the sudoku board in the following format:
	 *   0,0,2,0,8,0,0,0,1 (new line)
	 *   1,0,0,4,0,2,0,0,6 (new line)
	 *   . . .
	 * 9 rows separated with a new line.  Each row contains 9 values separated by a comma.
	 * A 0 represents an empty value
	 * 
	 */
	public void newGame(File gameFile) 
	{
		board = gameFile;
		FileReader reader = null;
		try {
			reader = new FileReader(gameFile);
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
	}
	/** Return string representation of the sudoku board in the following format:
	 *   0,0,2,0,8,0,0,0,1 (new line)
	 *   1,0,0,4,0,2,0,0,6 (new line)
	 *   . . .
	 *   
	 * @return string representation of the sudoku board
	 */
	public String toString()
	{
		String boardString = "";
		FileReader reader = null;
		try 
		{
			reader = new FileReader(board);
			Scanner in = new Scanner(reader);
			in.useDelimiter(",|\\n");
			while(in.hasNext())
			{
				boardString += in.next() + ",";
			}
			
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return boardString;
	}

}
