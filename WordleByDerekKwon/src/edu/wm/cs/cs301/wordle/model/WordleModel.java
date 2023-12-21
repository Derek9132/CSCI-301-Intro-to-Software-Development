package edu.wm.cs.cs301.wordle.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.wm.cs.cs301.wordle.controller.ReadWordsRunnable;

public class WordleModel {
	
	private char[] currentWord, guess;
	
	private final int columnCount, maximumRows;
	private int currentColumn, currentRow;
	
	private List<String> wordList;// = new ArrayList<String>();
	
	private final Random random;
	
	private final Statistics statistics;
	
	boolean giveFeedback;
	
	private WordleResponse[][] wordleGrid;
	
	//public WordleModel() {
	public WordleModel(int cols, int rows) {
		this.currentColumn = -1;
		this.currentRow = 0;
		//this.columnCount = 5;
		//this.maximumRows = 6;
		this.columnCount = cols;
		this.maximumRows = rows;
		this.random = new Random();
		
		createWordList();
		//System.out.println("Wordlist made");
		
		this.wordleGrid = initializeWordleGrid();
		this.guess = new char[columnCount];
		//this.statistics = new Statistics();
		this.statistics = setStatistics();
		//this.statistics.clearData();
	}
	
	/**private void createWordList() {
		ReadWordsRunnable runnable = new ReadWordsRunnable(this);
		new Thread(runnable).start();
	}**/
	
	private void createWordList() {		
	     ReadWordsRunnable runnable = new ReadWordsRunnable(this);		
	     Thread wordListThread = new Thread(runnable);		
	     wordListThread.start();		
	     try {			
	          wordListThread.join();		
	     } catch (InterruptedException e) {			
	          e.printStackTrace();		
	     }	
	}
	
	private Statistics setStatistics() {
		if (this.columnCount * this.maximumRows == 20)
		{
			Statistics easy = new Statistics(1);
			return easy;
		}
		else if (this.columnCount * this.maximumRows == 30)
		{
			Statistics medium = new Statistics(2);
			return medium;
		}
		else
		{
			Statistics hard = new Statistics(3);
			return hard;
		}
	}
	
	public void initialize() {
		this.wordleGrid = initializeWordleGrid();
		this.currentColumn = -1;
		this.currentRow = 0;
		generateCurrentWord();
		this.guess = new char[columnCount];
	}

	public void generateCurrentWord() {
		String word = getCurrentWord();
		this.currentWord = word.toUpperCase().toCharArray();
		
		//uncomment line below to show secret word on the console for debugging
		System.out.println("Current word set to " + word);
		//return word;
	}

	private String getCurrentWord() {
		return wordList.get(getRandomIndex());
	}
	

	private int getRandomIndex() {
		int size = wordList.size();
		return random.nextInt(size);
	}
	
	private WordleResponse[][] initializeWordleGrid() {
		WordleResponse[][] wordleGrid = new WordleResponse[maximumRows][columnCount];

		for (int row = 0; row < wordleGrid.length; row++) {
			for (int column = 0; column < wordleGrid[row].length; column++) {
				wordleGrid[row][column] = null;
			}
		}

		return wordleGrid;
	}
	
	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}
	
	public void setGuess(String input)
	{
		guess = input.toCharArray();
	}
	
	public void setCurrentWord() {
		int index = getRandomIndex();
		currentWord = wordList.get(index).toCharArray();
	}
	
	public void setCurrentColumn(char c) {
		currentColumn++;
		currentColumn = Math.min(currentColumn, (columnCount - 1));
		guess[currentColumn] = c;
		wordleGrid[currentRow][currentColumn] = new WordleResponse(c,
				Color.WHITE, Color.BLACK);
	}
	
	public void backspace() {
		if (this.currentColumn > -1) { //only backspace if there's room
			wordleGrid[currentRow][currentColumn] = null;
			guess[currentColumn] = ' ';
			this.currentColumn--;
			this.currentColumn = Math.max(currentColumn, -1);
		}
	}
	
	public WordleResponse[] getCurrentRow() {
		return wordleGrid[getCurrentRowNumber()];
	}
	
	public int getCurrentRowNumber() {
		return currentRow - 1;
	}
	
	public boolean setCurrentRow() {	//p2 Here
		String word = new String(guess);
		if (!(wordList.contains(word.toLowerCase()))) //Checks if guess is a word
		{
			giveFeedback = false; //If not a word, simply give the function a return value and do nothing
			return true;
		}
		else //Only gives feedback if guess is a word
		{
			for (int column = 0; column < guess.length; column++) {
				Color backgroundColor = AppColors.GRAY;
				Color foregroundColor = Color.WHITE;
				if (guess[column] == currentWord[column]) {
					backgroundColor = AppColors.GREEN;
				} else if (contains(currentWord, guess, column)) {
					backgroundColor = AppColors.YELLOW;
				}
				
				wordleGrid[currentRow][column] = new WordleResponse(guess[column],
						backgroundColor, foregroundColor);
			}
			
			currentColumn = -1;
			currentRow++;
			guess = new char[columnCount];
			giveFeedback = true;
			return currentRow < maximumRows;
		}
		
	}
	
	private boolean contains(char[] currentWord, char[] guess, int column) {
		for (int index = 0; index < currentWord.length; index++) {
			if (index != column && guess[column] == currentWord[index]) {
				return true;
			}
		}
		
		return false;
	}

	public WordleResponse[][] getWordleGrid() {
		return wordleGrid;
	}
	
	public int getMaximumRows() {
		return maximumRows;
	}

	public int getColumnCount() {
		return columnCount;
	}
	
	public int getCurrentColumn() {
		return currentColumn;
	}

	public int getTotalWordCount() {
		return this.wordList.size();
	}

	public Statistics getStatistics() {
		return statistics;
	}
	
	
	public boolean getFeedback() //Used for unit test
	{
		return giveFeedback;
	}
	
	public String getCurrent() //Used for part 2
	{
		String word = new String(currentWord);
		return word;
	}

}
