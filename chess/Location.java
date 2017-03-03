package chess;

public class Location {
	private String x;
	private int y;
	public Location(String x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public String getX()
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
			case "a":
				return 1;
			case "b":
				return 2;
			case "c":
				return 3;
			case "d":
				return 4;
			case "e":
				return 5;
			case "f":
				return 6;
			case "g":
				return 7;
			case "h":
				return 8;
		}
		return 0;
	}
}
