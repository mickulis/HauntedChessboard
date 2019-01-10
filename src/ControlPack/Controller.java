package ControlPack;

import EnumPack.CHESSPIECES;
import ModelPack.*;
import ViewPack.PieceButton;
import ViewPack.Tile;
import ViewPack.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller
{
	private Model model;
	private View view;
	
	private PieceButton focusPiece;	// holding a tile or piece;
	private Tile focusTile;
	
	private boolean hintsOn = true;
	
	public Controller()
	{
		view = new View(8, 8);
		
		for(Tile[] t: view.getTiles())
		{
			for(Tile tile: t)
			{
				tile.reset();
				tile.addActionListener(new TileListener(tile));
				tile.setEnabled(false);
			}
		}
		
		for(PieceButton button: view.getPieceButtons())
		{
			button.addActionListener(new PieceListener(button));
			button.setEnabled(false);
		}
		
		
		view.setVisible(true);
		
		startGame();
	}
	
	private void startGame()
	{
		model = new Model();
		
		for(Tile[] t: view.getTiles())
		{
			for(Tile tile: t)
			{
				if(model.isMarked(tile.getPoint()))
				{
					tile.paintBorder(Color.blue);
				}
				tile.setEnabled(true);
			}
		}
		
		for(PieceButton button: view.getPieceButtons())
		{
			button.reset();
		}
		
	}
	
	private void redraw()
	{
		for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Coordinates p = new Coordinates(i, j);
				paintBorder(i, j);
				view.placePiece(p, model.getPiece(p));
			}
		}
		if(model.checkVictoryCondition())
			winTheGame();
	}
	
	private void paintBorder(int y, int x)
	{
		if (model.isMarked(new Coordinates(y, x)))
		{
			if (model.getValue(y, x) == 3)
				view.getTiles()[y][x].paintBorder(Color.green);
			else
				view.getTiles()[y][x].paintBorder(Color.blue);
			return;
		}
		switch(model.getValue(y, x))
		{
//			case 0: view.getTiles()[y][x].paintBorder(); break;
			
//			case 1: view.getTiles()[y][x].paintBorder(Color.yellow); break;
			
//			case 2: view.getTiles()[y][x].paintBorder(Color.orange); break;
			
			case 3: view.getTiles()[y][x].paintBorder(Color.red); break;
			
//			case 4: view.getTiles()[y][x].paintBorder(Color.pink); break;
			
//			case 5: view.getTiles()[y][x].paintBorder(Color.magenta); break;
			
			default: view.getTiles()[y][x].paintBorder();
		}
		
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
				CHESSPIECES currentPiece = model.getPiece(tile.getPoint());
				view.undeployPiece(currentPiece);
				model.removePiece(tile.getPoint());
				model.deployPiece(tile.getPoint(), focusPiece.getPiece());
				if(focusPiece.getPiece() != CHESSPIECES.empty)
					focusPiece.setEnabled(false);
				
				clear();
				redraw();
				return;
			}
			
			if(focusTile != null)
			{
				model.swap(tile.getPoint(), focusTile.getPoint());
				clear();
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
			clear();
			focusPiece = button;
			button.focus();
		}
	}
	
	public void clear()
	{
		if(focusPiece != null)
			focusPiece.unfocus();
		if(focusTile != null)
			focusTile.unfocus();
		
		focusPiece = null;
		focusTile = null;
	}
	//endregion
	
}




