package display;

import java.io.IOException;

import levels.Level;
import levels.MyTextLevelLoader;

/**
 * The Class CLIDisplayer - Class that display a level to the user.
 */
public class CLIDisplayer implements iDisplayer
{	
	/*
	 * (non-Javadoc)
	 * 
	 */
	//	 * @see display.iDisplayer#DisplayLevel(levels.Level)
	@Override
	public void DisplayLevel(Level level)
	{		
		//Using the TextLevelLoader Class in order to save a level and display it to the user
		MyTextLevelLoader myTextLevelLoader = new MyTextLevelLoader();
		
		try
		{
			myTextLevelLoader.SaveLevel(level, System.out);
		} 
		catch (IOException e)
		{
			System.out.println("ERROR: Invalid level.");
		}
	}
}