package chess;
/**
 * 
 * @author Pranav Kanukollu, pvk9		
 * @author Nishanth Athreya, nsa48
 */
public class Rook extends Piece{

	private String color;
	private Location location;
	boolean check = false;
	//boolean hasMoved = false;
	public Rook(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	public boolean canMove(Location newLoc, Board b)
	{
		//have to check that no pieces of same color are in the way
		Piece checkPiece = this.getCheckPiece(b);
		if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				return false;
			}
		}
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
					if (b.board[c][currFile]!=null)//piece there
					{
						//System.out.println("Illegal move, try again.");
						return false;
					}
				}
			}
			else if (currRank>newRank) //going down
			{
				for (int c = currFile-1; c>newFile;c--)
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
	public String getColor()
	{
		return color;
	}
	public boolean moveTo(Location newLoc, Board b)
	{
		if (this.canMove(newLoc, b))
		{
			//System.out.println("this is a rook");
			//hasMoved = true;
			location = newLoc;
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
		
			System.out.println("Illegal move, try again");
			System.out.println();
			return false;
		
	}
	public String toString()
	{
		if (color.equals("black"))
		{
			return "bR";
		}
		return "wR";
	}
	public Location getLocation()
	{
		return location;
	}
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}

