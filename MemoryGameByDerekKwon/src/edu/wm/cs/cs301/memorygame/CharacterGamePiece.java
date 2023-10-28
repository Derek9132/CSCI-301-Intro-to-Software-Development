package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible = false;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
	}

	public Character getSymbol() {
		if (visible == true)
		{
			return this.symbol;
		}
		else 
		{
			return '?';
		}
	}
	
	public void setVisible(boolean v) {
	if (v == false)
	{
		visible = true;
	}
	else
	{
		visible = false;
	}
}
	
	
	public boolean isVisible() {
		return visible;

	}
	
	public boolean equals(GamePiece other) { //This method is never used, despite being part of the interface
		if (this.symbol == other.getSymbol())
		{
			return true;
		}
		else
		{
			return false;
		}		

	}
	
}
