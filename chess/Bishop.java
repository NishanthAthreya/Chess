package chess;

public class Bishop extends Piece{

	private String color;
	public Location location;
	public Bishop(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	public boolean canMove(Location newLoc, Board b)
	{
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
		if (newFile > currFile && newRank > currRank)	//topright
		{
			for (int i = currRank+1, j = currFile+1; i<=newRank; i++, j++)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("topright");
					return false;
				}
			}
		}
		if (newFile > currFile && newRank < currRank)	//bottomright
		{
			for (int i = currRank-1, j = currFile+1; j<=newFile; i--, j++)
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
			for (int i = currRank+1, j = currFile-1; i<=newRank; i++, j--)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("topleft" +  " " + b.board[i][j]);
					return false;
				}
			}
		}
		if (newFile < currFile && newRank < currRank)	//bottomleft
		{
			for (int i = currRank-1, j = currFile-1; i>=newRank; i--, j--)
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
	public boolean moveTo(Location newLoc, Board b)
	{
		if (this.canMove(newLoc, b))
		{
			location = newLoc;
			return true;
		}
		System.out.println("Illegal move, try again");
		System.out.println();
		return false;
	}
	public String getColor()
	{
		return color;
	}
	public String toString()
	{
		if (color == "white")
		{
			return "wB";
		}
		return "bB";
	}
}
