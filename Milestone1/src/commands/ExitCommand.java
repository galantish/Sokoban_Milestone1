package commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import levels.Level;

/**
 * The Class ExitCommand.
 */
public class ExitCommand implements iCommand
{
	/** InputStream (in order to close the load (file)). */
	private InputStream is;
	
	/** OutputStream (in order to close the save file if the user enter save). */
	private OutputStream os;

	/** ExitCommand - initializes the io to null. */
	public ExitCommand()
	{
		this.is = null;
		this.os = null;
	}
	
	/**
	 * ExitCommand - gets exit command and initialized the local io to exitcommand's ios.
	 * @param exitCommand
	 * 			a exit command
	 */
	public ExitCommand(Level level)
	{
		this.is = level.getIs();
		this.os = level.getOs();
	}
	
	/** Execute the command. */
	@Override
	public void Execute()
	{
		//Checking if the inputstream is open, and close it.
		if(is != null)
		{
			try 
			{
				this.is.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		//Checking if the outputstream is open, and close it.
		if(os != null)
		{
			try 
			{
				this.os.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public InputStream getIs() 
	{
		return is;
	}

	public void setIs(InputStream is) 
	{
		this.is = is;
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