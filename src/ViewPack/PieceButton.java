package ViewPack;

import EnumPack.CHESSPIECES;

import javax.swing.*;
import java.awt.*;

public class PieceButton extends JButton
{
	CHESSPIECES piece;
	
	PieceButton(CHESSPIECES piece)
	{
		super(CHESSPIECES.getSymbol(piece));
		this.piece = piece;
		unfocus();
		setPreferredSize(new Dimension(50, 50));
		// set text depending on piece
	}
	
	public CHESSPIECES getPiece()
	{
		return piece;
	}
	
	
	public void focus()
	{
		setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	public void unfocus()
	{
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
}
