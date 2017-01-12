package display;

import java.io.IOException;
import levels.Level;

/**
* The Interface iDisplayer - Interface that holds the function that a displayer
* should implement.
*/
public interface iDisplayer
{
	public void DisplayLevel(Level level) throws IOException;
}