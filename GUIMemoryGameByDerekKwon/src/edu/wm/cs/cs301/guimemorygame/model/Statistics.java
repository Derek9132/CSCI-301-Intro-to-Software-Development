package edu.wm.cs.cs301.guimemorygame.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.wm.cs.cs301.guimemorygame.view.StartingFrame;

public class Statistics {
	
	private String user;
	
	private String path;
	
	private final MemoryModel model;
	
	private String[] leaderArray;
	
	private int[] scores;
	
	private int turn;
	
	private boolean fileExists;

	
	//Statistics is made in the MemoryModel class
	//Get turn # and difficulty from memoryboard, get username from startingframe/memoryframe
	//public Statistics()	{
	public Statistics(String user, MemoryModel model) {
		this.user = user;
		this.fileExists = false;
		this.model = model;
		this.turn = model.getTurn();
		this.leaderArray = new String[3];
		this.scores = new int[3];
		this.path = "resources/leaderboard.txt";
		//this.path = "C:\\Users\\derek\\Desktop\\CSCI 301\\GUIMemoryGameByDerekKwon\\resources\\leaderboard.txt";
		try {
			readStatistics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("file made");
	}
		
		private void readStatistics() throws IOException //Reads from file, stores leader usernames and scores into arrays for future reference
		{
			try {
				BufferedReader scoreRead = new BufferedReader(new FileReader(path));
				String s;
				int index = 0;
				while ((s = scoreRead.readLine()) != null) //Prints leaderboard, puts scores into scores[] array for future comparison
				{
					System.out.println(s);
					leaderArray[index] = s;
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
				scoreRead.close();
				fileExists = true;
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found");
			}
			
		}
		
		
		public void updateLeaderboard() throws IOException 
		{
			//String[] leaderData = new String[3];
			BufferedWriter scoreWrite = new BufferedWriter(new FileWriter(path));
			if (fileExists == false)
			{
				if (model.getRows() * model.getColumns() == 12)
				{
					scoreWrite.write(user + "\t" + turn + "\t" + "easy");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "medium");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "hard");
					leaderArray[0] = user + "\t" + turn + "\t" + "easy";
					leaderArray[1] = "N\\A" + "\t" + "N\\A" + "\t" + "medium";
					leaderArray[2] = "N\\A" + "\t" + "N\\A" + "\t" + "hard";
				}
				else if (model.getRows() * model.getColumns() == 28)
				{
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "easy");
					scoreWrite.write("\n");
					scoreWrite.write(user + "\t" + turn + "\t" + "medium");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "hard");
					leaderArray[1] = user + "\t" + turn + "\t" + "medium";
					leaderArray[0] = "N\\A" + "\t" + "N\\A" + "\t" + "easy";
					leaderArray[2] = "N\\A" + "\t" + "N\\A" + "\t" + "hard";
				}
				else
				{
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "easy");
					scoreWrite.write("\n");
					scoreWrite.write("N\\A" + "\t" + "N\\A" + "\t" + "medium");
					scoreWrite.write("\n");
					scoreWrite.write(user + "\t" + turn + "\t" + "hard");
					leaderArray[2] = user + "\t" + turn + "\t" + "hard";
					leaderArray[0] = "N\\A" + "\t" + "N\\A" + "\t" + "easy";
					leaderArray[1] = "N\\A" + "\t" + "N\\A" + "\t" + "medium";
				}
				scoreWrite.close();
			}
			else //if file exists already
			{
				if (model.getRows() * model.getColumns() == 12)
				{
					if (turn <= scores[0] || scores[0] == 0) //if user's score is greater than current champion or if score = 0 (no user for that difficulty)
					{
						scoreWrite.write(user + "\t" + turn + "\t" + "easy");
						scoreWrite.write("\n");
						scoreWrite.write(leaderArray[1]);
						scoreWrite.write("\n");
						scoreWrite.write(leaderArray[2]);
						leaderArray[0] = user + "\t" + turn + "\t" + "easy";
					}
					else
					{
						for (int i = 0; i < 2; i++)
						{
							scoreWrite.write(leaderArray[i]);
							scoreWrite.write("\n");
						}
						scoreWrite.write(leaderArray[2]);
					}
				}
				else if (model.getRows() * model.getColumns() == 28)
				{
					if (turn <= scores[1] || scores[1] == 0)
					{
						scoreWrite.write(leaderArray[0]);
						scoreWrite.write("\n");
						scoreWrite.write(user + "\t" + turn + "\t" + "medium");
						scoreWrite.write("\n");
						scoreWrite.write(leaderArray[2]);
						leaderArray[1] = user + "\t" + turn + "\t" + "medium";
					}
					else
					{
					for (int i = 0; i < 2; i++)
						{
							scoreWrite.write(leaderArray[i]);
							scoreWrite.write("\n");
						}
						scoreWrite.write(leaderArray[2]);
					}
				}
				else
				{
					if (turn <= scores[2] || scores[2] == 0)
					{
						scoreWrite.write(leaderArray[0]);
						scoreWrite.write("\n");
						scoreWrite.write(leaderArray[1]);
						scoreWrite.write("\n");
						scoreWrite.write(user + "\t" + turn + "\t" + "hard");
						leaderArray[2] = user + "\t" + turn + "\t" + "hard";
					}
					else
					{
						for (int i = 0; i < 2; i++)
						{
							scoreWrite.write(leaderArray[i]);
							scoreWrite.write("\n");
						}
						scoreWrite.write(leaderArray[2]);
					}
				}
			}
			scoreWrite.close();
			for (int i = 0; i < 3; i++)
			{
				System.out.println(leaderArray[i]);
			}
		}
		
		
		public String getString()
		{
			String result = "<html>     LEADERBOARD     " + "<br>" + "======================" + "<br>" + "user" + "\t" + "score" + "\t" + "difficulty" + "<br>";
			for (int i = 0; i < 3; i++)
			{
				result = result + leaderArray[i] + "<br>";
			}
			return result;
		}
		
		public int getTurn()
		{
			return turn;
		}
		
}
		
	
