package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton
{
	private Coordinates point;
	private Color backgroundColor;
	private Color foregroundColor;
	private Color hintColor = Color.gray;
	
	
	Tile(int i, int j)
	{
		setLayout(new BorderLayout(getSize().width, getSize().height));
		point = new Coordinates(i, j);
		if((i + j) % 2 == 0)
		{
			backgroundColor = Color.decode("#663300");
			foregroundColor = Color.white;
			
		}
		else
		{
			backgroundColor = Color.decode("#b69b4c");
			foregroundColor = Color.black;
		}
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		paintBorder();
		setFocusPainted(false);
	}
	
	public void reset()
	{
		setText(null);
		setBackground(backgroundColor);
		paintBorder();
	}
	
	void placePiece(CHESSPIECES piece)
	{
		setForeground(foregroundColor);
		setText(CHESSPIECES.getSymbol(piece));
	}
	
	void placeHint(int value)
	{
		setForeground(hintColor);
		setText(Integer.toString(value));
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
