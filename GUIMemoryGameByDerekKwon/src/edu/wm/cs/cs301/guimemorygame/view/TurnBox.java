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
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import edu.wm.cs.cs301.guimemorygame.model.GamePiece;
import edu.wm.cs.cs301.guimemorygame.model.MemoryModel;

public class TurnBox extends JPanel{
	
	private final MemoryModel model;
	
	private final Rectangle turn;
	
	private final int topMargin, leftMargin, letterWidth;
	
	private final Insets insets;
	
	public TurnBox(MemoryFrame view, MemoryModel model)
	{
		this.model = model;
		this.letterWidth = 64;
		this.insets = new Insets(0, 6, 6, 6); 
		this.topMargin = 0;
		int wordWidth = (letterWidth + insets.right) * model.getColumns(); //# of columns in the game
		this.leftMargin = (this.getPreferredSize().width - wordWidth)/2;
		int height = (letterWidth + insets.bottom) * model.getRows() //# of rows in the game
				+ 2 * topMargin;
		this.turn = buildRectangle();
	}
	
	
	private Rectangle buildRectangle()
	{
		
		int x = 50;
		int y = topMargin;
		
		Rectangle turn = new Rectangle(x, y, letterWidth,
				letterWidth);
		
		return turn;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(150, 150);
	}
	
	protected void paintComponent(Graphics g) { //Part 3 here, change size of grid
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		Font titleFont = AppFonts.getTitleFont();
		Rectangle r = buildRectangle();
		drawOutline(g2d, r);
		drawTurnBox(g2d, String.valueOf(model.getTurn()), r, titleFont);
	
	}
		
	
	
	private void drawTurnBox(Graphics2D g2d,
			String s, Rectangle r, Font titleFont) {
			g2d.setColor(Color.WHITE);
			g2d.fillRect(r.x, r.y, r.width, r.height);
			g2d.setColor(Color.BLACK);
			drawCenteredString(g2d,String.valueOf(model.getTurn()), r, titleFont);
		
	}
	
	private void drawOutline(Graphics2D g2d, Rectangle r) {
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
	
	
	
	private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect,
			Font font) {
		FontMetrics metrics = g2d.getFontMetrics(font);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
				+ metrics.getAscent();

		g2d.setFont(font);
		g2d.drawString(text, x, y);
	}
	
	
	
	
}
