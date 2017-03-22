package chess;

public class Player {
	private String color;
	public Player(String color){
		this.color = color;
	}
	public boolean move(Board b, Location init, Location to, char x, boolean isCopy){
		if(b.board[init.getY()][init.convertX()] == null){
			/*System.out.println("Illegal move, try again");
			System.out.println();*/
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
				//
				Location opposKingsLoc = curr.getKingLocation(this.color, b);
				/*System.out.println(curr);
				System.out.println(curr.getLocation().getX() + " " + curr.getLocation().getY());
				System.out.println(opposKingsLoc.getX() + " " + opposKingsLoc.getY());*/
				if (curr.canMove(opposKingsLoc, b))
				{
					curr.check = true;
					b.check = true;
					/*if(!isCopy){
						System.out.println();
						System.out.println("Check");
						System.out.println();
					}*/
				}
				else
				{
					//System.out.println("yes");
					curr.check = false;
					b.check = false;
					Piece checkPiece = curr.getCheckPiece(b);
					if(checkPiece != null){
						checkPiece.check = false;
					}
				}
				//curr.moveTo(to);
				if((to.getY() == 7 || to.getY() == 0) && curr instanceof Pawn){
					/*System.out.println("Please enter what piece you want your pawn to"
							+ "transform into");
					String x = keyboard.nextLine().toUpperCase();
					String color;*/
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
				return true;
				}	
			else{
				/*if(canMove){
					System.out.println("Illegal move, try again");
					System.out.println();
				}*/
				/*System.out.println();
				System.out.println("Illegal move, try again");
				System.out.println();*/
				/*System.out.println("player1");
				System.out.println("canMove: " + canMove);
				System.out.println("other: " + p.getColor().equals(color));*/
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
			//
			Location opposKingsLoc = curr.getKingLocation(this.color, b);
			/*System.out.println(curr);
			System.out.println(curr.getLocation().getX() + " " + curr.getLocation().getY());
			System.out.println(opposKingsLoc.getX() + " " + opposKingsLoc.getY());*/
			if (curr.canMove(opposKingsLoc, b))
			{
				curr.check = true;
				b.check = true;
				/*if(!isCopy){
					System.out.println();
					System.out.println("Check");
					System.out.println();
				}*/
			}
			else {
				//System.out.println("yes");
				curr.check = false;
				b.check = false;
				Piece checkPiece = curr.getCheckPiece(b);
				if(checkPiece != null){
					checkPiece.check = false;
				}
			}
		//curr.moveTo(to);
			//System.out.println(curr.toString());
			if((to.getY() == 7 || to.getY() == 0) && curr instanceof Pawn){
				/*System.out.println("Please enter what piece you want your pawn to"
						+ "transform into");
				String x = keyboard.nextLine().toUpperCase();
				String color;*/
				//System.out.println("entered instanceof");
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
			return true;
		}
		/*System.out.println();
		System.out.println("Illegal move, try again");
		System.out.println();*/
		//System.out.println("random");
		return false;
	}
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
							/*System.out.println(p);
							System.out.println(location.getX() + " " + location.getY());
							System.out.println(p.getLocation().getX() + " " + p.getLocation().getY());*/
							Location newLoc = new Location((char)('a'+y),x);
							//System.out.println(newLoc.getX() + " " + newLoc.getY());
							boolean bl = this.move(copy, location, newLoc, 'Q', true); 
							//System.out.println(bl);
							if(bl){
								Piece checkPiece = p.getCheckPiece(copy);
								if (checkPiece==null)
								{
									//System.out.println("here 1");
									b.board[i][j].setLocation(location);
									cp.check = true;
									b.check = true;
									return false;
								}/*else{
									System.out.println(checkPiece);
								}*/
							}
							//System.out.println("left");
							cp.check = true;
							b.check = true;
							b.board[i][j].setLocation(location);
							//copy.board[newLoc.getY()][newLoc.convertX()] = p;
							//copy.board[location.getY()][location.convertX()]=null;
							/*for(int r = 0;r < copy.board.length;r++){
								for(int c = 0; c < copy.board[i].length;c++){
									if(copy.board[r][c] != null && copy.board[r][c].getColor().equals(this.color)){
										Piece k = oppos
									}
								}
							}*/
							/*Piece checkPiece = p.getCheckPiece(copy);
							if (checkPiece==null)
							{
								System.out.println("here 1");
								return false;
							}*/
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
					/*System.out.println("here in if 2");
					if(!copy.check)
					{
						myKing.setLocation(mykingloc);
						//b.check = true;
						return false;
					} */
					/*for (int x = 0; x< b.board.length; x++)
					{
						for (int y = 0; y<b.board[0].length;y++)
						{
							if(b.board[x][y] != null && !(b.board[x][y].canMove(loc, b)) && !(b.board[x][y].getColor().equals(king.getColor())))	//have to check that it's opposite color!!
							{
								System.out.println("here 2");
								return false;			//not a checkmate, king can move there
							}
						}
					}*/
				}
					//b.check = true;
					myKing.setLocation(mykingloc);
				//king.setLocation(kingsLoc);
			}
		}
			
		return true;
	}
}
