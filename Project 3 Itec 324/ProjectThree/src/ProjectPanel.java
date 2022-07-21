import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

/**
 * 
 * @author Chris Gunter
 * @version April 8th, 2018
 * 
 * Displays the main JFrame and JPanel.
 * Allows the user to enter files and start thread execution.
 * Once start button is pressed threads will execute and search through files.
 * The threads will calculate the number of letters and words in the files.
 */
public class ProjectPanel 
{
	/**
	 * Constructor for ProjectPanel
	 */
	public ProjectPanel()
	{
		// JFrame and JPanel
		JFrame frm = new JFrame("Project 3");
		JPanel pnl = new JPanel();
		
		// Stack used to collect strings of filenames
		Stack<String> stack = new Stack<String>();
		
		frm.setLayout(new BorderLayout());
		
		// Toolbars, JButtons, and JLabels for the Panel.
		JToolBar tbar = new JToolBar();
		JTextField fileInput = new JTextField(50);
		JButton addFile = new JButton("ADD");
		JButton startThreads = new JButton("START");
		JLabel filesAdded = new JLabel("FILES : ");
		
		// All components are added.
		pnl.add(fileInput);
		tbar.add(addFile);
		tbar.add(startThreads);
		frm.add(tbar, BorderLayout.NORTH);
		pnl.add(filesAdded);
		
		// add actionlistener for the add button
		addFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// adds file name to stack 
				String tempStr = fileInput.getText();
				stack.push(tempStr);
				
				// creates JLabel for filename
				JLabel temp = new JLabel(tempStr);
				
				// adds label to panel and repaints.
				pnl.add(temp);
				pnl.revalidate();
				pnl.repaint();
			}
		});
		
		// actionListener for Start button
		startThreads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				// create JLabel to differentiate old and new files
				JLabel temp = new JLabel("|| FILES : ");
				pnl.add(temp);
				pnl.revalidate();
				pnl.repaint();
				
				// Loops through stack and creates threads for each file name.
				while(!(stack.isEmpty()))
				{
					String tempStr = stack.peek();
					OutputPanel tempPnl = new OutputPanel(tempStr);
					Thread t = new Thread(tempPnl);
					t.start();
					stack.pop();
				}
			}
		});
		
		// sets all components visible
		pnl.setVisible(true);
		frm.add(pnl, BorderLayout.CENTER);
		frm.pack();
		frm.setSize(600,400);
		frm.setResizable(false);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}
}
