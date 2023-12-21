package edu.wm.cs.cs301.guimemorygame.model;

import java.util.Random;

import edu.wm.cs.cs301.guimemorygame.model.GamePiece;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryModel { 
	
	//Create grid (2d array) of game pieces; size depends on difficulty
	//Methods to implement: 
	//Private fields:
	
	
	private char[] characterSet;
	
	private final Random random;
	
	private int rows, columns;
	
	
	//private final int diff;
	
	private int turn;
	
	private final Alphabet alpha;
	
	private final GamePiece[][] board;
	
	public MemoryModel(int set, int r, int c) {
		this.alpha = setAlphabet(set);
		this.characterSet = alpha.toCharArray();
		this.random = new Random();
		this.rows = r;
		this.columns = c;
		this.board = initializeBoard();
		
	}
	
	
	private Alphabet setAlphabet(int set) //set character set using integers
	{
		if (set == 1)
		{
			Alphabet a = new Hiragana();
			return a;
		}
		else if (set == 2)
		{
			Alphabet a =  new Hangul();
			return a;
		}
		else
		{
			Alphabet a = new Latin();
			return a;
		}
	}
	
	private GamePiece[][] initializeBoard() //Create the board
	{
		GamePiece[][] piece = new GamePiece[rows][columns];
		ArrayList<Character> activeSymbols = generateCharacters();
		for (int i = 0; i < activeSymbols.size(); i++) //each iteration creates 2 gamepiece objects of a character in the arraylist, randomly puts them into the board
		{
			GamePiece g1 = new GamePiece(activeSymbols.get(i), Color.WHITE, Color.BLACK);
			GamePiece g2 = new GamePiece(activeSymbols.get(i), Color.WHITE, Color.BLACK);
			int randRows1 = random.nextInt(rows);
			int randCols1 = random.nextInt(columns);
			while (piece[randRows1][randCols1] != null)
			{
				randRows1 = random.nextInt(rows);
				randCols1 = random.nextInt(columns);
			}
			piece[randRows1][randCols1] = g1;
			int randRows2 = random.nextInt(rows);
			int randCols2 = random.nextInt(columns);
			while (piece[randRows2][randCols2] != null)
			{
				randRows2 = random.nextInt(rows);
				randCols2 = random.nextInt(columns);
			}
			piece[randRows2][randCols2] = g2;
		}
		
		return piece;
		
	}
	
	private ArrayList<Character> generateCharacters() //create the character set to be used
	{
		ArrayList<Character> activeSymbols = new ArrayList<Character>();
		for (int i = 0; i < (rows*columns)/2; i++)
		{
			int randIndex = random.nextInt(28);
			char symbol = (char)Array.getChar(characterSet, randIndex);
			while (activeSymbols.contains(symbol) == true) //checks to see if character is already in
			{
				randIndex = random.nextInt(28);
				symbol = (char)Array.getChar(characterSet, randIndex);
			}
			activeSymbols.add(symbol);
		}
		System.out.println(activeSymbols);
		return activeSymbols;
		
	}
	
	public boolean allFlipped() //check if all pieces are visible, determines when to end the game
	{
		int rows = this.board.length;
		int cols = this.board[0].length;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (this.board[i][j].isVisible() == false)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**public GamePiece flipAtPosition(int row, int col) //method for flipping a piece and returning the gamepiece object
	{

		this.board[row - 1][col - 1].flip(this.board[row - 1][col - 1].isVisible());
		return this.board[row - 1][col - 1];
	}**/
	

	
	/**public int getDiff()
	{
		return this.diff;
	}**/
	
	
	public GamePiece getGamePiece(int row, int col)
	{
		return board[row-1][col-1];
	}
	
	
	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	}
	
	public GamePiece[][] getBoard()
	{
		return board;
	}
	
	public void setTurn(int t)
	{
		turn = t;
	}
	
	public int getTurn()
	{
		return turn;
	}

	
		
	
}
