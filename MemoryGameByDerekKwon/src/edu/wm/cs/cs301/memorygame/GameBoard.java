package edu.wm.cs.cs301.memorygame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
	private final GamePiece[][] board;
	
	public GameBoard(int rows, int cols, Alphabet a) {
		this.board = new GamePiece[rows][cols];  //create a Gamepiece 2D array
		char[] symbols = a.toCharArray(); //get characters from alphabet interface
		int symbolCount = (rows*cols)/2; //2 pieces for each character, # of unique characters = (area of board)/2
		//System.out.println(symbol_count);
		ArrayList<Character> activeSymbols = new ArrayList<Character>(); //contains symbols to be used
		Random rand = new Random();
		for (int i = 0; i < symbolCount; i++) //randomly adds characters from symbols array to activeSymbols arraylist
		{
			int randIndex = rand.nextInt(28);
			char symbol = (char)Array.getChar(symbols, randIndex);
			while (activeSymbols.contains(symbol) == true) //checks to see if character is already in
			{
				randIndex = rand.nextInt(28);
				symbol = (char)Array.getChar(symbols, randIndex);
			}
			activeSymbols.add(symbol);
		}
		//System.out.println(active_symbols);
		Random index = new Random();
		for (int i = 0; i < activeSymbols.size(); i++) //each iteration creates 2 gamepiece objects of a character in the arraylist, randomly puts them into the board
		{
			GamePiece g1 = new CharacterGamePiece(activeSymbols.get(i));
			GamePiece g2 = new CharacterGamePiece(activeSymbols.get(i));
			int randRows1 = index.nextInt(rows);
			int randCols1 = index.nextInt(cols);
			while (this.board[randRows1][randCols1] != null)
			{
				randRows1 = index.nextInt(rows);
				randCols1 = index.nextInt(cols);
			}
			this.board[randRows1][randCols1] = g1;
			int randRows2 = index.nextInt(rows);
			int randCols2 = index.nextInt(cols);
			while (this.board[randRows2][randCols2] != null)
			{
				randRows2 = index.nextInt(rows);
				randCols2 = index.nextInt(cols);
			}
			this.board[randRows2][randCols2] = g2;
		}

	}
	void display() //displays the board
	{
		int rows = this.board.length;
		int cols = this.board[0].length;
		//System.out.println("The board is " + rows + " rows and " + cols + " columns.");
        String to_print = "     ";
        for (int i = 1; i <= cols; i++)
        {
          to_print = to_print + i + "   ";
        }
        to_print = to_print + "\r\n";
        to_print = to_print + "   ";
        for (int i = 0; i < cols; i++)
        {
          to_print = to_print + "====";
        }
        to_print = to_print + "=\r\n";
        for (int r = 1; r < rows; r++)
        {
	        to_print = to_print + r + " |";
	          for (int c = 0; c < cols; c++)
	          {
	            to_print = to_print + "| " + this.board[r-1][c].getSymbol() + " ";
	          }
	        to_print = to_print + "||\r\n";
	        to_print = to_print + "   ";
	        for (int i = 0; i < cols; i++)
	        {
	        	to_print = to_print + "----";
	        }
	        to_print = to_print + "-\r\n";
        }
        to_print = to_print + rows + " |";
        for (int c = 0; c < cols; c++)
        {
        	to_print = to_print + "| " + this.board[rows - 1][c].getSymbol() + " ";
        }
        to_print = to_print + "||\r\n";
        to_print = to_print + "   ";
        for (int i = 0; i < cols; i++)
        {
          to_print = to_print + "====";
        }
        to_print = to_print + "=\r\n";
        System.out.println(to_print);
	}
	
	boolean allVisible() //true if all pieces are visible, false otherwise
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
	
	
	GamePiece flip(int row, int col) //method for flipping a piece and returning the gamepiece object
	{
		this.board[row][col].setVisible(this.board[row][col].isVisible());
		return this.board[row][col];
		
	}
	
	boolean isFlipped(int row, int col) //returns if the character of a piece is visible or not
	{
		return this.board[row][col].isVisible();
	}
	
}
