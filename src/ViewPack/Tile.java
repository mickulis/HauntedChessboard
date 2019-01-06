package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton
{
	Coordinates point;
	Color color;
	CHESSPIECES piece;	// remove, use model instead
	
	Tile(int i, int j)
	{
		setLayout(new BorderLayout(getSize().width, getSize().height));
		point = new Coordinates(i, j);
		if((i + j) % 2 == 0)
		{
			color = Color.decode("#663300");
			setForeground(Color.white);
		}
		else
		{
			color = Color.decode("#b69b4c");
			setForeground(Color.black);
		}
		setBackground(color);
		setBorder(BorderFactory.createLineBorder(color, 4));
		setFocusPainted(false);
	}
	
	public void placePiece(CHESSPIECES piece)
	{
		this.piece = piece;
		setText(CHESSPIECES.getSymbol(piece));
	}
	
	public void clearPiece()
	{
		setText("");
	}
	
	public void paintBorder(Color color)
	{
		setBorder(BorderFactory.createLineBorder(color, 4));
	}
	
	public void paintBorder()
	{
		setBorder(BorderFactory.createLineBorder(color, 4));
	}
	
	public void focus()
	{
		setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	public void unfocus()
	{
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	public CHESSPIECES getPiece()
	{
		return piece;
	}
	
	public Coordinates getPoint()
	{
		return point;
	}

}
