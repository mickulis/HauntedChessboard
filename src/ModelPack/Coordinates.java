package ModelPack;

import java.io.Serializable;

public class Coordinates implements Serializable
{
	private int x, y;
	
	public Coordinates(int y, int x)
	{
		this.y = y;
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(Coordinates point)
	{
		return !(x != point.x || y != point.y);
		
	}
}
