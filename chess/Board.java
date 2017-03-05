package chess;

public class Board {
	Piece[][] board; 
	public Board(){
		board = new Piece[8][8];
		init();
	}
	public void init(){
		board[7][0] = new Rook(new Location('a', 7), "black");
		board[7][7] = new Rook(new Location('h', 7), "black");
		board[7][1] = new Knight(new Location('b',7), "black");
		board[7][6] = new Knight(new Location('g',7), "black");
		board[7][2] = new Bishop(new Location('c',7), "black");
		board[7][5] = new Bishop(new Location('f',7), "black");
		for(int c = 0;c < board[6].length;c++){
			board[6][c] = new Pawn(new Location((char)('a'+ c), 6), "black");
		}
		for(int c = 0;c < board[1].length;c++){
			board[1][c] = new Pawn(new Location((char)('a'+ c), 1), "white");
		}
		board[0][0] = new Rook(new Location('a', 0), "white");
		board[0][7] = new Rook(new Location('h', 0), "white");
		board[0][1] = new Knight(new Location('b', 0), "white");
		board[0][6] = new Knight(new Location('g', 0), "white");
		board[0][2] = new Bishop(new Location('c',0),"white");
		board[0][5] = new Bishop(new Location('f',0),"white");
	}
	public void draw(){
		for(int r = board.length - 1;r >= 0;r--){
			for(int c = 0;c < board[r].length;c++){
				if(board[r][c] != null)
					System.out.print(board[r][c] + " ");
				else{
					if((r + c) % 2 == 0)
						System.out.print("## ");
					else
						System.out.print("   ");
					//System.out.println();
				}
			}
			System.out.println(r + 1);
		}
		for(int i = 0;i < 8;i++){
			System.out.print(" " + (char)('a' + i) + " ");
		}
		System.out.println();
	}
	
}
