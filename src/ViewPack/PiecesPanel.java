package ViewPack;

import EnumPack.CHESSPIECES;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class PiecesPanel extends JPanel
{
	//JButton remove;
	PieceButton[] pieces;
	
	public PiecesPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//remove = new JButton("rmv");
		
		
		
		
		//remove.setMaximumSize(new Dimension(100, 120));
		//remove.setMinimumSize(new Dimension(100, 120));
		//remove.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel filler = new JLabel();
		filler.setMaximumSize(new Dimension(200, 200));
		JLabel filler2 = new JLabel();
		filler2.setMaximumSize(new Dimension(200, 200));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(200, 200));
		panel.setLayout(new GridLayout(3, 2));
		
		pieces = new PieceButton[6];
		for(CHESSPIECES piece: CHESSPIECES.values())
			pieces[CHESSPIECES.getInteger(piece)] = new PieceButton(piece);
		panel.add(pieces[0]);
		panel.add(pieces[1]);
		panel.add(pieces[2]);
		panel.add(pieces[3]);
		panel.add(pieces[4]);
		panel.add(pieces[5]);
		
		add(filler);
		//add(remove);
		add(panel);
		add(filler2);
		
		//remove.setVisible(true);
		pieces[0].setVisible(true);
		pieces[1].setVisible(true);
		pieces[2].setVisible(true);
		pieces[3].setVisible(true);
		pieces[4].setVisible(true);
		pieces[5].setVisible(true);
		panel.setVisible(true);
		
	}
	
	
	public PieceButton[] getPieceButtons()
	{
		return pieces;
	}
	
	public void undeployPiece(CHESSPIECES piece)
	{
		pieces[CHESSPIECES.getInteger(piece)].setEnabled(true);
	}
}
