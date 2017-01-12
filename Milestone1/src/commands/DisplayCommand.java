package commands;

import display.CLIDisplayer;
import levels.Level;

/**
 * The Class DisplayCommand.
 */
public class DisplayCommand implements iCommand
{
	/** The level. */
	private Level level;
	
	/** A displayer. */
	private CLIDisplayer displayer;
	
	/**
	 * Initializes the Display command.
	 *
	 * @param level
	 *            the level
	 */
	public DisplayCommand(Level level)
	{
		this.level = level;
		this.displayer = new CLIDisplayer();
	}
	
	/** Execute the command. */
	@Override
	public void Execute()
	{
	
			//Checking if the user has loaded a level.
			if(this.level.isEmpty() == true)
				return;
			
			//Display the level
			displayer.DisplayLevel(this.level);
			
			System.out.println();
	
	}
	
}