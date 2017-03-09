package chess;

public class Player {
	private String color;
	public Player(String color){
		this.color = color;
	}
	public boolean move(Board b, Location init, Location to){
		boolean canMove = false;
		Piece curr = b.board[init.getY()][init.convertX()];
		if (b.board[to.getY()][to.convertX()]!=null)	//pi
		{
			Piece p = b.board[to.getY()][to.convertX()];
			canMove = curr.moveTo(to,b);
			if (!(p.getColor().equals(color)) && canMove)
			{
				b.board[to.getY()][to.convertX()]=curr;
				b.board[init.getY()][init.convertX()]=null;
				//curr.moveTo(to);
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
			b.board[init.getY()][init.convertX()]=null;
		//curr.moveTo(to);
			return true;
		}
		return false;
	}
}
