package chess;

public class Bishop extends Piece{

	private String color;
	private Location location;
	boolean check = false;
	public Bishop(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	public boolean canMove(Location newLoc, Board b)
	{
		//System.out.println(b.check);
		//Piece checkPiece = this.getCheckPiece(b);
		//System.out.println(checkPiece);
		/*if (b.check==true)
		{
			return false;
		}*/
		/*if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				//System.out.println("can't move");
				return false;
			}
		}*/
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		int filediff = Math.abs(newFile-currFile);
		int rankdiff = Math.abs(newRank-currRank);
		if (filediff!=rankdiff)
		{
			return false;
		}
		/*if (b.board[newRank][newFile]!=null &&!(b.board[newRank][newFile].getColor().equals(this.color)))
		{
			return true;
		}*/
		if (newFile > currFile && newRank > currRank)	//topright
		{
			for (int i = currRank+1, j = currFile+1; i<newRank; i++, j++)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("entered");
					//System.out.println("topright");
					return false;
				}
			}
		}
		if (newFile > currFile && newRank < currRank)	//bottomright
		{
			for (int i = currRank-1, j = currFile+1; j<newFile; i--, j++)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("bottomright");
					return false;
				}
			}
		}
		if (newFile < currFile && newRank > currRank)	//topleft
		{
			for (int i = currRank+1, j = currFile-1; i<newRank; i++, j--)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("entered");
					//System.out.println("topleft" +  " " + b.board[i][j]);
					return false;
				}
			}
		}
		if (newFile < currFile && newRank < currRank)	//bottomleft
		{
			for (int i = currRank-1, j = currFile-1; i>newRank; i--, j--)
			{
				if (b.board[i][j]!=null)
				{
					//System.out.println("bottomleft");
					return false;
				}
			}
		}
		return true;
	}
	public boolean moveTo(Location newLoc, Board b)
	{
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
			//System.out.println("3");
			System.out.println(checkPiece);
			System.out.println(1);
			return false;
		}
		}
		Piece checkPiece = this.getCheckPiece(b);
		//System.out.println(checkPiece);
		/*if (b.check==true)
		{
			return false;
		}*/
		if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
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
				//System.out.println("1");
				//System.out.println("Check");
				System.out.println(3);
				return false;
			}
			else if(b.check == true && this.canMove(checkLoc, b) && !(newLoc.equals(checkLoc))){
				/*System.out.println("Illegal movie, try again");
				System.out.println();*/
				//System.out.println("2");
				//System.out.println("Check");
				System.out.println(4);
				return false;
			}
		}
		if (this.canMove(newLoc, b))
		{
			//System.out.println("entered into canmove");
			location = newLoc;
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
			//System.out.println("ready to return true");
			return true;
		}
		/*System.out.println("Illegal movie, try again");
		System.out.println();*/
		//System.out.println("4");
		System.out.println(5);
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
			return "wB";
		}
		return "bB";
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
