package chess;

public abstract class Piece {

	abstract boolean canMove(String newLoc);
	
	abstract void moveTo(String newLoc);
}
