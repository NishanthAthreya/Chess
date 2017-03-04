package chess;
public class Knight extends Piece{
	private String color;
	public Location location;
	public Knight(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	
	public String getColor()
	{
		return color;
	}

	@Override
	boolean canMove(Location newLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void moveTo(Location newLoc) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString()
	{
		if (color.equals("black"))
		{
			return "bK";
		}
		return "wK";
	}
}
