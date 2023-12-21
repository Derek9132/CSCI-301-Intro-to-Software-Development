package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import edu.wm.cs.cs301.guimemorygame.model.MemoryModel;
import edu.wm.cs.cs301.guimemorygame.controller.KeyBoardButtonAction;
import edu.wm.cs.cs301.guimemorygame.model.GamePiece;

public class MemoryBoard extends JPanel {
	private static final long serialVersionUID = 1L;

	private final int topMargin, leftMargin, letterWidth;

	private final Insets insets;

	private final Rectangle[][] grid;

	private final MemoryModel model;
	
	private final KeyBoardButtonAction action;
	
	private ArrayList<Integer> guess;
	
	//private final KeyBoardButtonAction action;
	
	public MemoryBoard(MemoryFrame view, MemoryModel model, KeyboardPanel key)
	{
		this.model = model;
		this.action = new KeyBoardButtonAction(view, model, key);
		this.topMargin = 0;
		this.letterWidth = 64;
		this.insets = new Insets(0, 6, 6, 6); //Distance between squares

		int wordWidth = (letterWidth + insets.right) * model.getColumns();
		this.leftMargin = (this.getPreferredSize().width - wordWidth) / 2;
		int height = (letterWidth + insets.bottom) * model.getRows()
				+ 2 * topMargin;
		this.grid = buildGrid();
	}
	
	private Rectangle[][] buildGrid() { //Part 3 here, change size of grid
		Rectangle[][] grid = new Rectangle[model.getRows()][model.getColumns()];
		//Rectangle[][] grid = new Rectangle[7][6];

		int x = leftMargin;
		int y = topMargin;

		for (int row = 0; row < model.getRows(); row++) {
			for (int column = 0; column < model.getColumns(); column++) {
				grid[row][column] = new Rectangle(x, y, letterWidth,
						letterWidth);
				x += letterWidth + insets.right;
			}
			x = leftMargin;
			y += letterWidth + insets.bottom;
		}

		return grid;
	}
	
	public void setList(ArrayList<Integer> g)
	{
		guess = g;
	}
	
	
	protected void paintComponent(Graphics g) { //Part 3 here, change size of grid
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		Font titleFont = AppFonts.getTitleFont();
		GamePiece[][] pieces = model.getBoard(); 
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				Rectangle r = grid[row][column];
				GamePiece gp = pieces[row][column];
				drawOutline(g2d, r);
				drawGamePiece(g2d, gp, r, titleFont, Color.BLACK, Color.WHITE);
			}
		}
		if (guess != null)  //highlight the grid depending on user's inputs
		{
			if (guess.size() == 1) //size of 1 corresponds to row input
			{
				for (int c = 0; c < grid[0].length; c++)
				{
					drawGamePiece(g2d, pieces[guess.get(0) - 1][c],grid[guess.get(0) - 1][c], titleFont, Color.BLACK, Color.CYAN);
				}
			}
			else if (guess.size() == 2) //size of 2 corresponds to row and column input
			{
				for (int c = 0; c < grid[0].length; c++)
				{
					drawGamePiece(g2d, pieces[guess.get(0) - 1][c],grid[guess.get(0) - 1][c], titleFont, Color.BLACK, Color.CYAN);
				}
				for (int r = 0; r < grid.length; r++)
				{
					drawGamePiece(g2d, pieces[r][guess.get(1) - 1],grid[r][guess.get(1) - 1], titleFont, Color.BLACK, Color.YELLOW);
				}
				drawGamePiece(g2d, pieces[guess.get(0) - 1][guess.get(1) - 1],grid[guess.get(0) - 1][guess.get(1) - 1], titleFont, Color.BLACK, Color.GREEN);
				//Yellow + Blue = Green
			}
		}
	}
	
	
	private void drawOutline(Graphics2D g2d, Rectangle r) { //taken from Wordle project
		int x = r.x + 1;
		int y = r.y + 1;
		int width = r.width - 2;
		int height = r.height - 2;
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3f));
		g2d.drawLine(x, y, x + width, y);
		g2d.drawLine(x, y + height, x + width, y + height);
		g2d.drawLine(x, y, x, y + height);
		g2d.drawLine(x + width, y, x + width, y + height);
	}
	
	
	private void drawGamePiece(Graphics2D g2d, 
			GamePiece g, Rectangle r, Font titleFont, Color foreGround, Color backGround) { //taken from Wordle project
		if (g != null) {
			g2d.setColor(backGround);
			g2d.fillRect(r.x, r.y, r.width, r.height);
			g2d.setColor(foreGround);
			drawCenteredString(g2d,
					Character.toString(g.getSymbol()), r, titleFont);
		}
	}
	
	@Override
	public Dimension getPreferredSize() //Could not figure out how to set size dynamically
	{
		if (model.getColumns() * model.getRows() == 12)
		{
			return new Dimension(400, 230);
		}
		else if (model.getColumns() * model.getRows() == 28)
		{
			return new Dimension(600, 350);
		}
		else
		{
			return new Dimension(700, 510);
		}
	}
	
	private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect,
			Font font) { //taken from Wordle project
		FontMetrics metrics = g2d.getFontMetrics(font);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
				+ metrics.getAscent();

		g2d.setFont(font);
		g2d.drawString(text, x, y);
	}

}
