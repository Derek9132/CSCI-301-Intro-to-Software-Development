package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsFrame extends JFrame{

	private final JFrame instructFrame;
	
	public InstructionsFrame() {
		this.instructFrame = initializeInstruct();
		// TODO Auto-generated constructor stub
		
	}
	
	private JFrame initializeInstruct()
	{
		JFrame instruct = new JFrame();
		
		instruct.setTitle("Welcome Page");
		instruct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instruct.setSize(900,400);
		instruct.setResizable(false);
		instruct.setVisible(true);
		instruct.setLocationRelativeTo(null);
		
		instruct.add(titlePanel(), BorderLayout.NORTH);
		instruct.add(buildPanel(), BorderLayout.CENTER);
		instruct.add(backToGame(), BorderLayout.SOUTH);
		
		return instruct;
	}
	
	private JPanel titlePanel()
	{
		JPanel title = new JPanel();
		
		JLabel label = new JLabel("Memory Game Instructions");
		label.setFont(AppFonts.getTitleFont());
		title.add(label);		
		
		return title;
	}
	
	private JPanel buildPanel()
	{
		JPanel text = new JPanel();
		
		JLabel text1 = new JLabel("<html>Use the buttons or your keyboard to input a row and column number on the grid.<br><html>");
		text1.setFont(AppFonts.getTextFont());
		
		JLabel text2 = new JLabel("<html>The board will highlight your selected row in blue and selected column in yellow.<br>"
				+ "The intersection of your selections will be green and that is the piece that will be flipped. Click enter to flip the piece.<br><html>");
		text2.setFont(AppFonts.getTextFont());
		
		JLabel text3 = new JLabel("<html>You can always click backspace to clear your guess and start a new one. This will delete both your row and column input.<br><html>");
		text3.setFont(AppFonts.getTextFont());
		
		JLabel text4 = new JLabel("<html>If you flip 2 pieces and their symbols match, you stay on the same turn and keep going.<br>"
				+ "Otherwise, you move onto the next turn and the turn counter increases accordingly.<br><html>");
		text4.setFont(AppFonts.getTextFont());
		
		JLabel text5 = new JLabel("<html>The goal of the game is to flip over all the cards on the board in as few turns as possible. Good luck, and focus!<br><html>");
		text5.setFont(AppFonts.getTextFont());
		
		text.add(text1);
		text.add(text2);
		text.add(text3);
		text.add(text4);
		text.add(text5);
		
		return text;
	}
	
	private JButton backToGame()
	{
		JButton back = new JButton("Return to game");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructFrame.dispose();
			}
		});
		return back;
		

	}

}
