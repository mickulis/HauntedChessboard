/*
 * Author: Michał Kulis
 * Project: HauntedChessboard
 *
 */

package EnumPack;

public enum CHESSPIECES
{
	empty,
	knight,
	bishop,
	rook,
	king,
	queen;
	
	public static int getInteger(CHESSPIECES piece)
	{
		if(piece == null)
			return 0;
		switch(piece)
		{
			case empty: return 0;
			case knight: return 1;
			case bishop: return 2;
			case rook: return 3;
			case king: return 4;
			case queen: return 5;
		}
		return 0;
	}
	
	public static String getSymbol(CHESSPIECES piece)	// encapsulated maybe
	{
		if(piece == null)
			return null;
		switch(piece)
		{
			case empty: return null;
			case knight: return "\u2658";
			case bishop: return "\u2657";
			case rook: return "\u2656";
			case king: return "\u2654";
			case queen: return "\u2655";
		}
		return null;
	}
	
	
	public static CHESSPIECES getPiece(String name)
	{
		if(name == null)
			return null;
		switch(name)
		{
			case "": return empty;
			case "knight": return knight;
			case "bishop": return bishop;
			case "rook": return rook;
			case "king": return king;
			case "queen": return queen;
		}
		return null;
	}
	
}
