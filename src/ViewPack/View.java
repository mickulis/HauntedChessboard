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
		//setLayout(new BorderLayout());
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
		//endregion
		
		menuPanel = new MenuPanel();
		mainPanel.add(menuPanel, BorderLayout.SOUTH);
		
		
		font = new Font(Font.SERIF, Font.BOLD, 20);
		
		
		
		setFont(font);
		setSize(getPreferredSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(mainPanel);
		board.setVisible(true);
		mainPanel.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "GAME INITIALIZED","GAME INITIALIZED", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void setupEastPanel()
	{
		pieces = new PiecesPanel();
		back = new JButton("Back");
		hint = new JButton("Hint");
		back.setPreferredSize(new Dimension(40, 60));
		hint.setPreferredSize(new Dimension(40, 50));
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		hint.setAlignmentX(Component.CENTER_ALIGNMENT);
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
	
	
	public void addBackListener(ActionListener listener)
	{
		back.addActionListener(listener);
	}
	
	public void addHintListener(ActionListener listener)
	{
		hint.addActionListener(listener);
	}
	
}
