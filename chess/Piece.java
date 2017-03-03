package chess;

public abstract class Piece {
	protected String color;
	abstract boolean canMove(Location newLoc);
	
	abstract void moveTo(Location newLoc);
}
