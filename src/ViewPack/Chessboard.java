package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.RIGHT;

public class Chessboard extends JComponent
{
	private Tile[][] tiles;
	private ArrayList<JLabel> labels;
	
	Chessboard(int height, int width)
	{
		setLayout(new GridLayout(height + 2, width + 2));
		labels = new ArrayList<>();
		tiles = new Tile[height][width];
		
		createLetterRow(width, JLabel.BOTTOM);
		
		for(int i = 0; i < height; i++)
		{
			createChessboardRow(width, i);
		}
		
		createLetterRow(width, JLabel.TOP);
		
	}
	
	private void createChessboardRow(int width, int i)
	{
		JLabel label = new JLabel(Character.toString('8' - (char)i) + "  ", RIGHT);
		labels.add(label);
		add(label);
		for (int j = 0; j < width; j++)
		{
			tiles[i][j] = new Tile(i, j);
			add(tiles[i][j]);
			tiles[i][j].setVisible(true);
		}
		label = new JLabel("  " + Character.toString('8' - (char)i), LEFT);
		labels.add(label);
		add(label);
	}
	
	private void createLetterRow(int width, int bottom)
	{
		add(new JLabel());
		for (int i = 0; i < width; i++)
		{
			JLabel label = new JLabel(Character.toString('A' + (char) i), CENTER);
			label.setVerticalAlignment(bottom);
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
	
	void placePiece(Coordinates p, CHESSPIECES piece)	// clearPiece = deployPiece(p, null)?
	{
		if(piece == null || piece == CHESSPIECES.empty)
			clearPiece(p);
		else
			tiles[p.getY()][p.getX()].placePiece(piece);
	}
	
	void placeHint(Coordinates p, int value)	// clearPiece = deployPiece(p, null)?
	{
		tiles[p.getY()][p.getX()].placeHint(value);
	}
	
	
	
	private void clearPiece(Coordinates p)		// unnecessary with placePiece(p, null)
	{
		tiles[p.getY()][p.getX()].clearPiece();
	}
	
	Tile[][] getTiles()
	{
		return tiles;
	}
	
	
}
