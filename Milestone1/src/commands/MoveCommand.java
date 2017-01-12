package commands;

import java.io.IOException;
import java.util.ArrayList;

import items.Player;
import items.Position;
import items.iMoveable;
import levels.Level;

/**
 * The Class MoveCommand.
 */
public class MoveCommand implements iCommand
{
	/** The level. */
	private Level level;

	/** Type of move, like: up, down, right and left. */
	private String moveType;
	
	private iMoveable item;
	
	/**
	 * Initializes the move command.
	 *
	 * @param level
	 *            the level
	 */
	public MoveCommand(Level level)
	{
		this.level = level;
	}

	/** Execute the command. */
	@Override
	public void Execute()
	{
		try
		{
			//Checking if the user had loaded a level.
			if(this.level.isEmpty() == true)
				throw new Exception("ERROE: Invalid level.");
						
			Position deltaPos = new Position(this.level.getPolicy().getMoveTypeSetPosition(moveType));

			if(deltaPos.getX() == 0 && deltaPos.getY() == 0)
				throw new IOException("ERROR: Invalid move type.");
			
			Position currentItemPos = new Position(this.item.getPosition()); 
			
			//Creating new position for the player
			Position itemNewPosition = new Position(currentItemPos.getX() + deltaPos.getX(), currentItemPos.getY() + deltaPos.getY());

			//Checking if the new position is valid by a helping method
			if (this.level.isValidPosition(itemNewPosition) == false)
				return;
			
			boolean isCanPlayerMove = this.level.getPolicy().isMovePosible(this.level, this.item, itemNewPosition);
			
			if(isCanPlayerMove == false)
				return;	
			
			this.level.Move(this.item, itemNewPosition);

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * isValidMoveType - initializes the CLI and the type of a move command.
	 *
	 * @param moveType
	 * 			  the move type command
	 * 
	 * @return true/false if the user entered valid move type
	 */
	public boolean isValidMoveType(String moveType)
	{
		ArrayList<String> moveList = new ArrayList<String>();
		moveList.add("up");
		moveList.add("down");
		moveList.add("right");
		moveList.add("left");
		
		if(moveList.contains(moveType))
			return true;
		
		return false;
	}
	
	/**
	 * GetMoveType - return the local move type.
	 * 
	 * @return moveType
	 */
	public String getMoveType()
	{
		return moveType;
	}

	/**
	 * SetMoveType - initializes the move type variable
	 * 
	 * @param moveType
	 * 			type of move, like: up, down, right and left
	 */
	public void setMoveType(String moveType)
	{
		this.moveType = moveType.toLowerCase();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public iMoveable getItem() {
		return item;
	}

	public void setItem(iMoveable item) {
		this.item = item;
	}
	

}
