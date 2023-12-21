package edu.wm.cs.cs301.guimemorygame.controller;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

import edu.wm.cs.cs301.guimemorygame.model.GamePiece;
import edu.wm.cs.cs301.guimemorygame.model.MemoryModel;
import edu.wm.cs.cs301.guimemorygame.model.Statistics;
import edu.wm.cs.cs301.guimemorygame.view.MemoryFrame;
import edu.wm.cs.cs301.guimemorygame.view.KeyboardPanel;
import edu.wm.cs.cs301.guimemorygame.view.LeaderBoard;

public class KeyBoardButtonAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final MemoryFrame view;
	
	private final MemoryModel model;
	
	private char guess1 = ' ';
	
	private char guess2 = ' ';
	
	private GamePiece g1;
	
	private GamePiece g2;
	
	private Timer delay = new Timer();
	
	private KeyboardPanel keyboard;
	
	//private int[] guess;
	
	private ArrayList<Integer> guess;
	
	
	public KeyBoardButtonAction(MemoryFrame view, MemoryModel model, KeyboardPanel key)
	{
		this.view = view;
		this.model = model;
		this.keyboard = key;
		this.guess = new ArrayList<Integer>();
	}
	
	/**public String getRowInput()
	{
		if (guess.size() > 0)
		{
			return String.valueOf(guess.get(0));
		}
		return " ";
	}
	
	public String getColInput()
	{
		if (guess.size() > 1)
		{
			return String.valueOf(guess.get(1));
		}
		return " ";
	}**/

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String text = button.getActionCommand();
		switch (text) {
		case "Enter":
			//add code
			System.out.println(guess);
			if (guess.size() == 2) //if guess is full
			{
				if (guess1 == ' ')
				{
					g1 = this.model.getGamePiece(guess.get(0), guess.get(1));
					if (g1.isVisible() == false) //if card is face down
					{
						g1.flip(g1.isVisible());
						guess1 = g1.getSymbol();
					}
					else //otherwise, clear guess
					{
						guess1 = ' ';
						guess.clear();
					}
				}
				else if (guess2 == ' ')
				{
					g2 = this.model.getGamePiece(guess.get(0), guess.get(1));
					if (g2.isVisible() == false)
					{
						g2.flip(g2.isVisible());
						guess2 = g2.getSymbol();
					}
					else
					{
						guess2 = ' ';
						guess.clear();
					}
				}
				view.repaintMemoryBoard();
				
				if (guess1 != ' ' && guess2 != ' ') //if both guesses have been made
				{
					if (guess1 == guess2) //if both guesses are equal
					{
						if (model.allFlipped() == true) //if game is done
						{
							Statistics end = new Statistics(view.user(), model); //turn # and username as input
							try {
								end.updateLeaderboard(); 
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							new LeaderBoard(end, view); //Leaderboard frame
							//view.shutdown();
							//show statistics dialog
						}
						else //if game is not done, show message and continue game
						{
							System.out.println("Correct guess!");
						}
						guess1 = ' ';
						guess2 = ' ';
					}
					else //if two guesses are not equal, guess is incorrect
					{
						//wait 2 seconds
						//Timer delay = new Timer();
						model.setTurn(model.getTurn() + 1);
						guess1 = ' ';
						guess2 = ' ';
						keyboard.lockButtons(); //To prevent player from flipping cards while 2 are already face-up
						Timer delay = new Timer();
						delay.schedule(new TimerTask() {
							public void run()
							{
								g1.flip(true);
								g2.flip(true);
								keyboard.unlockButtons(); //allows player to select again after cards are flipped
								view.repaintMemoryBoard();
							}
						}, 2000);
						view.repaintMemoryBoard();
					}
				}
				//view.repaintMemoryBoard();
			}
			else //if guess is not yet full
			{
				//Do nothing, user must either input another number or clear their guess
			}
		case "Backspace": //clear guess
			//add code
			guess.clear();
			view.repaintMemoryBoard();
		default:
			//add code
			//if (guess.size() == 0)
			//input will correspond to row
			//repaint grid
			//else if guess.size != 2
			//input will correspond to column
			//repaint grid
			int input = 50;
			try
			{
				input = Integer.valueOf(text);
			}
			catch (Exception e3)
			{
				System.out.println("Enter or Backspace");
			}
			if (guess.size() != 2)
			{
				if (guess.size() == 0)
				{
					if (input <= model.getRows())
					//if (Integer.parseInt(text) <= model.getRows())
					{
						guess.add(Integer.parseInt(text));
						view.getMemoryBoard().setList(guess);
						view.repaintMemoryBoard();
						//System.out.println(guess);
					}
				}
				else if (guess.size() == 1)
				{
					if (input <= model.getColumns())
					//if (Integer.parseInt(text) <= model.getColumns())
					{
						guess.add(Integer.parseInt(text));
						view.getMemoryBoard().setList(guess);
						view.repaintMemoryBoard();
						//System.out.println(guess);
					}
				}
			}
			
			
		}
		// TODO Auto-generated method stub
		
	}



}

