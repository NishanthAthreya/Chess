package chess;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Board b = new Board();
		b.draw();
		int turn = 1;
		int i =0;
		Player one = new Player("white");
		Player two = new Player("black");
		Scanner scan = new Scanner(System.in);
		while (i<1000)	//until game ends
		{
		if (turn%2!=0)
		{
			boolean flag = false;
			while (!flag)	//keeps executing until valid move is applied
			{
			System.out.println();
			System.out.println("White's move: ");
			String s = scan.nextLine();
			Location current = new Location(s.charAt(0),Integer.parseInt(s.charAt(1)+"")-1);
			Location movingto = new Location(s.charAt(3),Integer.parseInt(s.charAt(4)+"")-1);
			flag = one.move(b,current,movingto);
			turn++;
			b.draw();
			}
		}
		else{
			boolean flag = false;
			while (!flag)
			{
			System.out.println();
			System.out.println("Black's move: ");
			String s = scan.nextLine();
			Location current = new Location(s.charAt(0),Integer.parseInt(s.charAt(1)+"")-1);
			Location movingto = new Location(s.charAt(3),Integer.parseInt(s.charAt(4)+"")-1);
			flag = two.move(b, current, movingto);
			turn++;
			b.draw();
			}
		}
		i++;
	}
		scan.close();
	}
}
