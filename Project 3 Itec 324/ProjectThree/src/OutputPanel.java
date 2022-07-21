import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * 
 * @author Chris Gunter
 * @version April 8th, 2018
 * 
 * A runnable class that conducts the instructions in the thread.
 * Counts the number of letters and words in a file.
 */
public class OutputPanel implements Runnable
{
	// private variables 
	private int alphNums = 0; // number of letters
	private int numWords = 0; // number of words
	private String fileContents = ""; // file put into a string
	private String tempLine = null; // temp string to get lines in a file
	private JLabel alphabet; // label for number of letters
	private JLabel words; // label for number of words
	
	private JPanel pnl; // panel for the class
	
	/**
	 * Constructor for OutputPanel
	 * @param fileName
	 */
	public OutputPanel(String fileName)
	{
		JFrame frm = new JFrame("Output Panel - " + fileName);
		pnl = new JPanel();
		
		frm.setLayout(new BorderLayout());
		
		alphabet = new JLabel("Alphabet Letters: " + alphNums);
		words = new JLabel("Words : " + numWords);
		
		pnl.add(alphabet);
		pnl.add(words);
		//C:\Users\Duffi\Desktop\Desktop\Collection Of Software Projects\Itec324\Project 3 Itec 324\ProjectThree\src\text\test0.txt
		try 
		{
			//FileReader and BufferedReader for file
			FileReader fileRead = new FileReader(fileName);
			BufferedReader buffRead = new BufferedReader(fileRead);
			
			// Gets a new line from file and adds to string
			while((tempLine = buffRead.readLine()) != null)
			{
				fileContents += tempLine;
				fileContents += " ";
			}
			buffRead.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Unable to open file : " + fileName);
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.err.println("Error when reading file : " + fileName);
			e.printStackTrace();
		}
		
		// sets all components to visible
		pnl.setVisible(true);
		frm.add(pnl, BorderLayout.CENTER);
		frm.pack();
		frm.setSize(400,200);
		frm.setResizable(false);
		frm.setLocation((int)Math.random() * 401, (int)Math.random() * 401);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setVisible(true);
	}
	
	/**
	 * Updates the number of letters
	 * @param newAlph new number of letters
	 */
	public void setAlphabet(int newAlph)
	{
		alphNums = newAlph;
		alphabet.setText("Alphabet Letters: " + alphNums);
		pnl.revalidate();
		pnl.repaint();
	}

	/**
	 * Updates the number of words
	 * @param newWords new number of words
	 */
	public void setWords(int newWords)
	{
		numWords = newWords;
		words.setText("Words : " + numWords);
		pnl.revalidate();
		pnl.repaint();
	}

	
	@Override
	/**
	 * The run method for the thread
	 */
	public void run() 
	{
		for(int x = 0; x < fileContents.length(); x++)
		{
			if((fileContents.charAt(x) >= 'A' && fileContents.charAt(x) <= 'Z')
					|| (fileContents.charAt(x) >= 'a' && fileContents.charAt(x) <= 'z'))
			{
				alphNums++;
				this.setAlphabet(alphNums);
			}
			else if((fileContents.charAt(x) == ' ') || (fileContents.charAt(x) == '\n'))
			{
				numWords++;
				this.setWords(numWords);
			}
			try {
				Thread.sleep(100);;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}
