import static org.junit.Assert.*;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SubokuBoardManagerTest {

	private static SudokuBoardManager myBoard;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myBoard = new BoardManager();
		JFileChooser chooser = new JFileChooser();
		int status;
		
		chooser.setDialogTitle("Select Sudoku Game File");
		status = chooser.showOpenDialog(null);
		
		if(status == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				File inFile = chooser.getSelectedFile();
				myBoard.newGame(inFile);

			}
			catch(InputMismatchException e)
			{
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myBoard = null;
	}

	@Test
	public void testSetValueAt() {
			try {
				assertEquals(myBoard.getValueAt(2,2),0);
				myBoard.setValueAt(2,2,4);
				assertEquals(myBoard.getValueAt(2,2),4);
				myBoard.setValueAt(2,2,8);
				fail("This statement should have thrown a ValueNotValidException");
			} catch (InputOutOfRangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ValueNotValidException e) {
				// TODO Auto-generated catch block
				System.out.println("This is an invalid value");
			}
		

	}

	@Test
	public void testGetValueAt() {
		try {
			assertEquals(myBoard.getValueAt(1,1),8);
			myBoard.getValueAt(5,10);
			fail("This statement should have thrown an InputOutOfRangeException");
		} catch (InputOutOfRangeException e) {
			// TODO Auto-generated catch block
			System.out.println("This is an invalid column value");
		}
	}

	@Test
	public void testToString() {
		String boardString, resultString;
		boardString = myBoard.toString();
		resultString = boardString.substring(0,17);
		assertEquals(resultString,"8,0,0,3,0,9,0,0,5");
		resultString = boardString.substring(72,89);
		assertEquals(resultString,"0,0,1,0,0,0,7,0,0");
	}

	@Test
	public void testDisplayPossibleValues() {
		int[] results;
		try {
			results = myBoard.displayPossibleValues(2, 2);
			assertEquals(results[0],1);
			assertEquals(results[1],4);
			assertEquals(results[2],6);
			assertEquals(results[3],9);
			
		} catch (InputOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}