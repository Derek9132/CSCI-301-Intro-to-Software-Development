package edu.wm.cs.cs301.guimemorygame.model;

import java.awt.Color;

import edu.wm.cs.cs301.guimemorygame.model.ColorResponse;

public class GamePiece {
	private final Character symbol;
	private boolean visible;
	private final ColorResponse colorResponse;
	
	public GamePiece(char s, Color backgroundColor, Color foregroundColor)
	{
		this.symbol = s;
		this.visible = false;
		this.colorResponse = new ColorResponse(backgroundColor, foregroundColor);
	}
	
	public boolean isVisible() //returns if piece is visible
	{
		return visible;
		
	}
	
	public void flip(boolean v) { //flips game piece, switches boolean visible value
		if (v == false)
		{
			visible = true;
		}
		else
		{
			visible = false;
		}
	}
	
	public char getSymbol() //returns symbol of piece
	{
		if (visible == true)
		{
			return this.symbol;
		}
		else
		{
			return '?';
		}
	}
	
	public Color getBackgroundColor() {
		return colorResponse.getBackgroundColor();
	}

	public Color getForegroundColor() {
		return colorResponse.getForegroundColor();
	}
}
