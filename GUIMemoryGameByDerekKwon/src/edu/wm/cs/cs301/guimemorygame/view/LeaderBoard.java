package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wm.cs.cs301.guimemorygame.model.MemoryModel;
import edu.wm.cs.cs301.guimemorygame.model.Statistics;
import edu.wm.cs.cs301.guimemorygame.view.MemoryFrame;

public class LeaderBoard {
	
	private final JFrame endStats;
	
	private final MemoryFrame frame;
	
	
	private final Statistics stats;
	//Receives text from Statistics
	//Statistics should update before being passed into Leaderboard constructor
	//Puts text into JFrame
	//Adds play again and exit button
	public LeaderBoard(Statistics s, MemoryFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.stats = s;
		this.endStats = initializeLeaderboard();
	}
	
	private JFrame initializeLeaderboard() //creates the leaderboard frame
	{
		JFrame leader = new JFrame();
		
		leader.setTitle("Leaderboard");
		leader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		leader.setSize(800,700);
		leader.setResizable(false);
		leader.setVisible(true);
		leader.setLocationRelativeTo(null);
		
		leader.add(titlePanel(), BorderLayout.NORTH);
		leader.add(body(), BorderLayout.CENTER);
		leader.add(exitOrRestart(), BorderLayout.SOUTH);
		System.out.println("Leaderboard made");
		
		leader.pack();
		
		return leader;

	}
	
	private JPanel titlePanel() //title
	{
		JPanel panel = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Congratulations! You completed the board in " + stats.getTurn() + " turns.");
		label.setFont(AppFonts.getTextFont());
		panel.add(label);
		
		return panel;
	}
	
	private JPanel body() //gets text from its statistics object
	{
		String body = stats.getString();
		JPanel text = new JPanel();
		JLabel scores = new JLabel(body);
		text.add(scores);
		return text;
	}
	
	private JPanel exitOrRestart() //exit/play again buttons
	{
		JPanel buttons = new JPanel(new FlowLayout());
		JButton playAgain =  new JButton("Play Again");
		JButton end = new JButton("Exit");
		playAgain.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  frame.getFrame().dispose();
				  endStats.dispose();
				  new MemoryFrame(new MemoryModel(3,frame.getModel().getRows(),frame.getModel().getColumns()), frame.getStart());
			  }
		});
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endStats.dispose();
				frame.shutdown();
			}
		});
		buttons.add(end);
		buttons.add(playAgain);
		
		return buttons;

	}
	
	

}
