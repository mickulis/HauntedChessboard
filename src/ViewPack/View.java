package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame
{
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	Chessboard board;
	PiecesPanel pieces;
	Menu menu;
	
	
	Font font;
	public View(int height, int width)
	{
		//setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		board = new Chessboard(height, width);
		pieces = new PiecesPanel();
		
		
		
		//region Center panel
		JPanel panel = new JPanel();
		mainPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10, 12));
		panel.add(board, BorderLayout.CENTER);
		panel.add(pieces, BorderLayout.EAST);
		//endregion
		
		//region South panel
		mainPanel.add(new JLabel("asdfgdnsadfgfds"), BorderLayout.SOUTH);
		
		
		//endregion
		font = new Font(Font.SERIF, Font.BOLD, 40);
		
		
		
		setFont(font);
		setSize(getPreferredSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(mainPanel);
		panel.setVisible(true);
		board.setVisible(true);
		mainPanel.setVisible(true);
		
		
		
		
	}
	
	
	public void setFont(Font font)
	{
		super.setFont(font);
		board.setFont(font);
	}
	
	public void placePiece(Coordinates p, CHESSPIECES piece)
	{
		board.placePiece(p, piece);
	}
	
	public Tile[][] getTiles()
	{
		return board.getTiles();
	}
	
	public PieceButton[] getPieceButtons()
	{
		return pieces.getPieceButtons();
	}
	
	public void undeployPiece(CHESSPIECES piece)
	{
		pieces.undeployPiece(piece);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
}
