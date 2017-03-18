package chess;
public class Pawn extends Piece{
	private String color;
	private Location location;
	boolean check = false;
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
		//killing
		Piece checkPiece = this.getCheckPiece(b);
		if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				return false;
			}
		}
		if (color == "white" && newLoc.getY()<location.getY())
		{
			return false;
		}
		if (color == "black" && newLoc.getY()>location.getY())
		{
			return false;
		}
		if(Math.abs(this.location.convertX() - newLoc.convertX()) == 1 &&
				Math.abs(this.location.getY() - newLoc.getY()) == 1){
			if(b.board[newLoc.getY()][newLoc.convertX()] != null)
				return true;
			else
				return false;
		}
		//moving
		if(this.location.convertX() != newLoc.convertX())
			return false;
		if(b.board[newLoc.getY()][newLoc.convertX()] != null)//cannot kill while moving
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
	public Location getLocation()
	{
		return location;
	}
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}
