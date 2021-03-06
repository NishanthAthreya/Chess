package chess;
/**
 * This class defines a chess board. This board keeps track of where each piece is located by storing all the pieces in
 * a 2D array. All pieces can be accessed via the board, so that information about each individual piece can be
 * retrieved when necessary.
 * 
 * @author Pranav Kanukollu, pvk9		
 * @author Nishanth Athreya, nsa48
 */
public class Board {
	Piece[][] board; 
	Location BlackKingsLocation;
	boolean check= false;
	Piece prev;
	/**
	 * Constructor.
	 */
	public Board(){
		board = new Piece[8][8];
		prev = null;
		init();
	}
	/**
	 * This method creates the pieces and populates the pieces in their appropriate locations so that the board
	 * is ready for the first move. All pieces are placed in their initial locations.
	 */
	public void init(){
		board[7][0] = new Rook(new Location('a', 7), "black");
		board[7][7] = new Rook(new Location('h', 7), "black");
		board[7][1] = new Knight(new Location('b',7), "black");
		board[7][6] = new Knight(new Location('g',7), "black");
		board[7][2] = new Bishop(new Location('c',7), "black");
		board[7][5] = new Bishop(new Location('f',7), "black");
		board[7][3] = new Queen(new Location('d',7), "black");
		board[7][4] = new King(new Location('e',7),"black");
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
		board[0][3] = new Queen(new Location('d', 0), "white");
		board[0][4] = new King(new Location('e', 0),"white");
	}
	/**
	 * This method prints out the chess board.
	 */
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
	/**
	 * This method creates a copy of this board.
	 * @return Board The method returns the copy of the board.
	 */
	public Board boardcopy(){
		Board copy = new Board();
		copy.prev = this.prev;
		copy.check = this.check;
		for(int i = 0;i < board.length;i++){
			for(int j = 0;j < board[i].length;j++){
				copy.board[i][j] = this.board[i][j];
			}
		}
		return copy;
	}
}
