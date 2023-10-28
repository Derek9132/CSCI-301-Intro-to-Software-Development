package edu.wm.cs.cs301.memorygame;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MemoryGame {
	private GameBoard board;
	int[] scores = new int[3]; //This array will hold the top scores for each difficulty: scores[0] is easy, scores[1] is medium, scores[2] is hard
	String[] leaderData = new String[3]; //This string array holds not only the scores but also the usernames and what difficulty the top players played on
	boolean fileExists = false; //boolean to see if file already exists
	public MemoryGame() throws IOException {
		System.out.println("Welcome to my memory game!");
		System.out.println("Rules: First, select a set of symbols that you would like in your memory game. You can choose between the latin alphabet"
				+ "\n" + ", Japanese hiragana, or Korean Hangul. Next, choose a difficulty. Choose easy mode for a 3 x 4 board, medium for a 4 x 7 board, "
				+ "\n" + "and hard for a 7 x 8 board. All cards will begin face down, and the player will flip 2 of them at a time. If the cards match, "
				+ "\n" + "the turn continues. If they do not, the game moves to the next turn and the two selected cards will be flipped back. "
				+ "\n" + "Remember where the cards are, and try to aim for the lowest possible number of turns.");
		try
		{
			BufferedReader scoreReadStart = new BufferedReader(new FileReader("resources/leaderboard.txt"));
			System.out.println("     LEADERBOARD     ");
			System.out.println("======================");
			String s;
			int index = 0;
			while ((s = scoreReadStart.readLine()) != null) //Prints leaderboard, puts scores into scores[] array for future comparison
			{
				System.out.println(s);
				leaderData[index] = s;
				String[] rows = s.split("\t");
				try
				{
					int score = Integer.parseInt(rows[1]);
					scores[index] = score;
				}
				catch (Exception e)
				{
					scores[index] = 0;
				}
			    index++;
			}
			scoreReadStart.close();
			/**for (int x = 0; x < 3; x++)
			{
				System.out.println(scores[x]);
			}**/
			fileExists = true;
		}
		catch (Exception e)
		{
			System.out.println("The game has not been played yet, so there is no leaderboard."); //Playing the game for the first time will not show a leaderboard at the start
		}
		Scanner username = new Scanner(System.in);
		System.out.println("Please enter a username: "); //username needed for leaderboard purposes
		String user = username.nextLine();
		Scanner tileSelect = new Scanner(System.in);
		System.out.println("Please select a symbol set. Choose between Latin, Hangul, and Hiragana: ");
		ArrayList<String> symbol_list = new ArrayList<String>(); //Invalid inputs handled through contents of an arraylist
		symbol_list.add("hiragana");
		symbol_list.add("latin");
		symbol_list.add("hangul");
		String symbol = tileSelect.nextLine().toLowerCase().strip();
		//symbol = symbol.strip();
		//symbol = symbol.toLowerCase();
		while (symbol_list.contains(symbol) == false) //checking for invalid inputs
		{
			System.out.println("Invalid Input. Please try again.");
			symbol = tileSelect.nextLine();
		}
		Scanner diffSelect = new Scanner(System.in);
		System.out.println("Please select a difficulty. Choose between easy, medium, or hard: ");
		ArrayList<String> difficultyList = new ArrayList<String>(); //Invalid inputs handled through contents of an arraylist
		difficultyList.add("easy");
		difficultyList.add("medium");
		difficultyList.add("hard");
		String difficulty = diffSelect.nextLine().toLowerCase().strip();
		while (difficultyList.contains(difficulty) == false) //checking for invalid inputs
		{
			System.out.println("Invalid Input. Please try again.");
			difficulty = diffSelect.nextLine();
		}
		
		if (difficulty.equals("easy")) //Creates easy board
		{
			if (symbol.equals("latin"))
			{
				Alphabet a = new Latin();
				this.board = new GameBoard(3,4,a);
			}
			else if (symbol.equals("hangul"))
			{
				Alphabet a = new Hangul();
				this.board = new GameBoard(3,4,a);
			}
			else
			{
				Alphabet a = new Hiragana();
				this.board = new GameBoard(3,4,a);
			}
		
		}
		else if (difficulty.equals("medium")) //Creates medium board
		{
			if (symbol.equals("latin"))
			{
				Alphabet a = new Latin();
				this.board = new GameBoard(4,7,a);
			}
			else if (symbol.equals("hangul"))
			{
				Alphabet a = new Hangul();
				this.board = new GameBoard(4,7,a);
			}
			else
			{
				Alphabet a = new Hiragana();
				this.board = new GameBoard(4,7,a);
			}
		}
		else
		{
			if (symbol.equals("latin")) //Creates hard board
			{
				Alphabet a = new Latin();
				this.board = new GameBoard(7,8,a);
			}
			else if (symbol.equals("hangul"))
			{
				Alphabet a = new Hangul();
				this.board = new GameBoard(7,8,a);
			}
			else
			{
				Alphabet a = new Hiragana();
				this.board = new GameBoard(7,8,a);
			}
		}
		int turn = 1;
		boolean quit = false;
		while (this.board.allVisible() == false) //Each iteration of the for loop is 2 flips, runs until all pieces are visible
		{
			char flip1 = 'a'; //flip variables are used for comparison at the end of the while loop iteration
			char flip2 = 'b';
			int rowNum1 = 1; //row and col variables used to flip pieces back over at the end of the while loop iteration if pieces are not equal
			int rowNum2 = 1; 
			int colNum1 = 1;
			int colNum2 = 1; 
			System.out.println("Turn: " + turn);
			this.board.display();
			System.out.println("Choose a tile [Row  Column] or type 'quit' to exit: ");
			Scanner firstInput = new Scanner(System.in); //Scanner for first input
			String firstSelect = firstInput.nextLine().toLowerCase().strip();
			if (firstSelect.equals("quit")) //This if-else block handles the first input, either quits the game or flips a gamepiece and gets the character
			{
				quit = true;
				break;
			}
			else
			{
				boolean invalid = true;
				while (invalid == true)
				{
	                try //try-catch handles invalid inputs
	                {
	                    String[] coords = firstSelect.split(" ",0); //user input split into array
	                    rowNum1 = Integer.parseInt(coords[0]); //user input converted to string
	                    colNum1 = Integer.parseInt(coords[1]);
	                    if (this.board.isFlipped(rowNum1 - 1, colNum1 - 1))
	                    {
	                    	throw new Exception("That tile is already flipped."); //exception thrown if card is already visible
	                    }
	                    flip1 = this.board.flip(rowNum1 - 1, colNum1 - 1).getSymbol(); //flips a piece, gets the character
	                    invalid = false; //condition to exit loop
	                }
	                catch(Exception e)
	                {
	                    System.out.println("The selected tile has already been flipped, the input is out of bounds, or the input format is incorrect. Please try again.");
	                    firstSelect = firstInput.nextLine().toLowerCase().strip();
	                }
				}
			}
			System.out.println("Turn: " + turn);
			this.board.display();
			System.out.println("Choose a tile [Row  Column] or type 'quit' to exit: ");
			Scanner secondInput = new Scanner(System.in); //Scanner for second input
			String secondSelect = firstInput.nextLine().toLowerCase().strip();
			if (secondSelect.equals("quit")) //This if-else block handles the second input, either quits the game or flips a gamepiece and gets the character
			{
				quit = true;
				break;
			}
			else
			{
				boolean invalid = true;
				while (invalid == true)
				{
	                try
	                {
	                    String[] coords = secondSelect.split(" ",0);
	                    rowNum2 = Integer.parseInt(coords[0]);
	                    colNum2 = Integer.parseInt(coords[1]);
	                    if (this.board.isFlipped(rowNum2 - 1, colNum2 - 1))
	                    {
	                    	throw new Exception("That tile is already flipped.");
	                    }
	                    flip2 = this.board.flip(rowNum2 - 1, colNum2 - 1).getSymbol();
	                    invalid = false;
	                }
	                catch(Exception e)
	                {
	                	System.out.println("The selected tile has already been flipped, the input is out of bounds, or the input format is incorrect. Please try again.");
	                    secondSelect = secondInput.nextLine().toLowerCase().strip();
	                }
				}
			System.out.println("Turn: " + turn);
			this.board.display();
			}
			if (flip1 == flip2) //compares the two characters
			{
				System.out.println("You got a match!"); //turn continues, no incrementation
			}
			else
			{
				System.out.println("No match. Moving on to the next turn."); //next turn, turn incremented
				turn++;
				this.board.flip(rowNum1 - 1, colNum1 - 1);
				this.board.flip(rowNum2 - 1, colNum2 - 1);
				System.out.println("Remember the locations of " + flip1 + " and " + flip2 + "."); //cards flipped back over
				
			}


		}
		if (quit) //handles quit, does not show leaderboard
		{
			System.out.println("You have quit the game. Thank you for playing!");
		}
		else //code to handle updating leaderboard
		{
			BufferedWriter scoreWrite = new BufferedWriter(new FileWriter("resources/leaderboard.txt"));
			System.out.println("Congratulations! You completed the board in " + turn + " turns.");
			if (fileExists == false) //handles no file, creates new
			{
				if (difficulty.equals("easy"))
				{
					scoreWrite.write(user + "\t" + turn + "\t" + "easy");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "medium");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "hard");
				}
				else if (difficulty.equals("medium"))
				{
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "easy");
					scoreWrite.write("\n");
					scoreWrite.write(user + "\t" + turn + "\t" + "medium");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "hard");
				}
				else
				{
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "easy");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "medium");
					scoreWrite.write("\n");
					scoreWrite.write(user + "\t" + turn + "\t" + "hard");
				}
				scoreWrite.close();
			}
			else //if file exists already
			{
				if (difficulty.equals("easy"))
				{
					if (turn <= scores[0] || scores[0] == 0) //if user's score is greater than current champion or if score = 0 (no user for that difficulty)
					{
						System.out.println("You have made the leaderboard!");
						scoreWrite.write(user + "\t" + turn + "\t" + "easy");
						scoreWrite.write("\n");
						scoreWrite.write(leaderData[1]);
						scoreWrite.write("\n");
						scoreWrite.write(leaderData[2]);
					}
					else
					{
						for (int i = 0; i < 2; i++)
						{
							scoreWrite.write(leaderData[i]);
							scoreWrite.write("\n");
						}
						scoreWrite.write(leaderData[2]);
					}
				}
				else if (difficulty.equals("medium"))
				{
					if (turn <= scores[1] || scores[1] == 0)
					{
						System.out.println("You have made the leaderboard!");
						scoreWrite.write(leaderData[0]);
						scoreWrite.write("\n");
						scoreWrite.write(user + "\t" + turn + "\t" + "medium");
						scoreWrite.write("\n");
						scoreWrite.write(leaderData[2]);
					}
					else
					{
						for (int i = 0; i < 2; i++)
						{
							scoreWrite.write(leaderData[i]);
							scoreWrite.write("\n");
						}
						scoreWrite.write(leaderData[2]);
					}
				}
				else
				{
					if (turn <= scores[2] || scores[2] == 0)
					{
						System.out.println("You have made the leaderboard!");
						scoreWrite.write(leaderData[0]);
						scoreWrite.write("\n");
						scoreWrite.write(leaderData[1]);
						scoreWrite.write("\n");
						scoreWrite.write(user + "\t" + turn + "\t" + "hard");
					}
					else
					{
						for (int i = 0; i < 2; i++)
						{
							scoreWrite.write(leaderData[i]);
							scoreWrite.write("\n");
						}
						scoreWrite.write(leaderData[2]);
					}
				}
			}
			scoreWrite.close();
		}
		System.out.println("Check the Leaderboard: "); //print leaderboard again
		BufferedReader scoreReadEnd = new BufferedReader(new FileReader("resources/leaderboard.txt"));
		System.out.println("     LEADERBOARD     ");
		System.out.println("======================");
		String a;
		while ((a = scoreReadEnd.readLine()) != null)
		{
			System.out.println(a);
		}
		scoreReadEnd.close();
		System.out.println("Thank you for playing!");
	}
}
