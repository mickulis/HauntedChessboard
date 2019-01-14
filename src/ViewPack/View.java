/*
 * Author: Michał Kulis
 * Project: HauntedChessboard
 *
 */

package ViewPack;

import EnumPack.CHESSPIECES;
import ModelPack.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame
{
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	private Chessboard board;
	private PiecesPanel pieces;
	private MenuPanel menuPanel;
	
	private JButton back;
	private JButton hint;
	
	private Font font;
	
	public View(int height, int width)
	{

		setTitle("Haunted Chessboard");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(1, 1));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 30));
		
		board = new Chessboard(height, width);
		mainPanel.add(board, BorderLayout.CENTER);
		
		
		
		
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		eastPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		
		
		setupEastPanel();
		
		
		
		mainPanel.add(eastPanel, BorderLayout.EAST);
		
		
		eastPanel.add(pieces);
		eastPanel.add(back);
		eastPanel.add(new JLabel(" "));
		eastPanel.add(hint);
		
		
		menuPanel = new MenuPanel();
		mainPanel.add(menuPanel, BorderLayout.SOUTH);
		
		
		String OS = System.getProperty("os.name").toLowerCase();
		
		
		if (OS.contains("win"))
		{
			font = new javax.swing.plaf.FontUIResource("Serif",Font.PLAIN,30);
		}
		else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"))
		{
			font = new javax.swing.plaf.FontUIResource("DejaVu Sans Mono Book",Font.PLAIN,30);
		}
		
		
		
		setFont(font);
		setSize(getPreferredSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(mainPanel);
		board.setVisible(true);
		mainPanel.setVisible(true);
		
		
	}
	
	private void setupEastPanel()
	{
		pieces = new PiecesPanel();
		back = new JButton("Back");
		hint = new JButton("    Hints    ");
		back.setPreferredSize(new Dimension(40, 60));
		hint.setPreferredSize(new Dimension(40, 50));
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		hint.setAlignmentX(Component.CENTER_ALIGNMENT);
		hint.setBorder(BorderFactory.createRaisedBevelBorder());
		hint.setFocusPainted(false);
		back.setFocusPainted(false);
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
	
	public void placeHint(Coordinates p, int value)
	{
		board.placeHint(p, value);
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
	
	
	public void addBackListener(ActionListener listener)
	{
		back.addActionListener(listener);
	}
	
		public JButton getHintButton()
	{
		return hint;
	}
	
}
