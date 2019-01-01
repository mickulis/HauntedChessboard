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
		switch(piece)
		{
			case empty: return "\u274C";
			case knight: return "\u2658";
			case bishop: return "\u2657";
			case rook: return "\u2656";
			case king: return "\u2654";
			case queen: return "\u2655";
		}
		return null;
	}
}
