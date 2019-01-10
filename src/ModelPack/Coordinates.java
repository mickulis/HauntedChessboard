package ModelPack;

import java.io.Serializable;

public class Coordinates implements Serializable
{
	int x, y;
	
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
		if(x != point.x || y != point.y)
			return false;
		return true;
	}
}
