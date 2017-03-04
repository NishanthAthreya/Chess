package chess;

public class Player {
	private String color;
	public Player(String color){
		this.color = color;
	}
	public boolean move(Board b, Location init, Location to){
		Piece curr = b.board[init.getY()][init.convertX()];
		if (curr.moveTo(to))
		{
			b.board[to.getY()][to.convertX()]=curr;
			b.board[init.getY()][init.convertX()]=null;
		//curr.moveTo(to);
		return true;
		}
		return false;
	}
}
