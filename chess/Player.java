package chess;
/**
 * This class defines a Player object, which can move a piece from one location on the board to another.
 * This class also has implementations for checkmate and stalemate.
 * @author Pranav Kanukollu, pvk9
 * @author Nishanth Athreya, nsa48
 *
 */
public class Player {
	private String color;
	/**
	 * Constructor.
	 * @param color This is the color of the pieces that the Player moves.
	 */
	public Player(String color){
		this.color = color;
	}
	/**
	 * This method moves a piece from location to another, if it is allowed to do so. This is also where a
	 * piece captures another piece of the opposing color, if the other piece is in the final location of the piece.
	 * Additionally, this method defines promotion for a pawn when it reaches the other side of the board.
	 * @param b Board object, where the pieces are being moved on.
	 * @param init This is the initial location of the piece.
	 * @param to This is the final location of the piece.
	 * @param x This is a char, which is used for indicating what piece that pawn should be promoted to.
	 * @param isCopy 
	 * @return boolean Returns true or false based on whether the player has successfully moved the piece or not.
	 */
	public boolean move(Board b, Location init, Location to, char x, boolean isCopy){
		if(b.board[init.getY()][init.convertX()] == null){
			return false;
		}
		boolean canMove = false;
		Piece curr = b.board[init.getY()][init.convertX()];
		if (b.board[to.getY()][to.convertX()]!=null)	//pi
		{
			Piece p = b.board[to.getY()][to.convertX()];
			canMove = curr.moveTo(to,b);
			if (!(p.getColor().equals(color)) && canMove)
			{
				b.board[to.getY()][to.convertX()]=curr;
				Location newLoc = new Location(to.getX(),to.getY());		//changing location of piece
				curr.setLocation(newLoc);
				b.board[init.getY()][init.convertX()]=null;
				if(b.board[to.getY()][to.convertX()] instanceof Pawn){
					Pawn pawn = (Pawn)b.board[to.getY()][to.convertX()];
					pawn.numMoves = pawn.numMoves + 1;
				}
				Location opposKingsLoc = curr.getKingLocation(this.color, b);
				if (curr.canMove(opposKingsLoc, b))
				{
					curr.check = true;
					b.check = true;
				}
				else
				{
					curr.check = false;
					b.check = false;
					Piece checkPiece = curr.getCheckPiece(b);
					if(checkPiece != null){
						checkPiece.check = false;
					}
				}
				if((to.getY() == 7 || to.getY() == 0) && curr instanceof Pawn){
					switch(x){
					case 'B':
						color = b.board[to.getY()][to.convertX()].getColor();
						b.board[to.getY()][to.convertX()] = new Bishop(to, color);
						break;
					case 'N':
						color = b.board[to.getY()][to.convertX()].getColor();
						b.board[to.getY()][to.convertX()] = new Knight(to, color);
						break;
					case 'R':
						color = b.board[to.getY()][to.convertX()].getColor();
						b.board[to.getY()][to.convertX()] = new Rook(to, color);
						break;
					default:
						color = b.board[to.getY()][to.convertX()].getColor();
						b.board[to.getY()][to.convertX()] = new Queen(to, color);
						break;
					}
				}
				b.prev = curr;
				return true;
				}	
			else{
				return false;
			}
		}
		if (curr.moveTo(to,b))
		{
			if (curr.isCastling == true)
			{
				//System.out.println("is castling");
				if (to.getX()=='g' && to.getY() == 0)
				{
				b.board[0][5] = new Rook(new Location('f',0),"white");	//changing rook's position
				b.board[0][7] = null;
				}
				if (to.getX()=='b' && to.getY() == 0)
				{
					b.board[0][2] = new Rook(new Location('c',0),"white");
					b.board[0][0] = null;
				}
				if (to.getX()=='g' && to.getY() == 7)
				{
					b.board[7][5] = new Rook(new Location('f',7),"black");
					b.board[7][7] = null;
				}
				if (to.getX()=='b' && to.getY() == 7)
				{
					b.board[7][2] = new Rook(new Location('c',7),"black");
					b.board[7][0] = null;
				}
			}
			b.board[to.getY()][to.convertX()]=curr;
			Location newLoc = new Location(to.getX(),to.getY());		//changing location of piece
			curr.setLocation(newLoc);
			b.board[init.getY()][init.convertX()]=null;
			Location opposKingsLoc = curr.getKingLocation(this.color, b);
			if (curr.canMove(opposKingsLoc, b))
			{
				curr.check = true;
				b.check = true;
			}
			else {
				curr.check = false;
				b.check = false;
				Piece checkPiece = curr.getCheckPiece(b);
				if(checkPiece != null){
					checkPiece.check = false;
				}
			}
			if((to.getY() == 7 || to.getY() == 0) && curr instanceof Pawn){
				switch(x){
				case 'B':
					color = b.board[to.getY()][to.convertX()].getColor();
					b.board[to.getY()][to.convertX()] = new Bishop(to, color);
					break;
				case 'N':
					color = b.board[to.getY()][to.convertX()].getColor();
					b.board[to.getY()][to.convertX()] = new Knight(to, color);
					break;
				case 'R':
					color = b.board[to.getY()][to.convertX()].getColor();
					b.board[to.getY()][to.convertX()] = new Rook(to, color);
					break;
				default:
					color = b.board[to.getY()][to.convertX()].getColor();
					b.board[to.getY()][to.convertX()] = new Queen(to, color);
					break;
				}
			}
			b.prev = curr;
			return true;
		}
		return false;
	}
	/**
	 * This method indicates whether or not it is a checkmate based on whether the king has any valid moves or not.
	 * @param b Board object, which is used to traverse through the board to see if king has any valid moves.
	 * @return boolean Returns true/false indicating whether it is a checkmate or not.
	 */
	public boolean isCheckMate(Board b)
	{
		if (!b.check)
		{
			//System.out.println("no");
			return false;
		}
		Piece[][] pieces = b.board; 
		Piece king = null;
		for (int i = 0; i < pieces.length; i++)
		{
			for (int j = 0; j<pieces[0].length;j++)
			{
				if (pieces[i][j] != null && this.color.equals("black"))
				{
				if (pieces[i][j].toString().equals("wK"))
				{
					king = pieces[i][j];
				}
				}
				else if (pieces[i][j] != null && this.color.equals("white"))
				{
					if (pieces[i][j].toString().equals("bK"))
					{
						king = pieces[i][j];
					}
				}
			}
		}
		Piece myKing = null;
		for (int i = 0; i < pieces.length; i++)
		{
			for (int j = 0; j<pieces[0].length;j++)
			{
				if (pieces[i][j] != null && this.color.equals("black"))
				{
				if (pieces[i][j].toString().equals("bK"))
				{
					myKing = pieces[i][j];
				}
				}
				else if (pieces[i][j] != null && this.color.equals("white"))
				{
					if (pieces[i][j].toString().equals("wK"))
					{
						myKing = pieces[i][j];
					}
				}
			}
		}
		if(king.getCheckPiece(b).getColor().equals(this.color))
			return false;
		//Location kingsLoc = king.getLocation();
		for(int i = 0;i < b.board.length;i++){
			for(int j = 0;j < b.board[i].length;j++){
				Piece p = b.board[i][j];
				if(p != null && p.getColor().equals(this.color)){
					Piece cp = p.getCheckPiece(b);
					for(int x = 0; x < b.board.length;x++){
						for(int y = 0;y < b.board[x].length;y++){
							if(p.toString().equals("bK") || p.toString().equals("wK")){
								break;
							}
							Board copy = b.boardcopy();
							Location location = new Location((char)('a'+j),i);
							Location newLoc = new Location((char)('a'+y),x);
							boolean bl = this.move(copy, location, newLoc, 'Q', true); 
							if(bl){
								Piece checkPiece = p.getCheckPiece(copy);
								if (checkPiece==null)
								{
									b.board[i][j].setLocation(location);
									cp.check = true;
									b.check = true;
									return false;
								}
							}
							cp.check = true;
							b.check = true;
							b.board[i][j].setLocation(location);
						}
					}
				}
			}
		}
		Location mykingloc = myKing.getLocation();
		for (int i = mykingloc.getY()-1; i< mykingloc.getY()+2; i++)
		{
			for (int j = mykingloc.convertX()-1; j<mykingloc.convertX()+2;j++)
			{
				Location loc = new Location((char)('a'+j),i);
				Board copy = b.boardcopy();
				
				if (i >=0 && i<=7 && j>=0 && j<=7 && myKing.canMove(loc, copy)/*this.move(copy, mykingloc, loc, 'Q', true)*/)
				{
					boolean f = true;
					for(int x = 0;x < copy.board.length;x++){
						for(int y = 0;y < copy.board[x].length;y++){
							Piece p = copy.board[x][y];
							if(p != null && !p.getColor().equals(this.color) && p.canMove(loc, copy)/*&& p.moveTo(mykingloc, copy)*/){
								f = false;
								break;
							}
						}
						if(!f)
							break;
					}
					if(f)
						return false;
				}
					myKing.setLocation(mykingloc);
			}
		}
			
		return true;
	}
	/**
	 * This method checks if a stalemate is present.
	 * @param b is the Board.
	 * @return boolean indicates if there is a stalemate.
	 */
	public boolean isStalemate(Board b){
		if(b.check)
			return false;
		Piece[][] pieces = b.board; 
		Piece myKing = null;
		for (int i = 0; i < pieces.length; i++)
		{
			for (int j = 0; j<pieces[0].length;j++)
			{
				if (pieces[i][j] != null && this.color.equals("black"))
				{
				if (pieces[i][j].toString().equals("bK"))
				{
					myKing = pieces[i][j];
				}
				}
				else if (pieces[i][j] != null && this.color.equals("white"))
				{
					if (pieces[i][j].toString().equals("wK"))
					{
						myKing = pieces[i][j];
					}
				}
			}
		}
		for(int i = 0;i < pieces.length;i++){
			for(int j = 0;j < pieces[i].length;j++){
				//Location location = new Location((char)('a'+j), i);
				Piece p = pieces[i][j];
				if(p != null && p.getColor().equals(this.color)){
					for(int x = 0;x < b.board.length;x++){
						for(int y = 0;y < b.board[x].length;y++){
							Board copy = b.boardcopy();
							Location newLoc = new Location((char)('a'+y), x);
							if(p.canMove(newLoc, copy) && !newLoc.equals(myKing.getLocation())){
							if(b.board[x][y] == null || (b.board[x][y] != null && !p.getColor().equals(b.board[x][y].getColor()))){
								Location loc = myKing.getLocation();
								if(p.getLocation().equals(myKing.getLocation())){
									loc = newLoc;
								}
								copy.board[x][y] = p;
							copy.board[i][j] = null;
							boolean f = true;
							for(int k = 0;k < copy.board.length;k++){
								for(int l = 0;l < copy.board[k].length;l++){
									Piece enemy = copy.board[k][l];
									if(enemy != null && !enemy.getColor().equals(this.color) && enemy.canMove(/*myKing.getLocation()*/loc, copy)){
										f = false;
										break;
									}
								}
								if(!f)
									break;
							}
							if(f){
								return false;
							}
							}
							}
						}
					}
				}
			}
		}
		return true;
	}
}
