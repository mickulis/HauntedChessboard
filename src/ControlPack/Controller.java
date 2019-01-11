package ControlPack;

import EnumPack.CHESSPIECES;
import ModelPack.*;
import ViewPack.MenuPanel;
import ViewPack.PieceButton;
import ViewPack.Tile;
import ViewPack.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Controller
{
	private final JFileChooser fileChooser = new JFileChooser(".");
	
	private Model model;
	private View view;
	
	private PieceButton focusPiece;	// holding a tile or piece;
	private Tile focusTile;
	
	private boolean hintsOn = false;
	
	public Controller()
	{
		view = new View(8, 8);
		
		setupChessboardTiles();
		
		setupChesspieceButtons();
		
		setupMenu();
		view.addBackListener(new BackListener());
		view.addHintListener(new HintsListener());
		view.setVisible(true);
		
		startGame();
		
	}
	
	private void setupChesspieceButtons()
	{
		for(PieceButton button: view.getPieceButtons())
		{
			button.addActionListener(new PieceListener(button));
			button.setEnabled(false);
		}
	}
	
	private void setupChessboardTiles()
	{
		for(Tile[] t: view.getTiles())
		{
			for(Tile tile: t)
			{
				tile.reset();
				tile.addActionListener(new TileListener(tile));
				tile.setEnabled(false);
			}
		}
	}
	
	private void setupMenu()
	{
		MenuPanel menu = view.getMenuPanel();
		{
			menu.addNewGameListener(new StartListener());
			menu.addCloseGameListener(new CloseListener());
			menu.addGiveUpListener(new GiveUpListener());
			menu.addLoadGameListener(new LoadListener());
			menu.addSaveGameListener(new SaveListener());
			menu.addHelpListener(new HelpListener());
		}
		
	}
	
	private void startGame()
	{
		model = new Model();
		drawBoard();
	}
	
	private void drawBoard()
	{
		for(Tile[] t: view.getTiles())
		{
			for(Tile tile: t)
			{
				if(model.isMarked(tile.getPoint()))
				{
					tile.paintBorder(Color.blue);
				}
				else
				{
					tile.paintBorder();
				}
				tile.setEnabled(true);
				String text = null;
				if(model != null)
				{
					CHESSPIECES piece = model.getPiece(tile.getPoint());
					text = CHESSPIECES.getSymbol(piece);
				}
				tile.setText(text);
			}
		}
		
		redrawPieceButtons();
	}
	
	private void redrawPieceButtons()
	{
		for(PieceButton button: view.getPieceButtons())
		{
			button.unfocus();
			if(model.isDeployed(button.getPiece()))
			{
				button.setEnabled(false);
			}
			else
			{
				button.setEnabled(true);
			}
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
		
		redrawPieceButtons();
		
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
		
		if(hintsOn)
			paintHintBorders(y, x);
		else
			paintBasicBorders(y, x);
	}
	
	private void paintBasicBorders(int y, int x)
	{
		switch(model.getValue(y, x))
		{
			case 3: view.getTiles()[y][x].paintBorder(Color.red); break;
			
			default: view.getTiles()[y][x].paintBorder();
		}
	}
	
	private void paintHintBorders(int y, int x)
	{
		switch(model.getValue(y, x))
		{
			case 0: view.getTiles()[y][x].paintBorder(); break;

			case 1: view.getTiles()[y][x].paintBorder(Color.yellow); break;

			case 2: view.getTiles()[y][x].paintBorder(Color.orange); break;

			case 3: view.getTiles()[y][x].paintBorder(Color.red); break;

			case 4: view.getTiles()[y][x].paintBorder(Color.pink); break;

			case 5: view.getTiles()[y][x].paintBorder(Color.magenta); break;
			
			default: view.getTiles()[y][x].paintBorder();
		}
	}
	
	private void winTheGame()
	{
		System.err.println("ERROR - GAME ACTUALLY ENDED");
	}
	
	private void saveGame()
	{
		File saveFile;
		int returnValue = fileChooser.showSaveDialog(view);
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			saveFile = fileChooser.getSelectedFile();
			try
			{
				FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(model);
			}
			catch (FileNotFoundException fnfe)
			{
				System.err.println("SAVE: FILE NOT FOUND");
			}
			catch (IOException ioe)
			{
				System.err.println("SAVE: INPUT OUTPUT EXCEPTION");
			}
		}
	}
	
	private void loadGame()
	{
		File loadFile;
		int returnValue = fileChooser.showOpenDialog(view);
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			loadFile = fileChooser.getSelectedFile();
			try
			{
				FileInputStream fileInputStream = new FileInputStream(loadFile);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				System.out.println("OIS created");
				model = (Model)objectInputStream.readObject();
				System.out.println("model loaded");
				drawBoard();
				redraw();
			}
			catch (FileNotFoundException fnfe)
			{
				System.err.println("LOAD: FILE NOT FOUND");
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
				System.err.println("LOAD: INPUT OUTPUT EXCEPTION");
			}
			catch (ClassNotFoundException cnfe)
			{
				System.err.println("LOAD: WRONG FILE");
			}
		}
		drawBoard();
	}
	
	
	private void clear()
	{
		if(focusPiece != null)
			focusPiece.unfocus();
		if(focusTile != null)
			focusTile.unfocus();
		
		focusPiece = null;
		focusTile = null;
	}
	
	
	//region Inner Classes		// toImplement: Save, Load, Start, Close, GiveUp, Back, Hints, Help
	
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
			{
				focusTile = tile;
				tile.focus();
			}
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
	
	class SaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			saveGame();
		}
	}
	
	class LoadListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			loadGame();
		}
	}
	
	class StartListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			startGame();
		}
	}
	
	class CloseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	class GiveUpListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			model.solve();
			drawBoard();
			redraw();
		}
	}
	
	class BackListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			model.back();
			redraw();
		}
	}
	
	class HintsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			hintsOn = !hintsOn;
			redraw();
		}
	}
	
	class HelpListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
		
		}
	}
	
	
	
	
	
	//endregion
	
}




