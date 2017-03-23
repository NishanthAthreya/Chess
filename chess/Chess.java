package chess;

import java.util.Scanner;
/**
 * This is the main class for the chess application.
 * @author Pranav Kanukollu, pvk9
 * @author Nishanth Athreya, nsa48
 *
 */
public class Chess {
	/**
	 * This is the main method for the chess application which creates a board object, and two player objects. 
	 * The game continues until one of the players resigns, one of the players asks for a draw, or if there is a checkmate or stalemate.
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board();
		b.draw();
		int turn = 1;
		Player one = new Player("white");
		Player two = new Player("black");
		Scanner scan = new Scanner(System.in);
		boolean f = false;
		while (!f)	//until game ends
		{
		char c = ' ';
		if (turn%2!=0)
		{
			boolean flag = false;
			while (!flag)	//keeps executing until valid move is applied
			{
				System.out.println();
				System.out.print("White's move: ");
				System.out.println();
				String s = "";
				while(s.length() < 5 || s.length() > 5){
					s = scan.nextLine();
					if(s.equalsIgnoreCase("resign")){
						System.out.println();
						System.out.println("Black wins");
						System.exit(0);
					}
					else if(s.contains("draw?")){
						s = scan.nextLine();
						if(s.equals("draw"))
							System.exit(0);
						else
							s = "notdraw";		
					}
					//
					if (s.endsWith("N") || s.endsWith("B") || s.endsWith("R"))
					{
						break;
					}
					if((s.length() < 5 || s.length() > 5) && !(s.equals("notdraw"))){
						System.out.println();
						System.out.println("Illegal move, try again");
						System.out.println();
						System.out.print("White's move: ");
						System.out.println();
					}
				}
				Location current = new Location(s.charAt(0),Integer.parseInt(s.charAt(1)+"")-1);
				Location movingto = new Location(s.charAt(3),Integer.parseInt(s.charAt(4)+"")-1);
				//char c = ' ';
				if(s.length() > 6){
					if(movingto.getY() == 7 || movingto.getY() == 0){
						c = s.charAt(6);
					}
				}
				flag = one.move(b,current,movingto, c, false);
				if(!flag){
					System.out.println();
					System.out.println("Illegal move, try again");
					//System.out.println();
				}
			}
			turn++;
			f = two.isCheckMate(b);
			if(flag && !f){
				if(b.check){
					System.out.println();
					System.out.println("Check");
					System.out.println();
				}
				b.draw();
			}
			if(!f){
				if(one.isStalemate(b) || two.isStalemate(b)){
					System.out.println();
					System.out.println("Stalemate");
					System.out.println();
					System.exit(0);
				}
			}
		}
		else{
			boolean flag = false;
			while (!flag)
			{
				System.out.println();
				System.out.print("Black's move: ");
				System.out.println();
				String s = "";
				while(s.length() < 5 || s.length() > 5){
					s = scan.nextLine();
					if(s.equalsIgnoreCase("resign")){
						System.out.println();
						System.out.println("White wins");
						System.exit(0);
					}
					else if(s.contains("draw?")){
						s = scan.nextLine();
						if(s.equals("draw"))
							System.exit(0);
						else
							s = "notdraw";
							
					}
					//
					if (s.endsWith("N") || s.endsWith("B") || s.endsWith("R"))
					{
						break;
					}
					if((s.length() < 5 || s.length() > 5) && !(s.equals("notdraw"))){
						System.out.println();
						System.out.println("Illegal move, try again");
						System.out.println();
						System.out.print("Black's move: ");
						System.out.println();
					}
				}
				Location current = new Location(s.charAt(0),Integer.parseInt(s.charAt(1)+"")-1);
				Location movingto = new Location(s.charAt(3),Integer.parseInt(s.charAt(4)+"")-1);
				if(s.length() > 6){
					if(movingto.getY() == 7 || movingto.getY()==0){
						c = s.charAt(6);
					}
				}
				flag = two.move(b, current, movingto, c, false);
				if(!flag){
					System.out.println();
					System.out.println("Illegal move, try again");
					//System.out.println();
				}
			}
			turn++;
			f = one.isCheckMate(b);
			if(flag && !f){
				if(b.check){
					System.out.println();
					System.out.println("Check");
					System.out.println();
				}
				b.draw();
			}
			if(!f){
				if(two.isStalemate(b) || one.isStalemate(b)){
					System.out.println();
					System.out.println("Stalemate");
					System.out.println();
					System.exit(0);
				}
			}
		}
	}
		if(turn % 2 == 0){
			System.out.println();
			System.out.println("Checkmate");
			System.out.println("White wins");
		}else{
			System.out.println();
			System.out.println("Checkmate");
			System.out.println("Black wins");
		}
		scan.close();
	}
}
