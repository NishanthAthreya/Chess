package chess;

public class Bishop extends Piece{

	private String color;
	private Location location;
	boolean check = false;
	public Bishop(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	public boolean canMove(Location newLoc, Board b)
	{
		//System.out.println(b.check);
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
	public boolean moveTo(Location newLoc, Board b)
	{
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
				System.out.println("Check");
			}
			//System.out.println("ready to return true");
			return true;
		}
		System.out.println("Illegal movie, try again");
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
	public Location getLocation()
	{
		return location;
	}
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}
