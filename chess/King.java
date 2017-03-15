package chess;

public class King extends Piece{

	private String color;
	private Location location;
	public King(Location location, String color)
	{
		this.location = location;
		System.out.println("location set");
		this.color = color;
	}
	public boolean canMove(Location newLoc, Board b)
	{
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		int fileDiff = Math.abs(newFile - currFile);
		int rankDiff = Math.abs(newRank - currRank);
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
			return "wK";
		}
		return "bK";
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
