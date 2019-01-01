package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import static javax.swing.SwingConstants.CENTER;

public class Chessboard extends JComponent
{
	private Tile[][] tiles;
	private ArrayList<JLabel> labels;
	
	Chessboard(int height, int width)
	{
		setLayout(new GridLayout(height + 2, width + 2));
		labels = new ArrayList<>();
		tiles = new Tile[height][width];
		
		add(new JLabel());
		for(int i = 0; i < width; i++)
		{
			JLabel label = new JLabel(Character.toString('0' + i), CENTER);
			labels.add(label);
			add(label);
		}
		add(new JLabel());
		
		for(int i = 0; i < height; i++)
		{
			JLabel label = new JLabel(Character.toString('0' + i), CENTER);
			labels.add(label);
			add(label);
			for (int j = 0; j < width; j++)
			{
				tiles[i][j] = new Tile(i, j);
				add(tiles[i][j]);
				tiles[i][j].setVisible(true);
			}
			label = new JLabel(Character.toString('0' + i), CENTER);
			labels.add(label);
			add(label);
		}
		
		add(new JLabel());
		for(int i = 0; i < width; i++)
		{
			JLabel label = new JLabel(Character.toString('0' + i), CENTER);
			labels.add(label);
			add(label);
		}
		add(new JLabel());
		
		
		
		
	}
	
	public void setFont(Font font)
	{
		super.setFont(font);
		for(Tile[] row: tiles)
		{
			for (Tile t : row)
			{
				t.setFont(font);
			}
		}
		for(JLabel label: labels)
		{
			label.setFont(font);
		}
	}
	
	public void placePiece(Coordinates p, CHESSPIECES piece)	// clearPiece = deployPiece(p, null)?
	{
		if(piece == null || piece == CHESSPIECES.empty)
			clearPiece(p);
		else
			tiles[p.getY()][p.getX()].placePiece(piece);
	}
	
	public void clearPiece(Coordinates p)
	{
		tiles[p.getY()][p.getX()].clearPiece();
	}
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
	
	
}
