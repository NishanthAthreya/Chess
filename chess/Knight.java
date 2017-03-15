package chess;
public class Knight extends Piece{
	private String color;
	private Location location;
	boolean check = false;
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
		Piece checkPiece = this.getCheckPiece(b);
		if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				return false;
			}
		}
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
	public Location getLocation()
	{
		return location;
	}
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}
