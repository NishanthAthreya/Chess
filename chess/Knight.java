package chess;
/**
 * This class defines a knight and all of its moves. The class overrides all the methods 
 * from the Piece abstract class.
 * 
 * @author Pranav Kanukollu, pvk9		
 * @author Nishanth Athreya, nsa48
 */
public class Knight extends Piece{
	private String color;
	private Location location;
	boolean check = false;
	/**
	 * Constructor.
	 * 
	 * @param location Current location of the knight.
	 * @param color Color of the knight.
	 */
	public Knight(Location location, String color)
	{
		this.location = location;
		this.color = color;
	}
	/**
	 * This method returns the color of the knight.
	 * @return String The type of the color is String.
	 */
	public String getColor()
	{
		return color;
	}
	/**
	 * This method checks if the knight can move to a specified location on the board. 
	 * @param newLoc This is a Location object parameter which is the new Location where the knight is trying to move.
	 * @param b This is a Board object parameter, which is where the piece is moving on
	 * @return boolean This method returns true or false based on whether or not the knight can move to the the newLoc.
	 */
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
	/**
	 * This method takes into account whether or not there is a check on a king, in which case
	 * it can't move. It takes into account various cases where it can move even if there is a check. 
	 * It finally returns true or false based on whether it moved or not.
	 * @param newLoc Location object parameter, which is where the knight is trying to move to.
	 * @param b Board object parameter, which is where the knight is moving on.
	 * @return boolean Returns true or false based on whether the knight has moved or not.
	 */
	@Override
	boolean moveTo(Location newLoc, Board b) {
		// TODO Auto-generated method stub
		if (b.check==false)
		{
		Board copy = b.boardcopy();
		copy.board[newLoc.getY()][newLoc.convertX()] = this;
		copy.board[location.getY()][location.convertX()]=null;
		Piece checkPiece = this.getCheckPiece(copy);
		for(int r = 0;r < copy.board.length;r++){//new check
			for(int c = 0;c < copy.board[r].length;c++){
				Piece piece = copy.board[r][c];
				if(piece != null && !(piece.getColor().equals(this.color))){
					Location opposKingsLoc = piece.getKingLocation(piece.getColor(), copy);
					if(piece.canMove(opposKingsLoc, copy)){
						copy = null;
						return false;
					}
				}
			}
		}
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
				if(copy.board[newLoc.getY()][newLoc.convertX()] != null && this.getColor().equals(copy.board[newLoc.getY()][newLoc.convertX()] .getColor()))//same color
					return false;//new
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
				//System.out.println("Check");
				return false;
			}
			else if(b.check == true && this.canMove(checkLoc, b) && !(newLoc.equals(checkLoc))){
				/*System.out.println("Illegal movie, try again");
				System.out.println();*/
				//System.out.println(4);
				//System.out.println("Check");
				return false;
			}
		}
		else{
			Board copy = b.boardcopy();
			copy.board[newLoc.getY()][newLoc.convertX()] = this;
			copy.board[location.getY()][location.convertX()]=null;
			for(int r = 0;r < copy.board.length;r++){//new check
				for(int c = 0;c < copy.board[r].length;c++){
					Piece p = copy.board[r][c];
					if(p == null)
						break;
					if(p.getColor().equals(this.color))
						break;
					Location opposKingsLoc = p.getKingLocation(p.getColor(), copy);
					if(p.canMove(opposKingsLoc, b)){
						copy = null;
						return false;
					}
				}
			}
		}
		//return super.moveTo(newLoc, b);
		if(canMove(newLoc, b)){
			//this.location = newLoc;
			Location opposKingsLoc = this.getKingLocation(this.color, b);
			//System.out.println("Bishop's loc: " + this.getLocation().getX()+ " " + this.getLocation().getY());
			//System.out.println("King's Loc: " + opposKingsLoc.getX() + " " + opposKingsLoc.getY());
			//System.out.println("called getKingLoc");
			if (canMove(opposKingsLoc,b))
			{
				//System.out.println("entered");
				b.check = true;
				//System.out.println("Check");
			}
			return true;
		}
		/*System.out.println("Illegal move, try again");
		System.out.println();*/
		//System.out.println(5);
		return false;
	}
	/**
	 * This method returns a String version of the knight based on its color.
	 * @return String 
	 */
	public String toString()
	{
		if (color.equals("black"))
		{
			return "bN";
		}
		return "wN";
	}
	/**
	 * This method returns the current location of the knight.
	 * @return Location Return type is a Location object.
	 */
	public Location getLocation()
	{
		return location;
	}
	/**
	 * This method sets the location of the knight to a new Location.
	 * @param newLoc This is a Location object which will be set to the location field of the knight.
	 * @return Nothing
	 */
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}
