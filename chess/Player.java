package chess;

public class Player {
	private String color;
	public Player(String color){
		this.color = color;
	}
	public boolean move(Board b, Location init, Location to, char x){
		if(b.board[init.getY()][init.convertX()] == null){
			System.out.println("Invalid move, try again");
			System.out.println();
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
				//
				Location opposKingsLoc = curr.getKingLocation(this.color, b);
				if (curr.canMove(opposKingsLoc, b))
				{
					curr.check = true;
					b.check = true;
					System.out.println();
					System.out.println("Check");
					System.out.println();
				}
				else
				{
					curr.check = false;
					b.check = false;
				}
				//curr.moveTo(to);
				if(to.getY() == 7 || to.getY() == 0){
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
				if(canMove){
					System.out.println("Illegal move, try again");
					System.out.println();
				}
				return false;
			}
		}
		if (curr.moveTo(to,b))
		{
			b.board[to.getY()][to.convertX()]=curr;
			Location newLoc = new Location(to.getX(),to.getY());		//changing location of piece
			curr.setLocation(newLoc);
			b.board[init.getY()][init.convertX()]=null;
			//
			Location opposKingsLoc = curr.getKingLocation(this.color, b);
			if (curr.canMove(opposKingsLoc, b))
			{
				curr.check = true;
				b.check = true;
				System.out.println();
				System.out.println("Check");
				System.out.println();
			}
			else {
				curr.check = false;
				b.check = false;
			}
		//curr.moveTo(to);
			if(to.getY() == 7 || to.getY() == 0){
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
		System.out.println();
		System.out.println("Invalid move, try again");
		System.out.println();
		return false;
	}
	public boolean isCheckMate(Board b)
	{
		if (!b.check)
		{
			return false;
		}
		Piece[][] pieces = b.board; 
		Piece king = null;
		for (int i = 0; i < pieces.length; i++)
		{
			for (int j = 0; j<pieces[0].length;j++)
			{
				if (this.color.equals("black"))
				{
				if (pieces[i][j].equals("wK"))
				{
					king = pieces[i][j];
				}
				}
				else if (this.color.equals("white"))
				{
					if (pieces[i][j].equals("bK"))
					{
						king = pieces[i][j];
					}
				}
			}
		}
		Location kingsLoc = king.getLocation();
		for (int i = kingsLoc.getY()-1; i< kingsLoc.getY()+2; i++)
		{
			for (int j = kingsLoc.convertX()-1; j<kingsLoc.convertX()+2;j++)
			{
				Location loc = new Location((char)('a'+i),j);
				if (i >=0 && i<=7 && j>=0 && j<=7 && king.canMove(loc, b))
				{
					for (int x = 0; x< b.board.length; x++)
					{
						for (int y = 0; y<b.board[0].length;y++)
						{
							if(!(b.board[x][y].canMove(loc, b)))
							{
								return false;			//not a checkmate, king can move there
							}
						}
					}
				}
			}
		}
			
		return true;
	}
}
