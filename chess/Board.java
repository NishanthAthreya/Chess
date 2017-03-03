package chess;

public class Board {
	Piece[][] board; 
	public Board(){
		board = new Piece[8][8];
	}
	public void draw(){
		for(int r = 0;r < board.length;r++){
			for(int c = 0;c < board[r].length;c++){
				if(board[r][c] != null)
					System.out.print(board[r][c]);
				else{
					if((r + c) % 2 == 0)
						System.out.print("##");
					else
						System.out.print("  ");
					System.out.println();
				}
			}
			System.out.println();
		}
	}
}
