package chess;
/**
 * 
 * @author Pranav Kanukollu, pvk9		
 * @author Nishanth Athreya, nsa48
 */
public class Rook extends Piece{

	private String color;
	public Location location;
	public Rook(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
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
				for (int c = currFile; c<= newFile;c++)
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
				for (int c = currFile; c>= newFile;c--)
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
				for (int c = currRank; c<= newRank;c++)
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
				for (int c = currFile; c>= newFile;c--)
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
			location = newLoc;
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
}

