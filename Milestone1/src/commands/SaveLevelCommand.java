package commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import levels.Level;
import levels.LevelLoaderFactory;
import levels.iLevelLoader;

/**
 * The Class SaveLevelCommand.
 */
public class SaveLevelCommand implements iCommand
{
	/** The level. */
	private Level level;
	
	/** A path of a file's location. */
	private String path;
	
	/** An outputstream. */	
	private OutputStream os;
	
	/**
	 * Initializes the save level command.
	 * @param level
	 * 			the level
	 * @param exitCommand
	 * 			the exit command
	 */
	public SaveLevelCommand(Level level)
	{
		this.level = level;
		this.os = level.getOs();
	}

	/** Execute the command. */
	@Override
	public void Execute()
	{
		//Creating a factory object in order to fitting the type of a level file
		LevelLoaderFactory loaderFactory = new LevelLoaderFactory();
		iLevelLoader levelsaver = loaderFactory.CreateLevelLoader(path);
		
		try
		{
			if(this.level.isEmpty() == true)
				throw new IOException("ERROR: invalid path.");
			
			this.os = new FileOutputStream(path);
			this.level.setOs(this.os);
			
			//Saves a level
			levelsaver.SaveLevel(this.level, this.os);
			System.out.println("The level is saved successfully.");

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

	/**
	 * Return the path to save the level.
	 *
	 * @return path
	 *           
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * Initializes a path.
	 *
	 * @param path
	 *            a path to save the level.
	 * @see commands.iCommand#Execute()
	 */
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


	public OutputStream getOs() 
	{
		return os;
	}

	public void setOs(OutputStream os) 
	{
		this.os = os;
	}
	
	
}
