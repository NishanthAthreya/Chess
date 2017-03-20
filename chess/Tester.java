package chess;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Board b = new Board();
		b.draw();
		//System.out.println();
		//b.draw2();
		int turn = 1;
		//int i = 0;
		Player one = new Player("white");
		Player two = new Player("black");
		Scanner scan = new Scanner(System.in);
		while (!one.isCheckMate(b) && !two.isCheckMate(b))	//until game ends
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
					if(movingto.getY() == 7){
						c = s.charAt(6);
					}
				}
				flag = one.move(b,current,movingto, c);
				b.draw();
				//System.out.println();
				//b.draw2();
			}
			turn++;
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
					if(movingto.getY() == 7){
						c = s.charAt(6);
					}
				}
				flag = two.move(b, current, movingto, c);
				b.draw();
				//System.out.println();
			//	b.draw2();
			}
			turn++;
		}
		//i++;
	}
		if(one.isCheckMate(b)){
			System.out.println();
			System.out.println("White wins");
		}else{
			System.out.println();
			System.out.println("Black wins");
		}
		scan.close();
	}
}
