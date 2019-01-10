package ViewPack;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel
{
	JButton newGame;
	JButton saveGame;
	JButton loadGame;
	JButton closeGame;
	JButton help;
	JButton giveUp;
	
	
	
	public MenuPanel()
	{
		add(new JButton("NEW GAME"));
		add(new JButton("SAVE"));
		add(new JButton("LOAD"));
		add(new JButton("CLOSE"));
		add(new JButton("HELP"));
		add(new JButton("GIVE UP"));
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
