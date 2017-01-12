package boot;

import java.io.IOException;

import commands.CLI;
import display.CLIDisplayer;
import levels.Level;

public class Run 
{
	public static void main (String[] args) throws ClassNotFoundException, IOException
	{		
		CLI cli = new CLI(new CLIDisplayer());
		cli.Listen();
	}
}