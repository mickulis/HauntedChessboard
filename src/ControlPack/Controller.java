package ControlPack;

import EnumPack.CHESSPIECES;
import ModelPack.*;
import ViewPack.PieceButton;
import ViewPack.Tile;
import ViewPack.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller
{
	private Model model;
	private View view;
	
	private PieceButton focusPiece;	// holding a tile or piece;
	private Tile focusTile;
	
	public Controller(Model m, View v)
	{
		model = m;
		view = v;
		
		for(Tile[] t: v.getTiles())
		{
			for(Tile tile: t)
			{
				
				if(model.isMarked(tile.getPoint()))
				{
					tile.paintBlue();
					
				}
				tile.addActionListener(new TileListener(tile));
			}
		}
		
		for(PieceButton button: v.getPieceButtons())
		{
			button.addActionListener(new PieceListener(button));
		}
		
		
		v.setVisible(true);
	}
	
	
	
	private void redraw()
	{
		for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Coordinates p = new Coordinates(i, j);
				view.placePiece(p, model.getPiece(p));
			}
		}
		if(model.checkVictoryCondition())
			winTheGame();
	}
	
	private void winTheGame()
	{
		System.err.println("ERROR - GAME ACTUALLY ENDED");
	}
	
	
	
	
	
	
	//region Inner Classes
	
	class TileListener implements ActionListener
	{
		Tile tile;
		
		TileListener(Tile tile)
		{
			this.tile = tile;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(focusPiece != null)
			{
				view.undeployPiece(tile.getPiece());
				model.removePiece(tile.getPoint());
				model.deployPiece(tile.getPoint(), focusPiece.getPiece());
				if(focusPiece.getPiece() != CHESSPIECES.empty)
					focusPiece.setEnabled(false);
				focusPiece = null;
				focusTile = null;
				redraw();
				return;
			}
			
			if(focusTile != null)
			{
				model.swap(tile.getPoint(), focusTile.getPoint());
				focusPiece = null;
				focusTile = null;
				redraw();
				return;
			}
			if(model.isOccupied(tile.getPoint()))
				focusTile = tile;
		}
	}
	
	class PieceListener implements ActionListener
	{
		PieceButton button;
		
		PieceListener(PieceButton button)
		{
			this.button = button;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			focusPiece = button;
		}
	}
	
	//endregion
	
}




