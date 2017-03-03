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
	public boolean canMove(Location newLoc)
	{
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		if ((currFile != newFile)||(currRank != newRank))		//rook can only move in a line, so rank or file must stay the same
		{
			return false;
		}
		return true;
	}
	public String getColor()
	{
		return color;
	}
	public void moveTo(Location newLoc)
	{
		if (this.canMove(newLoc))
		{
			location = newLoc;
		}
		else{
			System.out.println("Illegal move, try again");
		}
	}
	public String toString()
	{
		if (color == "black")
		{
			return "bR";
		}
		return "wR";
	}
}

