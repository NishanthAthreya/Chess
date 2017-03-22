package chess;
/**
 * This class is an abstract class which defines what all pieces in the chessboard should have. 
 * This class also defines a few default methods which are used for check and checkmate.
 * @author Pranav Kanukollu, pvk9
 * @author Nishanth Athreya, nsa48
 */
public abstract class Piece {
	protected String color;
	protected boolean check;
	protected Location location;
	boolean isCastling = false;
	/**
	 * This method is implemented by all pieces to check whether that piece can move to a specified location
	 * on the board or not.
	 * @param newLoc Location object where the piece is trying to move to.
	 * @param b	Board object
	 * @return boolean Returns true or false based on whether the piece can move or not.
	 */
	abstract boolean canMove(Location newLoc, Board b);
	
	/**
	 * This method indicates whether a piece has moved to where it is trying to move or some obstacle came 
	 * in the way for it.
	 * @param newLoc Location object where the piece is trying to move to.
	 * @param b	Board objext
	 * @return boolean Returns true or false based on whether the piece has moved or not.
	 */
	abstract boolean moveTo(Location newLoc, Board b);
	
	/**
	 * This method returns the Piece which is causing the check on the opposing king on the board.
	 * @param b Board object to go through the whole board to see which piece is causing the check.
	 * @return Piece Returns the piece which is causing the check.
	 */
	protected Piece getCheckPiece(Board b)		//returns the piece that is causing the check
	{
		Piece[][] pieces = b.board;
		Piece check = null;
		for (int i = 0; i<pieces.length;i++)
		{
			for (int j = 0; j<pieces[0].length;j++)
			{
				
				if (pieces[i][j]!=null)
				{
					
					if (pieces[i][j].check==true)
					{
						//System.out.println(pieces[i][j].check);
						check = pieces[i][j];
						break;
					}
				}
			}
		}
		return check;
	}
	
	/**
	 * This method returns the Location of the opposing king.
	 * @param color String object, which is used to check only opposing color king.
	 * @param b	Board object
	 * @return Location Returns the location of the opposing king.
	 */
	protected Location getKingLocation(String color, Board b)	//returns the location of the opposing king
	{
		Piece[][] pieces = b.board;
		Location loc = null;
		//System.out.println("about to enter for loop");
		for (int i = 0; i<pieces.length;i++)
		{
			//System.out.println("entered first for loop");
			for (int j = 0; j<pieces[0].length;j++)
			{
				if (pieces[i][j]!=null)
				{
				//System.out.println("entered second for loop"+pieces[i][j].toString());
				if (color.equals("white") && pieces[i][j].toString().equals("bK"))
				{
					//System.out.println("entered if" + pieces[i][j].location);
					//loc = pieces[i][j].getLocation();
					loc = new Location((char)('a'+j),i);
				}
				else if (color.equals("black") && pieces[i][j].toString().equals("wK"))
				{
					//loc = pieces[i][j].getLocation();
					loc = new Location((char)('a'+j),i);
				}
				}
			}
		}
		//System.out.println(loc.getX() + " " + loc.getY());
		return loc;
	}
	/**
	 * This method returns the color of the current piece.
	 * @return String
	 */
	abstract String getColor();
	/**
	 * This method returns the location of the current piece.
	 * @return Location
	 */
	abstract Location getLocation();
	/**
	 * This method sets the location of the current piece to a given new location.
	 * @param newLoc 
	 */
	abstract void setLocation(Location newLoc);
}