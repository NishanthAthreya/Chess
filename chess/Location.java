package chess;
/**
 * This class keeps represents the location of a piece. 
 * 
 * @author Pranav Kanukollu, pvk9		
 * @author Nishanth Athreya, nsa48
 */
public class Location {
	private char x;
	private int y;
	/**
	 * Constructor.
	 * 
	 * @param x is the column of the board represented as a letter.
	 * @param y is the row of the board represented by numbers 1-8.
	 */
	public Location(char x, int y)
	{
		this.x = x;
		this.y = y;
	}
	/**
	 * This method returns the current column the piece is located in.
	 * @return char Return type is a char.
	 */
	public char getX()
	{
		return x;
	}
	/**
	 * This method returns the row the piece is located in.
	 * @return int Return type is an int.
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * This method returns the numerical value of the char representing the column the piece is located in.
	 * @return int Return type is int.
	 */
	public int convertX()
	{
		switch (x)
		{
			case 'a':
				return 0;
			case 'b':
				return 1;
			case 'c':
				return 2;
			case 'd':
				return 3;
			case 'e':
				return 4;
			case 'f':
				return 5;
			case 'g':
				return 6;
			case 'h':
				return 7;
		}
		return 0;
	}
	/**
	 * This method checks if two locations have the same values.
	 * @param o is the Object to be compared to.
	 * @return boolean represents whether or not the location classes are equal.
	 */
	public boolean equals(Object o){
		if(!(o instanceof Location))
			return false;
		else if(this == null || o == null)
			return false;
		else{
			Location l = (Location)o;
			if(this.getY() == l.getY() && this.convertX() == l.convertX())
				return true;
		}
		return false;
	}
}
