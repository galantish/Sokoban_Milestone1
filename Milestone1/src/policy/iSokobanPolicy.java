package policy;

import items.Position;
import items.iMoveable;
import levels.Level;

public interface iSokobanPolicy 
{
	//This method checks all the boolean memebers in the class MySokobanPolicy and checks if a movable item can move to the new position.
	public boolean isMovePosible(Level level, iMoveable item, Position pos);
}
