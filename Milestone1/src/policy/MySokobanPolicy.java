package policy;

import java.io.Serializable;

import items.Box;
import items.Player;
import items.Position;
import items.Target;
import items.Wall;
import items.iGeneralItem;
import items.iMoveable;
import levels.Level;

/**
 * The Class MySokobanPolicy - defines the policy of SOKOBAN game.
 */
public class MySokobanPolicy implements Serializable, iSokobanPolicy
{
	/** The policy that prevent the player to walk on walls. */
	private boolean isPlayerCanWalkOnWall;
	
	/** The policy that prevent the player to pull boxes. */
	private boolean isPlayerCanPullBox;
	
	/** The policy that prevent the box to walk on walls. */
	private boolean isBoxCanWalkOnWall;
	
	/** The policy that prevent the box to push boxes. */
	private boolean isBoxCanPushBox;
		
	/** Initialize all the policies to false. */
	public MySokobanPolicy()
	{
		this.isBoxCanPushBox = false;
		this.isBoxCanWalkOnWall = false;
		this.isPlayerCanPullBox = false;
		this.isPlayerCanWalkOnWall = false;
	}

	/**
	 * GetIsPlayerCanWalkOnWall.
	 * 
	 * @return true/false if the player can walk on walls
	 */
	public boolean getIsPlayerCanWalkOnWall()
	{
		return isPlayerCanWalkOnWall;
	}

	/**
	 * SetPlayerCanWalkOnWall - gets a policy and initializes it to the local policy.
	 * 
	 * @param isPlayerCanWalkOnWall
	 * 			a policy
	 */
	public void setPlayerCanWalkOnWall(boolean isPlayerCanWalkOnWall)
	{
		this.isPlayerCanWalkOnWall = isPlayerCanWalkOnWall;
	}

	/**
	 * GetIsPlayerCanPullBox.
	 * 
	 * @return true/false if the player can pull boxes
	 */
	public boolean getIsPlayerCanPullBox()
	{
		return isPlayerCanPullBox;
	}

	/**
	 * SetPlayerCanPullBox - gets a policy and initializes it to the local policy.
	 * 
	 * @param isPlayerCanPullBox
	 * 			a policy
	 */
	public void setPlayerCanPullBox(boolean isPlayerCanPullBox)
	{
		this.isPlayerCanPullBox = isPlayerCanPullBox;
	}

	/**
	 * GetIsBoxCanWalkInWall.
	 * 
	 * @return true/false if a box can walk on walls
	 */
	public boolean getIsBoxCanWalkInWall()
	{
		return isBoxCanWalkOnWall;
	}

	/**
	 * SetBoxCanWalkInWall - gets a policy and initializes it to the local policy.
	 * 
	 * @param isBoxCanWalkOnWall
	 * 			a policy
	 */
	public void setBoxCanWalkInWall(boolean isBoxCanWalkOnWall)
	{
		this.isBoxCanWalkOnWall = isBoxCanWalkOnWall;
	}

	/**
	 * GetIsBoxCanPushBox.
	 * 
	 * @return true/false if a box can push boxes
	 */
	public boolean getIsBoxCanPushBox()
	{
		return isBoxCanPushBox;
	}

	/**
	 * SetBoxCanPushBox - gets a policy and initializes it to the local policy.
	 * 
	 * @param isBoxCanPushBox
	 * 			a policy
	 */
	public void setBoxCanPushBox(boolean isBoxCanPushBox)
	{
		this.isBoxCanPushBox = isBoxCanPushBox;
	}

	/**
	 * IsBoxCanWalkOnWall.
	 * 
	 * @return true/false if a box can walk on walls.
	 */
	public boolean getIsBoxCanWalkOnWall()
	{
		return isBoxCanWalkOnWall;
	}

	/**
	 * SetBoxCanWalkOnWall - gets a policy and initializes it to the local policy.
	 * 
	 * @param isBoxCanWalkOnWall
	 * 			a policy
	 */
	public void setBoxCanWalkOnWall(boolean isBoxCanWalkOnWall)
	{
		this.isBoxCanWalkOnWall = isBoxCanWalkOnWall;
	}

	public Position getMoveTypeSetPosition(String moveType)
	{
		
		int xMove = 0;
		int yMove = 0;

		switch (moveType)
		{
		case "left":
			yMove--;
			break;
		case "right":
			yMove++;
			break;
		case "up":
			xMove--;
			break;
		case "down":
			xMove++;
			break;
		default:
			break;
		}
		
		Position pos = new Position(xMove, yMove);

		
		return pos;
	}
	
	public Position getDeltaPosition(Position currenItemPos, Position newItemPos)
	{
		int xMove = 0;
		int yMove = 0;
		
		//Maybe instead of = new Position(currenItemPos.getX() - newItemPos.getX(), currenItemPos.getY() - newItemPos.getY());
		Position deltaPos = new Position(currenItemPos.getX() - newItemPos.getX(), currenItemPos.getY() - newItemPos.getY());
							
		return deltaPos;
	}
	
	@Override
	public boolean isMovePosible(Level level, iMoveable item, Position pos) 
	{
		//Creating a general item by copy the current item on the new player position
		iGeneralItem newItemInPosition = level.getItemInPosition(pos);
		

		if((item instanceof Player) && (newItemInPosition instanceof Wall) && (this.getIsPlayerCanWalkOnWall() == false))
			return false;
		
		//Checking if the item is a type of box, so we will need to check another positions
		if((item instanceof Box) && (newItemInPosition instanceof Box) && (this.getIsBoxCanPushBox() == false))
			return false;
		
		if((item instanceof Player) && (newItemInPosition instanceof Box))
		{
			Position deltaPos = this.getDeltaPosition(pos, item.getPosition());

			//Creating new position for a box
			Position nextToBox = new Position(newItemInPosition.getPosition().getX() + deltaPos.getX(), newItemInPosition.getPosition().getY() + deltaPos.getY());

			if(level.isValidPosition(nextToBox) == false)
				return false;
			
			if(level.getItemInPosition(nextToBox) instanceof Box)
				return false;
			
			if(level.getItemInPosition(nextToBox) instanceof Wall && this.getIsBoxCanWalkInWall() == false)
				return false;
			
			else
				return true;		
		}

		return true;
	}
	
}