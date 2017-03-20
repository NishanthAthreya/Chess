package chess;

public class King extends Piece{

	private String color;
	private Location location;
	boolean hasMoved = false;
	public King(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	public boolean canMove(Location newLoc, Board b)
	{
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		int fileDiff = Math.abs(newFile - currFile);
		int rankDiff = Math.abs(newRank - currRank);
		System.out.println(this.location.convertX() + " " + this.location.getY());
		System.out.println(newLoc.convertX() + " " + newLoc.getY());
		Piece[][] pieces = b.board;
			if (this.color == "white")		//castling
			{
				if (newLoc.getX() == 'g')
				{
					if (hasMoved == true || currRank!=newRank)
					{
						System.out.println("hasMoved is true or currRank is not equal to newRank");
						return false;
					}
					if (pieces[0][7] == null)
					{
						System.out.println("rook is not there");
						return false;
					}
					if (!(pieces[0][7].toString().equals("wR")))
					{
						System.out.println("rooks toString is not matching");
						return false;
					}
					for (int i = currFile+1; i<newFile; i++)
					{
						if (pieces[currRank][i]!=null)	//works
						{
							//System.out.println("found a piece");
							return false; 	//piece there, can't castle
						}
					}
					//System.out.println("setting rooks location");
					isCastling = true;
					pieces[0][7].setLocation(new Location('f',0));
					return true;
				}
				if (newLoc.getX() == 'b')
				{
					if (hasMoved == true || currRank!=newRank)
					{
						System.out.println("king0.5");
						return false;
					}
					if (pieces[0][0] == null)
					{
						System.out.println("king");
						return false;
					}
					if (!(pieces[0][0].toString().equals("wR")))
					{
						System.out.println("king1");
						return false;
					}
					for (int i = currFile-1; i>newFile; i--)
					{
						if (pieces[currRank][i]!=null)
						{
							System.out.println("king2");
							return false; 	//piece there, can't castle
						}
					}
						pieces[0][0].setLocation(new Location('c',0));
						isCastling = true;
						return true;
				}
			}
			
			if (this.color == "black")		//castling
			{
				if (newLoc.getX() == 'g')
				{
					if (hasMoved == true || currRank!=newRank)
					{
						System.out.println("king3");
						return false;
					}
					if (pieces[7][7] == null)
					{
						System.out.println("king4");
						return false;
					}
					if (!(pieces[7][7].toString().equals("bR")))
					{
						System.out.println("king5");
						return false;
					}
					for (int i = currFile+1; i<newFile; i++)
					{
						if (pieces[currRank][i]!=null)
						{
							return false; 	//piece there, can't castle
						}
					}
					isCastling = true;
					pieces[7][7].setLocation(new Location('f',7));
					return true;
				}
				if (newLoc.getX() == 'b')
				{
					if (hasMoved == true || currRank!=newRank)
					{
						System.out.println("king6");
						return false;
					}
					if (pieces[7][0] == null)
					{
						System.out.println("king7");
						return false;
					}
					if (!(pieces[7][0].toString().equals("bR")))
					{
						System.out.println("king8");
						return false;
					}
					for (int i = currFile-1; i>newFile; i--)
					{
						if (pieces[currRank][i]!=null)
						{
							return false; 	//piece there, can't castle
						}
					}
					pieces[7][0].setLocation(new Location('c',7));
					isCastling = true;
					return true;
				}
			}
		if (b.board[newRank][newFile]!=null && b.board[newRank][newFile].getColor() == this.color)
		{
			System.out.println("king9");
			return false;
		}
		if (currFile == newFile && rankDiff == 1)
		{
			System.out.println("king10");
			return true;
		}
		if (currRank == newRank && fileDiff == 1)
		{
			return true;
		}
		if (fileDiff == 1 && rankDiff == 1)
		{
			return true;
		}
		System.out.println("king11");
		return false;
	}
	public boolean moveTo(Location newLoc, Board b)
	{
		if (this.canMove(newLoc, b))
		{
			hasMoved = true;
			location = newLoc;
			return true;
		}
		System.out.println("Illegal move, try again");
		System.out.println();
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
			return "wK";
		}
		return "bK";
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
