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
	boolean canMove(Location newLoc, Board b) {
		// TODO Auto-generated method stub
		int diffX = Math.abs(this.location.convertX() - newLoc.convertX());
		int diffY = Math.abs(this.location.getY() - newLoc.getY());
		if(diffX == 1){
			if(diffY == 2)
				return true;
		}else if(diffX == 2){
			if(diffY == 1)
				return true;
		}
		return false;
	}

	@Override
	boolean moveTo(Location newLoc, Board b) {
		// TODO Auto-generated method stub
		if(canMove(newLoc, b)){
			this.location = newLoc;
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
			return "bN";
		}
		return "wN";
	}
}
