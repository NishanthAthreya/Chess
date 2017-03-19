package chess;

public class Location {
	private char x;
	private int y;
	public Location(char x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public char getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
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
