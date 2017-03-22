package chess;
/**
 * This class defines a rook and all of its basic moves. This class overrides all the methods
 * from the Piece abstract class.
 * 
 * @author Pranav Kanukollu, pvk9		
 * @author Nishanth Athreya, nsa48
 */
public class Rook extends Piece{

	private String color;
	private Location location;
	boolean check = false;
	//boolean hasMoved = false;
	/**
	 * Constructor.
	 * 
	 * @param location Current location of the rook.
	 * @param color	Color of the rook.
	 */
	public Rook(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	/**
	 * This method checks if the rook can move to a specified location on the board. 
	 * @param newLoc This is a Location object parameter which is the new Location where the rook is trying to move.
	 * @param b This is a Board object parameter, which is where the piece is moving on
	 * @return boolean This method returns true or false based on whether or not the rook can move to the the newLoc.
	 */
	public boolean canMove(Location newLoc, Board b)
	{
		//have to check that no pieces of same color are in the way
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		if ((currFile != newFile)&&(currRank != newRank))		//rook can only move in a line, so rank or file must stay the same
		{
			return false;
		}
		if (currRank == newRank)
		{
			if (currFile<newFile) //going right
			{
				for (int c = currFile+1; c< newFile;c++)
				{
					if (b.board[currRank][c]!=null)//piece there
					{
						//System.out.println("Illegal move, try again.");
						return false;
					}
				}
			}
			else if (currFile>newFile) //going left
			{
				for (int c = currFile-1; c> newFile;c--)
				{
					if (b.board[currRank][c]!=null)//piece there
					{
						//System.out.println("Illegal move, try again.");
						return false;
					}
				}
			}
		}
		if (currFile == newFile)
		{
			if (currRank<newRank) //going up
			{
				for (int c = currRank+1; c< newRank;c++)
				{
					/*System.out.println("list:");
					System.out.println(b.board[c][currFile]);*/
					if (b.board[c][currFile]!=null)//piece there
					{
						//System.out.println("Illegal move, try again.");
						return false;
					}
				}
			}
			else if (currRank>newRank) //going down
			{
				for (int c = currRank-1; c>newRank;c--)
				{
					if (b.board[c][currFile]!=null)//piece there
					{
						//System.out.println("Illegal move, try again.");
						return false;
					}
				}
			}
		}
		return true;
	}
	/**
	 * This method returns the color of the rook.
	 * @return String The type of the color is String.
	 */
	public String getColor()
	{
		return color;
	}
	/**
	 * This method takes into account whether or not there is a check on a king, in which case
	 * it can't move. It takes into account various cases where it can move even if there is a check. 
	 * It finally returns true or false based on whether it moved or not.
	 * @param newLoc Location object parameter, which is where the rook is trying to move to.
	 * @param b Board object parameter, which is where the rook is moving on.
	 * @return boolean Returns true or false based on whether the rook has moved or not.
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
					//System.out.println("copy 2:");
					//copy.draw();
					//System.out.println(opposKingsLoc);
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
			//System.out.println("57");
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
				//System.out.println("Check");
				//System.out.println("1");
				//System.out.println(3);
				return false;
			}
			else if(b.check == true && this.canMove(checkLoc, b) && !(newLoc.equals(checkLoc))){
				/*System.out.println("Illegal movie, try again");
				System.out.println();
				System.out.println("Check");*/
				//System.out.println("2");
				//System.out.println(4);
				return false;
			}
		}
		if (this.canMove(newLoc, b))
		{
			//System.out.println("this is a rook");
			//hasMoved = true;
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
		/*	Location opposKingsLoc = this.getKingLocation(this.color, b);
			//System.out.println("Bishop's loc: " + this.getLocation().getX()+ " " + this.getLocation().getY());
			//System.out.println("King's Loc: " + opposKingsLoc.getX() + " " + opposKingsLoc.getY());
			//System.out.println("called getKingLoc");
			if (canMove(opposKingsLoc,b))
			{
				System.out.println("entered checking");
				//this.check = true;
				//b.board[this.getLocation().getY()][this.getLocation().convertX()].check = true;
				//b.check = true;
				System.out.println();
				System.out.println("Check");
				System.out.println();
			}*/
			//System.out.println(this.check);
			return true;
		}
		
			/*System.out.println("Illegal move, try again");
			System.out.println();*/
		//System.out.println("3");
		//System.out.println(5);
			return false;
		
	}
	/**
	 * This method returns a String version of the Rook based on its color.
	 * @return String 
	 */
	public String toString()
	{
		if (color.equals("black"))
		{
			return "bR";
		}
		return "wR";
	}
	/**
	 * This method returns the current location of the rook.
	 * @return Location Return type is a Location object.
	 */
	public Location getLocation()
	{
		return location;
	}
	/**
	 * This method sets the location of the rook to a new Location.
	 * @param newLoc This is a Location object which will be set to the location field of the rook.
	 * @return Nothing
	 */
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}

