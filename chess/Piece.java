package chess;

public abstract class Piece {
	protected String color;
	protected boolean check;
	protected Location location;
	abstract boolean canMove(Location newLoc, Board b);
	
	abstract boolean moveTo(Location newLoc, Board b);
	
	protected Piece getCheckPiece(Board b)		//returns the piece that is causing the check
	{
		Piece[][] pieces = b.board;
		Piece check = null;
		for (int i = 0; i<pieces.length;i++)
		{
			for (int j = 0; j<pieces[0].length;j++)
			{
				if (pieces[i][j]!=null)
				{
					if (pieces[i][j].check==true)
					{
						check = pieces[i][j];
						break;
					}
				}
			}
		}
		return check;
	}
	protected Location getKingLocation(String color, Board b)	//returns the location of the opposing king
	{
		Piece[][] pieces = b.board;
		Location loc = null;
		//System.out.println("about to enter for loop");
		for (int i = 0; i<pieces.length;i++)
		{
			//System.out.println("entered first for loop");
			for (int j = 0; j<pieces[0].length;j++)
			{
				if (pieces[i][j]!=null)
				{
				//System.out.println("entered second for loop"+pieces[i][j].toString());
				if (color.equals("white") && pieces[i][j].toString().equals("bK"))
				{
					//System.out.println("entered if" + pieces[i][j].location);
					loc = pieces[i][j].getLocation();
				}
				else if (color.equals("black") && pieces[i][j].toString().equals("wK"))
				{
					loc = pieces[i][j].getLocation();
				}
				}
			}
		}
		//System.out.println(loc.getX() + " " + loc.getY());
		return loc;
	}
	abstract String getColor();
	abstract Location getLocation();
	abstract void setLocation(Location newLoc);
}
