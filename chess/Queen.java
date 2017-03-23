package chess;
/**
 * This class defines a queen and all of its basic moves. This class overrides method from the Piece
 * abstract class.
 * @author Pranav Kanukollu, pvk9
 * @author Nishanth Athreya, nsa48
 *
 */
public class Queen extends Piece{
	private String color;
	private Location location;
	boolean check = false;
	/**
	 * Constructor.
	 * @param location This is the current location of the queen.
	 * @param color This is the color of the queen.
	 */
	public Queen(Location location, String color){
		this.location = location;
		this.color = color;
	}
	/**
	 * This method checks if the queen can move to a new location on the board given the new location and the board.
	 * @param newLoc This is a Location object parameter which is the new Location where the queen is trying to move.
	 * @param b This is a Board object parameter, which is where the queen is moving on.
	 * @return boolean Returns true/false based on whether queen can move to the new location or not.
	 */
	@Override
	boolean canMove(Location newLoc, Board b) {
		
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		int filediff = Math.abs(newFile-currFile);
		int rankdiff = Math.abs(newRank-currRank);
		//diagonal movement
				if (filediff == rankdiff){
					//checking if there is a piece in the way
					if(currFile < newFile && currRank < newRank){
						for(int i = currFile + 1, j = currRank + 1;i < newFile;i++,j++){
							if(b.board[j][i] != null)
								return false;
						}
					}
					else if(currFile < newFile && currRank > newRank){
						for(int i = currFile + 1, j = currRank - 1;i < newFile;i++,j--){
							if(b.board[j][i] != null)
								return false;
						}
					}
					else if(currFile > newFile && currRank < newRank){
						for(int i = currFile - 1, j = currRank + 1;i > newFile;i--,j++){
							if(b.board[j][i] != null)
								return false;
						}
					}
					else if(currFile > newFile && currRank > newRank){
						for(int i = currFile - 1, j = currRank - 1;i > newFile;i--,j--){
							if(b.board[j][i] != null)
								return false;
						}
					}
					if(b.board[newRank][newFile] == null)
						return true;
					else if(!b.board[newRank][newFile].getColor().equals(this.color))
						return true;
				}
				//horizontal movement
				else if(filediff > 0 && rankdiff == 0){
					//checking if there is a piece in the way
					if(currFile < newFile){
						for(int i = currFile + 1;i < newFile;i++){
							if(b.board[currRank][i] != null)
								return false;
						}
					}
					else if(currFile > newFile){
						for(int i = currFile - 1;i > newFile;i--){
							if(b.board[currRank][i] != null)
								return false;
						}
					}
					if(b.board[newRank][newFile] == null)
						return true;
					else if(!b.board[newRank][newFile].getColor().equals(this.color))
						return true;
				}
				//vertical movement
				else if(rankdiff > 0 && filediff == 0){
					//checking if there is a piece in the way
					if(currRank < newRank){
						for(int i = currRank+1;i < newRank;i++){
							if(b.board[i][currFile] != null){
								return false;
							}
						}
					}
					else if(currRank > newRank){
						for(int i = currRank - 1;i > newRank;i--){
							if(b.board[i][currFile] != null){
								return false;
							}
						}
					}
					if(b.board[newRank][newFile] == null)
						return true;
					else if(!b.board[newRank][newFile].getColor().equals(this.color))
						return true;
				}
				//System.out.println("normal");
		return false;
	}
	/**
	 * This method takes into account whether or not there is a check on a king, in which case
	 * the queen can't move. It takes into account various cases where it can move even if there is a check. 
	 * It finally returns true or false based on whether it moved or not.
	 * @param newLoc Location object parameter, which is where the queen is trying to move to.
	 * @param b Board object parameter, which is where the queen is moving on.
	 * @return boolean Returns true or false based on whether the queen has moved or not.
	 */
	@Override
	boolean moveTo(Location newLoc, Board b) {
		if (b.check==false)
		{
		Board copy = b.boardcopy();
		copy.board[newLoc.getY()][newLoc.convertX()] = this;
		copy.board[location.getY()][location.convertX()]=null;
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
		if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
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
				if (checkPiece2.canMove(opposKingsLoc,copy))
				{
					copy = null;
					//System.out.println("about to return false");
					//System.out.println(2);
					return false;
				}
				else
					return true;
			}
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				//System.out.println("can't move");
				/*System.out.println("Illegal movie, try again");
				System.out.println();
				System.out.println("Check");*/
				//System.out.println(3);
				return false;
			}
			else if(b.check == true && this.canMove(checkLoc, b) && !(newLoc.equals(checkLoc))){
				/*System.out.println("Illegal movie, try again");
				System.out.println();
				System.out.println("Check");*/
				//System.out.println(4);
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
		if (this.canMove(newLoc, b))
		{
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
	 * This method returns the color of the queen.
	 * @return String
	 */
	@Override
	String getColor() {
		return color;
	}
	/**
	 * This method returns the string representation of the queen.
	 * @return String
	 */
	public String toString()
	{
		if (color.equals("white"))
		{
			return "wQ";
		}
		return "bQ";
	}
	/**
	 * This method returns the current location of the queen.
	 * @return Location
	 */
	public Location getLocation()
	{
		return location;
	}
	/**
	 * This method sets the location of the queen to a new location.
	 * @param newLoc This is the new location which will be set as the location of the queen.
	 */
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}
