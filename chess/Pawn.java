package chess;
public class Pawn extends Piece{
	private String color;
	public Location location;
	public Pawn(Location location, String color){
		this.location = location;
		this.color = color;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public String toString()
	{
		if (color.equals("black"))
		{
			return "bp";
		}
		return "wp";
	}
	
	@Override
	boolean canMove(Location newLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean moveTo(Location newLoc) {
		// TODO Auto-generated method stub
		return false;
	}
}
