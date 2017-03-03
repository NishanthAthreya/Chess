package Chess;

public interface Piece {

	boolean canMove(String newLoc);
	
	void moveTo(String newLoc);
}
