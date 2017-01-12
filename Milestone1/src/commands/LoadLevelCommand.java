package commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import items.Box;
import items.Player;
import items.Target;
import items.iMoveable;
import items.iUnmoveable;
import levels.Level;
import levels.LevelLoaderFactory;
import levels.iLevelLoader;
import policy.MySokobanPolicy;

/**
 * The Class LoadLevelCommand.
 */
public class LoadLevelCommand implements iCommand
{
	/** The level. */
	private Level level;
	
	/** A path of a file's location. */
	private String path;
	
	/** An inputstream. */	
	private InputStream is;
	
	/**
	 * Initializes the level load command.
	 *
	 * @param level
	 *            the level
	 * @param exitCommand
	 * 			an exit command
	 */
	public LoadLevelCommand(Level level)
	{
		this.level = level;
		this.path = "";
		this.is = level.getIs();
	}

	/** Execute the command. */
	@Override
	public void Execute()
	{
		//Creating a factory object in order to fitting the type of a level file
		LevelLoaderFactory loaderFactory = new LevelLoaderFactory();
		iLevelLoader levelLoader = loaderFactory.CreateLevelLoader(this.path);
			
		try
		{
			this.is = new FileInputStream(this.path);
			this.level.setIs(this.is);
			
			if(levelLoader == null)
			{
				throw new IOException("ERROR: invalid path.");
			}
			
			//Load a level
			
			//this.level = levelLoader.LoadLevel(is); 	
			
			
			Level tempLevel = new Level(levelLoader.LoadLevel(this.is));
			
			//this.level = tempLevel;
		
			this.level.setBoard(tempLevel.getBoard());
			this.level.setRow(tempLevel.getRow());
			this.level.setCol(tempLevel.getCol());
			this.level.setItemsOnBoard(tempLevel.getItemsOnBoard());
			this.level.setBoard(tempLevel.getBoard());
			this.level.setPlayers(tempLevel.getPlayers());
			this.level.setBoxes(tempLevel.getBoxes());
			this.level.setTargets(tempLevel.getTargets());
			this.level.setPlayersSteps(tempLevel.getPlayersSteps());
			this.level.setPolicy(tempLevel.getPolicy());
			this.level.setTime(tempLevel.getTime());

			System.out.println("The level is loaded successfully.");
			
		} 
		
		catch (ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}


	public Level getLevel() 
	{
		return level;
	}

	public void setLevel(Level level) 
	{
		this.level = level;
	}


	public InputStream getIs() 
	{
		return is;
	}

	public void setIs(InputStream is) 
	{
		this.is = is;
	}


}
