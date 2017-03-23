package chess;
/**
 * This class defines a king and all of its moves. The class overrides all the methods 
 * from the Piece abstract class.
 * 
 * @author Pranav Kanukollu, pvk9		
 * @author Nishanth Athreya, nsa48
 */
public class King extends Piece{

	private String color;
	private Location location;
	boolean hasMoved = false;
	/**
	 * Constructor.
	 * 
	 * @param location Current location of the king.
	 * @param color Color of the king.
	 */
	public King(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	/**
	 * This method checks if the king can move to a specified location on the board. 
	 * @param newLoc This is a Location object parameter which is the new Location where the king is trying to move.
	 * @param b This is a Board object parameter, which is where the piece is moving on
	 * @return boolean This method returns true or false based on whether or not the king can move to the the newLoc.
	 */
	public boolean canMove(Location newLoc, Board b)
	{
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		int fileDiff = Math.abs(newFile - currFile);
		int rankDiff = Math.abs(newRank - currRank);
		Piece[][] pieces = b.board;
			if (this.color.equals("white"))		//castling
			{
				if (newLoc.getX() == 'g')
				{
					boolean c = true;
					if (hasMoved == true || currRank!=newRank)
					{
						c = false;
					}
					if(c){
					if (pieces[0][7] == null)
					{
						return false;
					}
					if (!(pieces[0][7].toString().equals("wR")))
					{
						return false;
					}
					for (int i = currFile+1; i<newFile; i++)
					{
						if (pieces[currRank][i]!=null)	//works
						{
							return false; 	//piece there, can't castle
						}
					}
					isCastling = true;
					pieces[0][7].setLocation(new Location('f',0));
					return true;
					}
				}
				if (newLoc.getX() == 'b')
				{
					boolean c = true;
					if (hasMoved == true || currRank!=newRank)
					{
						c = false;
					}
					if(c){
					if (pieces[0][0] == null)
					{
						return false;
					}
					if (!(pieces[0][0].toString().equals("wR")))
					{
						return false;
					}
					for (int i = currFile-1; i>newFile; i--)
					{
						if (pieces[currRank][i]!=null)
						{
							return false; 	//piece there, can't castle
						}
					}
						pieces[0][0].setLocation(new Location('c',0));
						isCastling = true;
						return true;
				}
				}
			}
			
			if (this.color.equals("black"))		//castling
			{
				if (newLoc.getX() == 'g')
				{
					boolean c = true;
					
					if (hasMoved == true || currRank!=newRank)
					{
						c = false;//no castling
					}
					if(c){
					if (pieces[7][7] == null)
					{
						return false;
					}
					if (!(pieces[7][7].toString().equals("bR")))
					{
						return false;
					}
					for (int i = currFile+1; i<newFile; i++)
					{
						if (pieces[currRank][i]!=null)
						{
							return false; 	//piece there, can't castle
						}
					}
					isCastling = true;
					pieces[7][7].setLocation(new Location('f',7));
					return true;
					}
				}
				if (newLoc.getX() == 'b')
				{
					boolean c = true;
					if (hasMoved == true || currRank!=newRank)
					{
						c = false;
					}
					if(c){
					if (pieces[7][0] == null)
					{
						return false;
					}
					if (!(pieces[7][0].toString().equals("bR")))
					{
						return false;
					}
					for (int i = currFile-1; i>newFile; i--)
					{
						if (pieces[currRank][i]!=null)
						{
							return false; 	//piece there, can't castle
						}
					}
					pieces[7][0].setLocation(new Location('c',7));
					isCastling = true;
					return true;
					}
				}
			}
		if (b.board[newRank][newFile]!=null && b.board[newRank][newFile].getColor() == this.color)
		{
			return false;
		}
		if (currFile == newFile && rankDiff == 1)
		{
			return true;
		}
		if (currRank == newRank && fileDiff == 1)
		{
			return true;
		}
		if (fileDiff == 1 && rankDiff == 1)
		{
			return true;
		}
		return false;
	}
	/**
	 * This method takes into account whether or not there is a check on a king, in which case
	 * it can't move. It takes into account various cases where it can move even if there is a check. 
	 * It finally returns true or false based on whether it moved or not.
	 * @param newLoc Location object parameter, which is where the king is trying to move to.
	 * @param b Board object parameter, which is where the king is moving on.
	 * @return boolean Returns true or false based on whether the king has moved or not.
	 */
	public boolean moveTo(Location newLoc, Board b)
	{
		if (b.check==false)//new stuff
		{
		Board copy = b.boardcopy();
		copy.board[newLoc.getY()][newLoc.convertX()] = this;
		copy.board[location.getY()][location.convertX()]=null;
		for(int r = 0;r < copy.board.length;r++){//new check
			for(int c = 0;c < copy.board[r].length;c++){
				Piece piece = copy.board[r][c];
				if(piece != null && !(piece.getColor().equals(this.color))){
					Location opposKingsLoc = piece.getKingLocation(piece.getColor(), copy);
					if(piece.canMove(opposKingsLoc, copy)){
						copy = null;
						System.out.println(piece);
						System.out.println(piece.getLocation().getX() + " " + piece.getLocation().getY());
						System.out.println(opposKingsLoc.getX() + " " + opposKingsLoc.getY());
						//System.out.println("yes");
						return false;
					}
				}
			}
		}
		}
		Piece checkPiece = this.getCheckPiece(b);
		if (checkPiece!=null )
		{
			//System.out.println("no");
			Location checkLoc = checkPiece.getLocation();
			//boolean flag = false;
			Board copy = b.boardcopy();
			//Piece checkPiece2 = this.getCheckPiece(copy);
			Piece checkPiece2 = copy.board[checkLoc.getY()][checkLoc.convertX()];
			//System.out.println(checkPiece2);
			if(canMove(newLoc, copy)){
				if(copy.board[newLoc.getY()][newLoc.convertX()] != null && this.getColor().equals(copy.board[newLoc.getY()][newLoc.convertX()].getColor()))//same color
					return false;//new
				copy.board[newLoc.getY()][newLoc.convertX()] = this;
				copy.board[location.getY()][location.convertX()]=null;
				checkPiece2 = this.getCheckPiece(copy);
				if(checkPiece2 == null)
					return true;
				Location opposKingsLoc = checkPiece2.getKingLocation(checkPiece2.getColor(), copy);
				for(int r = 0;r < copy.board.length;r++){//new check
					for(int c = 0;c < copy.board[r].length;c++){
						Piece p = copy.board[r][c];
						if(p != null && !(p.getColor().equals(copy.board[opposKingsLoc.getY()][opposKingsLoc.convertX()].getColor())) && p.canMove(opposKingsLoc, copy)){
							copy.draw();
							copy = null;
							return false;
						}
					}
				}
				if (checkPiece2.canMove(opposKingsLoc,copy))
				{
					copy = null;
					return false;
				}
				else
					return true;
			}
		}
		if (this.canMove(newLoc, b))
		{
			hasMoved = true;
			return true;
		}
		return false;
	}
	/**
	 * This method returns the color of the king.
	 * @return String The type of the color is String.
	 */
	public String getColor()
	{
		return color;
	}
	/**
	 * This method returns a String version of the king based on its color.
	 * @return String 
	 */
	public String toString()
	{
		if (color.equals("white"))
		{
			return "wK";
		}
		return "bK";
	}
	/**
	 * This method returns the current location of the king.
	 * @return Location Return type is a Location object.
	 */
	public Location getLocation()
	{
		return location;
	}
	/**
	 * This method sets the location of the rook to a new Location.
	 * @param newLoc This is a Location object which will be set to the location field of the king.
	 * @return Nothing
	 */
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}
