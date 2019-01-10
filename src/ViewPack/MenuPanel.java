package ViewPack;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel
{
	private JButton newGame;
	private JButton saveGame;
	private JButton loadGame;
	private JButton closeGame;
	private JButton help;
	private JButton giveUp;
	
	
	
	MenuPanel()
	{
		newGame = new JButton("NEW GAME");
		saveGame = new JButton("SAVE");
		loadGame = new JButton("LOAD");
		closeGame = new JButton("CLOSE");
		help = new JButton("HELP");
		giveUp = new JButton("GIVE UP");
		
		add(newGame);
		add(saveGame);
		add(loadGame);
		add(closeGame);
		add(help);
		add(giveUp);
	}
	
	
	public void addNewGameListener(ActionListener listener)
	{
		newGame.addActionListener(listener);
	}
	
	public void addSaveGameListener(ActionListener listener)
	{
		saveGame.addActionListener(listener);
	}
	
	public void addLoadGameListener(ActionListener listener)
	{
		loadGame.addActionListener(listener);
	}
	
	public void addCloseGameListener(ActionListener listener)
	{
		closeGame.addActionListener(listener);
	}
	
	public void addGiveUpListener(ActionListener listener)
	{
		giveUp.addActionListener(listener);
	}
	
	public void addHelpListener(ActionListener listener)
	{
		help.addActionListener(listener);
	}
	
	
	
	
}
