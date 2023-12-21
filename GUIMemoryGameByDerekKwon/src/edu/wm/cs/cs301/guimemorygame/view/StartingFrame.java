package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import edu.wm.cs.cs301.guimemorygame.model.MemoryModel;


public class StartingFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JFrame frame;
	
	public String username;
	
	private StartingFrame newStart;
	

	public StartingFrame() {
		
		this.frame = initialize();
		this.username = "";
		this.newStart = this;
		// TODO Auto-generated constructor stub
		
	}
	
	private String buildLeader() //build text for the leaderboard
	{
		String result = "<html>     LEADERBOARD     " + "<br>" + "======================" + "<br><html>";
		try {
			BufferedReader scoreReadStart = new BufferedReader(new FileReader("resources/leaderboard.txt"));
			result = result + "<html>user" + "\t" + "score" + "\t" + "difficulty<br><html>";
			String s;
			while ((s = scoreReadStart.readLine()) != null)
			{
				result = result + s + "<br>";
			}
			scoreReadStart.close();
			result = result + "<html>";
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			result = result + "No games have been played yet, so there is no leaderboard";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	private JFrame initialize() //initialize the frame
	{
		JFrame start = new JFrame();
		
		start.setTitle("Welcome Page");
		start.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		start.setSize(600,600);
		start.setResizable(false);
		start.setVisible(true);
		start.setLocationRelativeTo(null);
		
		start.add(title(), BorderLayout.NORTH);
		start.add(instructionAndLeaderBoard(), BorderLayout.CENTER);
		start.add(enterAndStart(), BorderLayout.SOUTH);
		
		start.pack();
		
		return start;
	}
	
	private JPanel title() //title panel
	{
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JLabel label = new JLabel("Welcome to the GUI Memory game!");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
		return panel;
	}
	
	private JPanel instructionAndLeaderBoard() //instructions and leaderboard
	{
		JPanel text = new JPanel();
		
		JLabel instruct = new JLabel("<html>In this game, you will flip cards and try to get all the matching pairs. "
				+ "<br>" +  "If you flip two cards with the same symbols, you stay on the same turn. Otherwise, you move onto the next turn. "
				+ "<br>" + "The goal is to make the board visible in as few turns as possible. "
				+ "<br>" + "Use the buttons or your keyboard to input the row and column numbers to flip pieces. The board will light up accordingly."
				+ "<br>" + "Click backspace to clear a guess or enter once you have selected a row and column to flip the piece at that location."
				+ "<br>" + "You can choose between easy (3x4), medium (4x7) and hard (7x8) boards. "
				+ "<br>" + "You can also choose the set of symbols that will appear on the cards: The Latin alphabet, Korean hangul, or Japanese hiragana.<br><br><html>");
		
		JLabel leaderboard = new JLabel(buildLeader());
		
		instruct.setFont(AppFonts.getTextFont());
		leaderboard.setFont(AppFonts.getTextFont());
		instruct.setVerticalAlignment(SwingConstants.CENTER);
		instruct.setHorizontalAlignment(SwingConstants.CENTER);
		
		text.add(instruct);
		text.add(leaderboard);
		
		text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
		
		return text;
	}
	
	private JPanel enterAndStart() //gets username from user
	{
		JPanel enterStart = new JPanel(new FlowLayout());
		JButton start =  new JButton("Start Game");
		JLabel enter = new JLabel("Enter Username: ");
		JTextField Juser = new JTextField(30);
		start.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { //Game cannot be made until player enters a username
				
			    if (!(Juser.getText().equals("")))
			    {
			    	username = Juser.getText();
			    	new MemoryFrame(new MemoryModel(3,3,4), newStart);
			    	frame.dispose();
			    }
			  } 
			} );
		
		enterStart.add(enter);
		enterStart.add(Juser);
		enterStart.add(start);
		
		return enterStart;
	}
	

}
