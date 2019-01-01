package ModelPack;

import java.util.Random;

public class Model2
{
	private int height;
	private int width;
	private int numberOfPieces = 5;
	private int[][] chessboard;
	private int[][] points;
	
	Model2(int height, int width)
	{
		this.height = height;
		this.width = width;
		chessboard = new int[height][width];
		points = new int[numberOfPieces][2];
		generatePoints();
		placeQueen(points[0][0], points[0][1]);
		placeKing(points[1][0], points[1][1]);
		placeRook(points[2][0], points[2][1]);
		placeBishop(points[3][0], points[3][1]);
		placeKnight(points[4][0], points[4][1]);
		
		//placeQueen(2, 1);
		//placeKing(0, 6);
		//placeRook(0, 1);
		//placeBishop(1, 6);
		//placeKnight(7, 6);
		//placeRook(2, 5);
		
	}


	public void displayBoard()
	{
		displayTopGrid();
		displayLetterRow();
		
		displayMidGrid();

		for(int i = 0; i < height; i++)
		{
			displayRow(i);
			displayMidGrid();
		}
		displayLetterRow();
		displayLowGrid();
	}
	
	private void displayRow(int y)
	{
		System.out.print("\u2503 " + (char)('A' + y) + " \u2503");
		for(int x = 0; x < width - 1; x++)
		{
			displayTile(y, x);
			System.out.print("\u2503");
		}
		displayTile(y, width - 1);
		System.out.print("\u2503 " + (char)('A' + y) + " \u2503");
		System.out.print("\n");
	}

	private void displayTile(int y, int x)
	{
		int value = chessboard[y][x];
		if(value == 3)
		{
			System.out.print(" 3 ");
			return;
		}
		if(value < 10)
		{
			System.out.print("   ");
			return;
		}
		if(value > 50)
		{
			System.out.print(" Q ");
			return;
		}
		if(value > 40)
		{
			System.out.print(" K ");
			return;
		}
		if(value > 30)
		{
			System.out.print(" R ");
			return;
		}
		if(value > 20)
		{
			System.out.print(" B ");
			return;
		}
		if(value > 10)
		{
			System.out.print(" N ");
			return;
		}
		System.out.print(" ? ");
		return;
	}
	
	
	public void displayTopGrid()
	{
		System.out.print("\u250C");
		for(int i = 0; i < chessboard.length + 1; i++)
		{
			System.out.print("---\u252C");
		}
		System.out.print("---\u2510\n");
	}
	
	public void displayMidGrid()
	{
		System.out.print("\u251C");
		for(int i = 0; i < chessboard.length + 1; i++)
		{
			System.out.print("---\u253C");
		}
		System.out.print("---\u2524\n");
	}
	
	public void displayLowGrid()
	{
		System.out.print("\u2514");
		for(int i = 0; i < chessboard.length + 1; i++)
		{
			System.out.print("---\u2534");
		}
		System.out.print("---\u2518\n");
	}
	
	public void displayLetterRow()
	{
		System.out.print("\u2503   \u2503");
		
		for(char i = 'A'; i < 'A' + width; i++)
		{
			System.out.print(" " + i +" \u2503");
		}
		System.out.print("   \u2503\n");
		
		
		
	}
	
	
	public void placeQueen(int y, int x)
	{
		placeQueen(y, x, true);
	}
	public void removeQueen(int y, int x)
	{
		placeQueen(y, x, false);
	}
	public void placeKing(int y, int x)
	{
		placeKing(y, x, true);
	}
	public void removeKing(int y, int x)
	{
		placeKing(y, x, false);
	}
	public void placeRook(int y, int x)
	{
		placeRook(y, x, true);
	}
	public void removeRook(int y, int x)
	{
		placeRook(y, x, false);
	}
	public void placeBishop(int y, int x)
	{
		placeBishop(y, x, true);
	}
	public void removeBishop(int y, int x)
	{
		placeBishop(y, x, false);
	}
	public void placeKnight(int y, int x)
	{
		placeKnight(y, x, true);
	}
	public void removeKnight(int y, int x)
	{
		placeKnight(y, x, false);
	}
	
	
	public void placeKing(int y, int x, boolean bool)
	{
		int val;
		if(bool)
			val = 1;
		else
			val = -1;
		if(chessboard[y][x] < 10)
		{
			chessboard[y][x] += 41 * val;
			incTile(y-1, x-1, val);
			incTile(y-1,    x, val);
			incTile(y-1, x+1, val);
			incTile(   y,   x-1, val);
			incTile(   y,   x+1, val);
			incTile(y+1, x-1, val);
			incTile(y+1,    x, val);
			incTile(y+1, x+1, val);
		}
	}
	
