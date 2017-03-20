package chess;
public class Pawn extends Piece{
	private String color;
	private Location location;
	boolean check = false;
	protected int numMoves;
	public Pawn(Location location, String color){
		this.location = location;
		this.color = color;
		this.numMoves = 0;
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
		//Piece checkPiece = this.getCheckPiece(b);
		Piece p1 = null, p2 = null;
		if(newLoc.getY() - 1 >= 0 && newLoc.getY() - 1 <= 7)
			p1 = b.board[newLoc.getY() - 1][newLoc.convertX()];
		if(newLoc.getY() + 1 >= 0 && newLoc.getY() + 1 <= 7)
			p2 = b.board[newLoc.getY() + 1][newLoc.convertX()];
		/*if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				return false;
			}
		}*/
		if (color.equals("white") && newLoc.getY()<location.getY())
		{
			return false;
		}
		if (color.equals("black") && newLoc.getY()>location.getY())
		{
			return false;
		}
		if(Math.abs(this.location.convertX() - newLoc.convertX()) == 1 &&
				Math.abs(this.location.getY() - newLoc.getY()) == 1){
			if(b.board[newLoc.getY()][newLoc.convertX()] != null)
				return true;
			else{
				if(this.location.getY() < newLoc.getY() && p1 != null){
					if(p1 instanceof Pawn && newLoc.getY() == 5 && !p1.getColor().equals(this.getColor()) && ((Pawn) p1).numMoves == 1){
						b.board[newLoc.getY() - 1][newLoc.convertX()] = null;
						return true;
					}
					else{
						return false;
					}
				}
				else if(p2 != null){
					if(p2 instanceof Pawn && newLoc.getY() == 2 && !p2.getColor().equals(this.getColor()) && ((Pawn) p2).numMoves == 1){
						b.board[newLoc.getY() + 1][newLoc.convertX()] = null;
						return true;
					}
				else{
					return false;
				}
				}
				return false;
			}
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
			System.out.println(1);
			return false;
		}
		}
		Piece p = b.board[newLoc.getY()][newLoc.convertX()];
		Piece checkPiece = this.getCheckPiece(b);
		//System.out.println(checkPiece);
		/*if (b.check==true)
		{
			return false;
		}*/
		if (checkPiece!=null )
		{Location checkLoc = checkPiece.getLocation();
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
			if (checkPiece2.canMove(opposKingsLoc,copy))
			{
				copy = null;
				//System.out.println("about to return false");
				System.out.println(2);
				return false;
			}
			else
				return true;
		}
		if (b.check == true &&!(this.canMove(checkLoc, b)) )
		{
			//System.out.println("can't move");
			/*System.out.println("Illegal movie, try again");
			System.out.println();*/
			System.out.println(3);
			return false;
		}
		else if(b.check == true && this.canMove(checkLoc, b) && !(newLoc.equals(checkLoc))){
			/*System.out.println("Illegal movie, try again");
			System.out.println();*/
			System.out.println(4);
			System.out.println("Check");
			return false;
		}
	}
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
			if(p == null || p.getColor().equals(this.getColor()))
				this.numMoves++;
			return true;
		}
		/*System.out.println("Illegal move, try again");
		System.out.println();*/
		System.out.println(5);
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
