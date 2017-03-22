package chess;
/**
 * This class defines a bishop and all of its basic moves. This class overrides the methods from the Piece 
 * abstract class.
 * @author Pranav Kanukollu, pvk9
 * @author Nishanth Athreya, nsa48
 */
public class Bishop extends Piece{
	private String color;
	private Location location;
	boolean check = false;
	/**
	 * Constructor.
	 * 
	 * @param location Current location of the bishop
	 * @param color	Current color of the bishop
	 */
	public Bishop(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	/**
	 * This method checks if the bishop can move to a new location on the board given the new location and the board.
	 * @param newLoc This is a Location object parameter which is the new Location where the bishop is trying to move.
	 * @param b This is a Board object parameter, which is where the piece is moving on.
	 * @return boolean Returns true/false based on whether bishop can move to the new location or not.
	 */
	public boolean canMove(Location newLoc, Board b)
	{
		//System.out.println(b.check);
		//Piece checkPiece = this.getCheckPiece(b);
		//System.out.println(checkPiece);
		/*if (b.check==true)
		{
			return false;
		}*/
		/*if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				//System.out.println("can't move");
				return false;
			}
		}*/
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		int filediff = Math.abs(newFile-currFile);
		int rankdiff = Math.abs(newRank-currRank);
		if (filediff!=rankdiff)
		{
			return false;
		}
		/*if (b.board[newRank][newFile]!=null &&!(b.board[newRank][newFile].getColor().equals(this.color)))
		{
			return true;
		}*/
		if (newFile > currFile && newRank > currRank)	//topright
		{
			for (int i = currRank+1, j = currFile+1; i<newRank; i++, j++)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("entered");
					//System.out.println("topright");
					return false;
				}
			}
		}
		if (newFile > currFile && newRank < currRank)	//bottomright
		{
			for (int i = currRank-1, j = currFile+1; j<newFile; i--, j++)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("bottomright");
					return false;
				}
			}
		}
		if (newFile < currFile && newRank > currRank)	//topleft
		{
			for (int i = currRank+1, j = currFile-1; i<newRank; i++, j--)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("entered");
					//System.out.println("topleft" +  " " + b.board[i][j]);
					return false;
				}
			}
		}
		if (newFile < currFile && newRank < currRank)	//bottomleft
		{
			for (int i = currRank-1, j = currFile-1; i>newRank; i--, j--)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("bottomleft");
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * This method takes into account whether or not there is a check on a king, in which case
	 * the bishop can't move. It takes into account various cases where it can move even if there is a check. 
	 * It finally returns true or false based on whether it moved or not.
	 * @param newLoc Location object parameter, which is where the bishop is trying to move to.
	 * @param b Board object parameter, which is where the bishop is moving on.
	 * @return boolean Returns true or false based on whether the bishop has moved or not.
	 */
	public boolean moveTo(Location newLoc, Board b)
	{
		if (b.check==false)
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
						return false;
					}
				}
			}
		}
		Piece checkPiece = this.getCheckPiece(copy);
		if (checkPiece!=null && !(this.getColor().equals(checkPiece.getColor())))
		{
			copy = null;
			/*System.out.println();
			System.out.println("Illegal move, try again");
			System.out.println();*/
			//System.out.println("3");
			//System.out.println(checkPiece);
			//System.out.println(1);
			return false;
		}
		}
		Piece checkPiece = this.getCheckPiece(b);
		//System.out.println(checkPiece);
		/*if (b.check==true)
		{
			return false;
		}*/
		if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			Board copy = b.boardcopy();
			//Piece checkPiece2 = this.getCheckPiece(copy);
			Piece checkPiece2 = copy.board[checkLoc.getY()][checkLoc.convertX()];
			//System.out.println(checkPiece2);
			if(canMove(newLoc, copy)){
				if(copy.board[newLoc.getY()][newLoc.convertX()] != null && this.getColor().equals(copy.board[newLoc.getY()][newLoc.convertX()] .getColor()))//same color
					return false;//new
				copy.board[newLoc.getY()][newLoc.convertX()] = this;
				copy.board[location.getY()][location.convertX()]=null;
				checkPiece2 = this.getCheckPiece(copy);
				if(checkPiece2 == null)
					return true;
				Location opposKingsLoc = checkPiece2.getKingLocation(checkPiece2.getColor(), copy);
				//System.out.println("copy: ");
				//copy.draw();
				if (checkPiece2.canMove(opposKingsLoc,copy))
				{
					copy = null;
					//System.out.println("about to return false");
					//System.out.println(2);
					return false;
				}
				else
					return true;
			}
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				//System.out.println("can't move");
				/*System.out.println("Illegal movie, try again");
				System.out.println();*/
				//System.out.println("1");
				//System.out.println("Check");
				//System.out.println(3);
				return false;
			}
			else if(b.check == true && this.canMove(checkLoc, b) && !(newLoc.equals(checkLoc))){
				/*System.out.println("Illegal movie, try again");
				System.out.println();*/
				//System.out.println("2");
				//System.out.println("Check");
				//System.out.println(4);
				return false;
			}
		}
		else{
			Board copy = b.boardcopy();
			copy.board[newLoc.getY()][newLoc.convertX()] = this;
			copy.board[location.getY()][location.convertX()]=null;
			for(int r = 0;r < copy.board.length;r++){//new check
				for(int c = 0;c < copy.board[r].length;c++){
					Piece p = copy.board[r][c];
					if(p == null)
						break;
					if(p.getColor().equals(this.color))
						break;
					Location opposKingsLoc = p.getKingLocation(p.getColor(), copy);
					if(p.canMove(opposKingsLoc, b)){
						copy = null;
						return false;
					}
				}
			}
		}
		if (this.canMove(newLoc, b))
		{
			//System.out.println("entered into canmove");
			location = newLoc;
			Location opposKingsLoc = this.getKingLocation(this.color, b);
			//System.out.println("Bishop's loc: " + this.getLocation().getX()+ " " + this.getLocation().getY());
			//System.out.println("King's Loc: " + opposKingsLoc.getX() + " " + opposKingsLoc.getY());
			//System.out.println("called getKingLoc");
			if (canMove(opposKingsLoc,b))
			{
				//System.out.println("entered");
				b.check = true;
				//System.out.println("Check");
			}
			//System.out.println("ready to return true");
			return true;
		}
		/*System.out.println("Illegal movie, try again");
		System.out.println();*/
		//System.out.println("4");
		//System.out.println(5);
		return false;
	}
	/**
	 * This method returns the color of the bishop.
	 * @return String
	 */
	public String getColor()
	{
		return color;
	}
	/**
	 * This method returns the string version of the bishop.
	 * @return String
	 */
	public String toString()
	{
		if (color == "white")
		{
			return "wB";
		}
		return "bB";
	}
	/**
	 * This method returns the current location of the bishop.
	 * @return Location
	 */
	public Location getLocation()
	{
		return location;
	}
	/**
	 * This method sets the location of the bishop to a new given location.
	 * @param newLoc This is what the location of the bishop will be set to.
	 */
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}
