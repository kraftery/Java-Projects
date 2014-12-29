import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

/**
 * This class creates the MorseCodeTree and converts Strings and files from morse code to english
 * @author Kieran Raftery
 *
 */
public class MorseCodeConverter 
{
	static boolean treeIsBuilt;
	static TreeMap<String, Character> MorseCodeTree;
	/**
	 * Constructor for MorseCodeConverter
	 */
	public MorseCodeConverter()
	{
		treeIsBuilt = false;
	}
	/**
	 * This method builds the morseCodeTree. Your MorseCode Tree is a TreeMap with 4 levels. 
	 * Insert a mapping for every letter of the alphabet into the tree map. T
	 * he root has a key of an empty string with a space character <сс,у т>. 
	 * The left node at level 1 stores letter тeу (code т.у) and the right node stores letter тtу (code т-т). 
	 * The 4 nodes at level 2 are тiу, тaу, тnу, тmу (code т..у, т.-т, т-.у, тят). 
	 * Insert into the tree by tree level from left to right. A т.у will take the branch to the left and a т-т will take the branch to the right. 
	 * With the MorseCodeComparator implemented, this is automatically done with the put method in the TreeMap. 
	 */
	private static void buildTree()
	{
		Comparator<String> MorseCodeComparator = new Comparator<String>(){
			public int compare(String s1, String s2)
			{
				if(s1.equals(""))
				{
					if(s2.charAt(0) == '.')
					{
						return -1;
					}
					else if(s2.charAt(0) == '-')
					{
						return 1;
					}
				}
				if(s2.equals(""))
				{
					if(s1.charAt(0) == '.')
					{
						return -1;
					}
					else if(s1.charAt(0) == '-')
					{
						return 1;
					}
				}
				
				return - s1.compareTo(s2);
			}
		};
		
		MorseCodeTree = new TreeMap<String, Character>(MorseCodeComparator);
		
		//root
		MorseCodeTree.put("", ' ');
		//level 1
		MorseCodeTree.put(".", 'e');
		MorseCodeTree.put("-", 't');
		//level 2
		MorseCodeTree.put("..", 'i');
		MorseCodeTree.put(".-", 'a');
		MorseCodeTree.put("-.", 'n');
		MorseCodeTree.put("--", 'm');
		//level 3
		MorseCodeTree.put("...", 's');
		MorseCodeTree.put("..-", 'u');
		MorseCodeTree.put(".-.", 'r');
		MorseCodeTree.put(".--", 'w');
		MorseCodeTree.put("-..", 'd');
		MorseCodeTree.put("-.-", 'k');
		MorseCodeTree.put("--.", 'g');
		MorseCodeTree.put("---", 'o');
		//level 4
		MorseCodeTree.put("....", 'h');
		MorseCodeTree.put("...-", 'v');
		MorseCodeTree.put("..-.", 'f');
		MorseCodeTree.put(".-..", 'l');
		MorseCodeTree.put(".--.", 'p');
		MorseCodeTree.put(".---", 'j');
		MorseCodeTree.put("-...", 'b');
		MorseCodeTree.put("-..-", 'x');
		MorseCodeTree.put("-.-.", 'c');
		MorseCodeTree.put("-.--", 'y');
		MorseCodeTree.put("--..", 'z');
		MorseCodeTree.put("--.-", 'q');
		
		treeIsBuilt = true;
	}
	/**
	 * This is a private testing method to make sure the tree is build correctly. 
	 * Prints out the in order traversal of the tree i.e. h s v i f u e l . . .
	 */
	private static void printTree()
	{
		if(!treeIsBuilt)
		{
			buildTree();
		}
		
		for(Entry<String, Character> entry : MorseCodeTree.entrySet())
		{
			System.out.print(entry.getValue() + " ");
		}
	}
	/**
	 * Converts a string of morse code to English. Check if the tree has been built. If not, build it.
	 * @param code - string of morse code
	 * @return conversion - the english conversion
	 */
	public static String convertToEnglish(String code)
	{
		String conversion = "";
		
		if(!treeIsBuilt)
		{
			buildTree();
		}
			
		code = code.replaceAll("/", "\n");
		Scanner scan = new Scanner(code);
		while(scan.hasNext())
		{
			Scanner word = new Scanner(scan.nextLine());
			while(word.hasNext())
			{
				conversion += MorseCodeTree.get(word.next());
			}
			conversion += " ";
		}
		
		conversion = conversion.trim();
		return conversion;
	}
	/**
	 * Converts a morse code file to English. Check if the tree has been built. If not, build it.
	 * @param code - file containing morse code
	 * @return conversion - the english conversion
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File code) throws FileNotFoundException
	{
		String conversion = "";
		
		if(!treeIsBuilt)
		{
			buildTree();
		}
		
		try
		{
			String fileToString = new Scanner(code).useDelimiter("\\Z").next();
			fileToString = fileToString.replaceAll("/", "\n");
			Scanner scan = new Scanner(fileToString);
			while(scan.hasNext())
			{
				Scanner word = new Scanner(scan.nextLine());
				while(word.hasNext())
				{
					conversion += MorseCodeTree.get(word.next());
				}
				conversion += " ";
			}
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		conversion = conversion.trim();
		return conversion;
	}
}
