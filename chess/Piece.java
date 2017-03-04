package chess;

public abstract class Piece {
	protected String color;
	abstract boolean canMove(Location newLoc, Board b);
	
	abstract boolean moveTo(Location newLoc, Board b);
	
	abstract String getColor();
}
