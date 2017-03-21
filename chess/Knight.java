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
		//System.out.println(this.location);
		//System.out.println(newLoc == null);
	/*	if (b.check==false)
		{
		Board copy = b.boardcopy();
		copy.board[newLoc.getY()][newLoc.convertX()] = this;
		copy.board[location.getY()][location.convertX()]=null;
		Piece checkPiece = this.getCheckPiece(copy);
		if (checkPiece!=null)
		{
			copy = null;
			return false;
		}
		}*/
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
		if (b.check==false)
		{
		Board copy = b.boardcopy();
		copy.board[newLoc.getY()][newLoc.convertX()] = this;
		copy.board[location.getY()][location.convertX()]=null;
		Piece checkPiece = this.getCheckPiece(copy);
		if (checkPiece!=null && !(this.getColor().equals(checkPiece.getColor())))
		{
			copy = null;
			/*System.out.println();
			System.out.println("Illegal move, try again");
			System.out.println();*/
			//System.out.println(1);
			return false;
		}
		}
		Piece checkPiece = this.getCheckPiece(b);
		//System.out.println(checkPiece);
		/*if (b.check==true)
		{
			return false;
		}*/
		if (checkPiece!=null )
		{
			Location checkLoc = checkPiece.getLocation();
			//boolean flag = false;
			Board copy = b.boardcopy();
			//Piece checkPiece2 = this.getCheckPiece(copy);
			Piece checkPiece2 = copy.board[checkLoc.getY()][checkLoc.convertX()];
			//System.out.println(checkPiece2);
			if(canMove(newLoc, copy)){
				copy.board[newLoc.getY()][newLoc.convertX()] = this;
				copy.board[location.getY()][location.convertX()]=null;
				checkPiece2 = this.getCheckPiece(copy);
				if(checkPiece2 == null)
					return true;
				Location opposKingsLoc = checkPiece2.getKingLocation(checkPiece2.getColor(), copy);
				//System.out.println("copy: ");
				//copy.draw();
				//System.out.println(checkPiece2);
				if (checkPiece2.canMove(opposKingsLoc,copy))
				{
					copy = null;
					//System.out.println("about to return false");
					//System.out.println(2);
					return false;
				}
				else
					//System.out.println("about to return true");
					return true;
			}
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				//System.out.println("can't move");
				/*System.out.println("Illegal movie, try again");
				System.out.println();*/
				//System.out.println(3);
				System.out.println("Check");
				return false;
			}
			else if(b.check == true && this.canMove(checkLoc, b) && !(newLoc.equals(checkLoc))){
				/*System.out.println("Illegal movie, try again");
				System.out.println();*/
				//System.out.println(4);
				System.out.println("Check");
				return false;
			}
		}
		//return super.moveTo(newLoc, b);
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
		/*System.out.println("Illegal move, try again");
		System.out.println();*/
		//System.out.println(5);
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
