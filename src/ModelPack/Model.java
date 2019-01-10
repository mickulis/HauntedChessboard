package ModelPack;

import EnumPack.CHESSPIECES;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Model implements Serializable
{
	private int minimalMarkedTiles = 3;
	private Random rng = new Random();
	private int height;
	private int width;
	private int[][] chessboard;
	private Coordinates[] points;
	private int[] initialPieceAvailability = {0, 1, 1, 1, 1, 1}; // for cloning later on
	private int[] currentPieceAvailability = {0, 1, 1, 1, 1, 1};	// check if pieces left: foreach if >0 return false
	private boolean[][] boardOfThrees;
	private ArrayList<String> backlog = new ArrayList<>();
	
	public Model()
	{
		this(8, 8);
	}
	
	public Model(int height, int width)
	{
		this.height = height;
		this.width = width;
		setup();
		
	}

	public void setup()
	{
		int numberOfMarkedTiles = 0;	// board with no 3's
		int iterator = 0;
		while(numberOfMarkedTiles < minimalMarkedTiles)
		{
			//region Initial state setup
			currentPieceAvailability = initialPieceAvailability.clone();
			chessboard = new int[height][width];
			int numberOfPieces = 0;
			for(int val: currentPieceAvailability)
				numberOfPieces += val;
			points = new Coordinates[numberOfPieces];
			generatePoints(numberOfPieces);
			for(int i = 0; i < numberOfPieces; i++)
				deployPiece(points[i], i+1);
			//endregion
			//region Win condition setup
			numberOfMarkedTiles = 0;
			boardOfThrees = new boolean[height][width];
			for (int i = 0; i < height; i++)			// check triviality with boolean method instead
			{
				for (int j = 0; j < width; j++)
				{
					if (getValue(i, j) == 3)
					{
						boardOfThrees[i][j] = true;
						System.out.println(i + " " + j + " - victory condition");
						numberOfMarkedTiles++;
					}
				}
			}
			//endregion
			
			if(numberOfMarkedTiles < minimalMarkedTiles)
				System.err.println("Trivial! Reroll!");
			
			if(iterator++ == 3000)	// failsafe if finding viable setup is very unlikely with set parameters
				throw new RuntimeException();
		}
		
		chessboard = new int[height][width];	// resetting board to an empty state
		currentPieceAvailability = initialPieceAvailability.clone();
		
		
		
	}
	
	
	//region Terminal display
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
		System.out.print("\u2503 " + (8 - y) + " \u2503");
		for(int x = 0; x < width - 1; x++)
		{
			displayTile(y, x);
			System.out.print("\u2503");
		}
		displayTile(y, width - 1);
		System.out.print("\u2503 " + (8 - y) + " \u2503");
		System.out.print("\n");
	}

	private void displayTile(int y, int x)
	{
		switch(chessboard[y][x])
		{
			case 1:
				System.out.print(" N "); return;
			case 2:
				System.out.print(" B "); return;
			case 3:
				System.out.print(" R "); return;
			case 4:
				System.out.print(" K "); return;
			case 5:
				System.out.print(" Q "); return;
		}
		System.out.print("   ");
	}
	
	
	private void displayTopGrid()
	{
		System.out.print("\u250C");
		for(int i = 0; i < chessboard.length + 1; i++)
		{
			System.out.print("---\u252C");
		}
		System.out.print("---\u2510\n");
	}
	
	private void displayMidGrid()
	{
		System.out.print("\u251C");
		for(int i = 0; i < chessboard.length + 1; i++)
		{
			System.out.print("---\u253C");
		}
		System.out.print("---\u2524\n");
	}
	
	private void displayLowGrid()
	{
		System.out.print("\u2514");
		for(int i = 0; i < chessboard.length + 1; i++)
		{
			System.out.print("---\u2534");
		}
		System.out.print("---\u2518\n");
	}
	
	private void displayLetterRow()
	{
		System.out.print("\u2503   \u2503");
		
		for(char i = 'A'; i < 'A' + width; i++)
		{
			System.out.print(" " + i +" \u2503");
		}
		System.out.print("   \u2503\n");
		
		
		
	}
	//endregion
	
	//region Pieces manipulation
	public void removePiece(Coordinates p)
	{
		if(!isOccupied(p))
			return;
		
		int piece = chessboard[p.getY()][p.getX()];
		//if(piece > 0)	// obsolete with isOccupied check above (maybe?)
		{
			currentPieceAvailability[piece]++;
		}
		chessboard[p.getY()][p.getX()] = 0;
		backlog.add("r " + p.getY() + " " + p.getX() + " " + piece);
		//displayBoard();
		
	}
	
	public void deployPiece(Coordinates p, int piece)
	{
		if(isOccupied(p))
			removePiece(p);
		//if(currentPieceAvailability[piece] > 0)
		{
			currentPieceAvailability[piece]--;
			chessboard[p.getY()][p.getX()] = piece;
		}
		
		backlog.add("p " + p.getY() + " " + p.getX() + " " + piece);
		
		//displayBoard();
		
	}
	
	public void deployPiece(Coordinates p, CHESSPIECES piece)
	{
		if(isOccupied(p))
			removePiece(p);
		int i = CHESSPIECES.getInteger(piece);
		if(i > 0)
		{
			//if(currentPieceAvailability[i] > 0)
			{
				currentPieceAvailability[i]--;
				chessboard[p.getY()][p.getX()] = i;
			}
			backlog.add("p " + p.getY() + " " + p.getX() + " " + piece);
			
			//displayBoard();
			
		}
		else	// remove
		{
			chessboard[p.getY()][p.getX()] = 0;
			//displayBoard();
			
		}
	}
	
	public boolean swap(Coordinates first, Coordinates second)
	{
		CHESSPIECES firstPiece = getPiece(first);
		chessboard[first.getY()][first.getX()] = CHESSPIECES.getInteger(getPiece(second));
		chessboard[second.getY()][second.getX()] = CHESSPIECES.getInteger(firstPiece);
		backlog.add("s " + first.getY() + " " + first.getX() + " " + second.getY() + " " + second.getX());
		
		//displayBoard();
		
		return true;
	}
	//endregion
	
	public void back()
	{
		if(backlog.size() == 0 )
			return;
		String lastMove = backlog.get(backlog.size() - 1);
		backlog.remove(backlog.size() - 1);
		
		reverseMove(lastMove);
		
		
	}
	
	public void solve()
	{
		currentPieceAvailability = initialPieceAvailability.clone();
		chessboard = new int[height][width];
		int numberOfPieces = 0;
		for(int val: currentPieceAvailability)
			numberOfPieces += val;
		for(int i = 0; i < numberOfPieces; i++)
			deployPiece(points[i], i+1);
	}
	
	private void reverseMove(String move)
	{
		switch(move.charAt(0))
		{
			case 'p': reversePlacing(move); break;
			case 'r': reverseRemoving(move); break;
			case 's': reverseSwapping(move); break;
		}
	}
	
	private void reversePlacing(String move)
	{
	
	}
	
	private void reverseRemoving(String move)
	{
	
	}
	
	private void reverseSwapping(String move)
	{
	
	}
	
	private void generatePoints(int numberOfPieces)
	{
		for(int i=0; i < numberOfPieces; i++)
		{
			int y, x;
			boolean distinct;
			do
			{
				distinct = true;
				points[i] = new Coordinates(rng.nextInt(height), rng.nextInt(width));
				System.out.println("Coordinates nr " + i + ": " + points[i].getY() + ", " + points[i].getX());
				for(int j = 0; j < i; j++)
				{
					if(points[i] == points[j])
						distinct = false;
				}
			}
			while(!distinct);
		}
	}
	
	private int PiecesLeft()
	{
		int pieces = 0;
		for(int i: currentPieceAvailability)
			pieces += i;
		//System.out.println(pieces);
		return pieces;
	}
	
	private int checkLine(int y, int x, int yMod, int xMod)
	{
		int tile;
		int i = 0;
		do
		{
			i++;
			tile = getTile(y + i * yMod, x + i * xMod);
		}
		while(tile == 0);
		
		if(tile == 5 || (tile == 4 & i == 1))	// King immediately next to the tile or Queen anywhere on the line
		{
			return 1;
		}
		if(yMod * xMod == 0 && tile == 3)	// Rook attacking
		{
				return 1;
		}
		if(yMod * xMod != 0 && tile == 2)	// Bishop attacking
		{
			return 1;
		}
		return 0;	// not attacked
	}
	
	private int checkKnights(int y, int x)
	{
		int value = 0;
		if(getTile(y-2, x-1) == 1)
			value++;
		if(getTile(y-2, x+1) == 1)
			value++;
		if(getTile(y-1, x-2) == 1)
			value++;
		if(getTile(y-1, x+2) == 1)
			value++;
		if(getTile(y+1, x-2) == 1)
			value++;
		if(getTile(y+1, x+2) == 1)
			value++;
		if(getTile(y+2, x-1) == 1)
			value++;
		if(getTile(y+2, x+1) == 1)
			value++;
		
		return value;
	}
	
	
	public boolean isDeployed(CHESSPIECES piece)
	{
		if(piece == CHESSPIECES.empty)
			return false;
		return (currentPieceAvailability[CHESSPIECES.getInteger(piece)] == 0);
	}
	
	public boolean isOccupied(Coordinates p)
	{
		return !(getPiece(p) == null);
	}
	
	
	
	public boolean isMarked(Coordinates p)
	{
		return boardOfThrees[p.getY()][p.getX()];
	}
	
	public boolean checkVictoryCondition()
	{
		System.out.println("Checking victory conditions");
		if(PiecesLeft() > 0)
			return false;
		for(int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if(boardOfThrees[i][j])
					System.out.println(getValue(i, j));
				if(getValue(i, j) == 3 && !boardOfThrees[i][j])
					return false;
				if(getValue(i, j) != 3 && boardOfThrees[i][j])
					return false;
			}
		}
		return true;
	}
	
	
	
	
	public CHESSPIECES getPiece(Coordinates p)
	{
		int value = getTile(p.getY(), p.getX());
		if(value > 0)
			return CHESSPIECES.values()[value];
		return null;
	}
	
	public int getValue(int y, int x)
	{
		int value = 0;
		
		value += checkLine(y, x, -1, -1);
		value += checkLine(y, x, -1, 0);
		value += checkLine(y, x, -1, 1);
		value += checkLine(y, x, 0, -1);
		value += checkLine(y, x, 0, 1);
		value += checkLine(y, x, 1, -1);
		value += checkLine(y, x, 1, 0);
		value += checkLine(y, x, 1, 1);
		
		value += checkKnights(y, x);
		
		return value;
		
	}
	
	private int getTile(int y, int x)
	{
		try
		{
			return chessboard[y][x];	// returning actual value of a tile
		}
		catch(IndexOutOfBoundsException ioobe)
		{
			return -1;	// outside of the board
		}
	}
	
	public static void main(String[] args)
	{
		Model m = new Model(8, 8);
		m.displayBoard();
	}
}

