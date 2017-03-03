package chess;

public abstract class Piece {

	abstract boolean canMove(Location newLoc);
	
	abstract void moveTo(Location newLoc);
}
