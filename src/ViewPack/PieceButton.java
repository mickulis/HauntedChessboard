package ViewPack;

import EnumPack.CHESSPIECES;

import javax.swing.*;

public class PieceButton extends JButton
{
	CHESSPIECES piece;
	
	PieceButton(CHESSPIECES piece)
	{
		super(CHESSPIECES.getSymbol(piece));
		this.piece = piece;
		// set text depending on piece
	}
	
	public CHESSPIECES getPiece()
	{
		return piece;
	}
	
}
