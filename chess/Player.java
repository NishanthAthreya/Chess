package chess;

import java.util.Scanner;

public class Player {
	private String color;
	public Player(String color){
		this.color = color;
	}
	public boolean move(Board b, Location init, Location to){
		boolean canMove = false;
		Piece curr = b.board[init.getY()][init.convertX()];
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		if (b.board[to.getY()][to.convertX()]!=null)	//pi
		{
			Piece p = b.board[to.getY()][to.convertX()];
			canMove = curr.moveTo(to,b);
			if (!(p.getColor().equals(color)) && canMove)
			{
				b.board[to.getY()][to.convertX()]=curr;
				b.board[init.getY()][init.convertX()]=null;
				//curr.moveTo(to);
				if(to.getY() == 7){
					System.out.println("Please enter what piece you want your pawn to"
							+ "transform into");
					String x = keyboard.nextLine().toUpperCase();
					String color;
					switch(x){
					case "BISHOP":
						color = b.board[to.getY()][to.convertX()].getColor();
						b.board[to.getY()][to.convertX()] = new Bishop(to, color);
						break;
					case "KNIGHT":
						color = b.board[to.getY()][to.convertX()].getColor();
						b.board[to.getY()][to.convertX()] = new Knight(to, color);
						break;
					case "ROOK":
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
			b.board[init.getY()][init.convertX()]=null;
		//curr.moveTo(to);
			if(to.getY() == 7){
				System.out.println("Please enter what piece you want your pawn to"
						+ "transform into");
				String x = keyboard.nextLine().toUpperCase();
				String color;
				switch(x){
				case "BISHOP":
					color = b.board[to.getY()][to.convertX()].getColor();
					b.board[to.getY()][to.convertX()] = new Bishop(to, color);
					break;
				case "KNIGHT":
					color = b.board[to.getY()][to.convertX()].getColor();
					b.board[to.getY()][to.convertX()] = new Knight(to, color);
					break;
				case "ROOK":
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
		return false;
	}
}
