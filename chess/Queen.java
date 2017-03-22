package chess;

public class Queen extends Piece{
	private String color;
	private Location location;
	boolean check = false;
	public Queen(Location location, String color){
		this.location = location;
		this.color = color;
	}
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
			this.location = newLoc;
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

	@Override
	String getColor() {
		return color;
	}
	public String toString()
	{
		if (color.equals("white"))
		{
			return "wQ";
		}
		return "bQ";
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
