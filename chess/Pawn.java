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
	boolean canMove(Location newLoc, Board b) {
		if(this.location.convertX() != newLoc.convertX())
			return false;
		int diff = Math.abs(this.location.getY() - newLoc.getY());
		if(diff > 2){
			return false;
		}
		if(this.color.equals("black")){
			if(this.location.getY() != 6 && diff > 1)
				return false;
		}
		else if(this.color.equals("white")){
			if(this.location.getY() != 1 && diff > 1)
				return false;
		}
		return true;
	}

	@Override
	boolean moveTo(Location newLoc, Board b) {
		if(canMove(newLoc, b))
		{
			this.location = newLoc;
			return true;
		}
		System.out.println("Illegal move, try again");
		System.out.println();
		return false;
	}
}
