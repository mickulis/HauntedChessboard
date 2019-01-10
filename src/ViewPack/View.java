package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame
{
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	private Chessboard board;
	private PiecesPanel pieces;
	private MenuPanel menuPanel;
	
	
	private Font font;
	
	public View(int height, int width)
	{

		setTitle("Haunted Chessboard");
		//setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(1, 1));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 30));
		
		board = new Chessboard(height, width);
		pieces = new PiecesPanel();
		
		mainPanel.add(board, BorderLayout.CENTER);
		mainPanel.add(pieces, BorderLayout.EAST);
		//endregion
		
		//region South panel
		menuPanel = new MenuPanel();
		mainPanel.add(menuPanel, BorderLayout.SOUTH);
		
		
		//endregion
		font = new Font(Font.SERIF, Font.BOLD, 20);
		
		
		
		setFont(font);
		setSize(getPreferredSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(mainPanel);
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
	
	public MenuPanel getMenuPanel()
	{
		return menuPanel;
	}
	
	
	
	
}