	public void placeQueen(int y, int x, boolean bool)
	{
		int val;
		if(bool)
			val = 1;
		else
			val = -1;
		if(chessboard[y][x] < 10)
		{
			chessboard[y][x] += 51 * val;
			for(int a = 1; a < 8; a++)
				{
					incTile(y-a,    x, val);
					incTile(y+a,    x, val);
					incTile(   y,   x-a, val);
					incTile(   y,   x+a, val);
					incTile(y-a, x-a, val);
					incTile(y-a, x+a, val);
					incTile(y+a, x-a, val);
					incTile(y+a, x+a, val);
				}
		}
	}
	
	public void placeRook(int y, int x, boolean bool)
	{
		int val;
		if(bool)
			val = 1;
		else
			val = -1;
		if(chessboard[y][x] < 10)
		{
			chessboard[y][x] += 31 * val;
			for(int a = 1; a < 8; a++)
			{
				incTile(y-a,    x, val);
				incTile(y+a,    x, val);
				incTile(   y,   x-a, val);
				incTile(   y,   x+a, val);
			}
		}
	}
	
	public void placeBishop(int y, int x, boolean bool)
	{
		int val;
		if(bool)
			val = 1;
		else
			val = -1;
		if(chessboard[y][x] < 10)
		{
			chessboard[y][x] += 21 * val;
			for(int a = 1; a < 8; a++)
			{
				incTile(y-a, x-a, val);
				incTile(y-a, x+a, val);
				incTile(y+a, x-a, val);
				incTile(y+a, x+a, val);
			}
		}
	}
	
	public void placeKnight(int y, int x, boolean bool)
	{
		int val;
		if(bool)
			val = 1;
		else
			val = -1;
		if(chessboard[y][x] < 10)
		{
			chessboard[y][x] += 11 * val;
			
			incTile(y - 2, x - 1, val);
			incTile(y - 2, x + 1, val);
			incTile(y + 2, x - 1, val);
			incTile(y + 2, x + 1, val);
			incTile(y - 1, x - 2, val);
			incTile(y - 1, x + 2, val);
			incTile(y + 1, x - 2, val);
			incTile(y + 1, x + 2, val);
			
		}
	}
	
	private void incTile(int y, int x, int val)
	{
		try
		{
			chessboard[y][x] += val;
		}
		catch(IndexOutOfBoundsException ioobe){}
	}
	
	private void generatePoints()
	{
		Random rng = new Random();
		for(int i=0; i < numberOfPieces; i++)
		{
			int y, x;
			boolean distinct;
			do
			{
				distinct = true;
				points[i][0] = rng.nextInt(height);
				points[i][1] = rng.nextInt(width);
				System.out.println("Coordinates nr " + i + ": " + points[i][0] + ", " + points[i][1]);
				for(int j = 0; j < i; j++)
				{
					if(points[i][0] == points[j][0] && points[i][1] == points[j][1])
						distinct = false;
				}
			}
			while(!distinct);
		}
	
	}
	
	
	
	public static void main(String[] args)
	{
		Model2 m = new Model2(8, 8);
		m.displayBoard();
	}

}
