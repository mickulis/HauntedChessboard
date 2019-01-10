package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton
{
	private Coordinates point;
	private Color color;
	
	
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
		paintBorder();
		setFocusPainted(false);
	}
	
	public void reset()
	{
		setText(null);
		setBackground(color);
		paintBorder();
	}
	
	void placePiece(CHESSPIECES piece)
	{
		setText(CHESSPIECES.getSymbol(piece));
	}
	
	void clearPiece()
	{
		setText("");
	}
	
	public void paintBorder(Color color)
	{
		setBorder(BorderFactory.createLineBorder(color, 4));
	}
	
	public void paintBorder()
	{
		setBorder(null);
	}
	
	public void focus()
	{
		setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	public void unfocus()
	{
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	public Coordinates getPoint()
	{
		return point;
	}

}
