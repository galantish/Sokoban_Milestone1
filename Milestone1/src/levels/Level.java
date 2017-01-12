package levels;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import items.*;
import policy.MySokobanPolicy;

/**
* The Class Level - The class that build a level.
*/
public class Level implements Serializable 
{
	/** The numbers of players's steps. */
	private int playersSteps;
	/** The list of players. */
	private ArrayList<Player> players;
	/** The list of boxes. */
	private ArrayList<Box> boxes;	
	/** The list of targets. */
	private ArrayList<Target> targets;	
	/** The 2D array of the movable items. */
	private iMoveable[][] itemsOnBoard;
	/** The 2D array of the unmovable items (the board game). */
	private iUnmoveable[][] board;
	/** The 2D arrays's length. */
	private int row;
	/** The 2D arrays's width. */
	private int col;
	/** The Sokoban Game Policy. */
	private MySokobanPolicy policy;	
	/** Level time. */
	private long time;
	/** InputStream for the level */
	private InputStream is;
	/** OutputStream for the level */
	private OutputStream os;	
	
	/**
	 * Initializes the level.
	 */
	public Level() 
	{
		this.row = 0;
		this.col = 0;
		this.playersSteps = 0;
		this.players = new ArrayList<Player>(); 
		this.boxes = new ArrayList<Box>();
		this.targets = new ArrayList<Target>();
		this.itemsOnBoard = new iMoveable[this.row][this.col];		
		this.board = new iUnmoveable[this.row][this.col];	
		this.policy = new MySokobanPolicy();
		this.time = System.currentTimeMillis();
		this.is = null;
		this.os = null;
	}
	
	/**
	 * Initializes the level.
	 * 
	 * @param row
	 * 				2D arrays's length
	 * @param col
	 * 				2D arrays's width
	 */
	public Level(int row, int col)
	{
		this.row = row;
		this.col = col;
		this.playersSteps = 0;
		this.players = new ArrayList<Player>(); 
		this.boxes = new ArrayList<Box>();
		this.targets = new ArrayList<Target>();
		this.itemsOnBoard = new iMoveable[this.row][this.col];		
		this.board = new iUnmoveable[this.row][this.col];
		this.policy = new MySokobanPolicy();
		this.time = System.currentTimeMillis();
		this.is = null;
		this.os = null;
		//Initializing the background array to Floor
		for(int i = 0; i < this.row; i++)
			for(int j = 0; j < this.col; j++)
				this.board[i][j] = new Floor();
	}
	
	/**
	 * Initializes the level.
	 * 
	 * @param row
	 * 				2D arrays's length
	 * @param col
	 * 				2D arrays's width
	 * @param policy
	 * 				the SOKOBAN policy
	 */
	public Level(int row, int col, MySokobanPolicy policy)
	{
		this.row = row;
		this.col = col;
		this.itemsOnBoard = new iMoveable[this.row][this.col];		
		this.board = new iUnmoveable[this.row][this.col];
		this.players = new ArrayList<Player>(); 
		this.boxes = new ArrayList<Box>();
		this.targets = new ArrayList<Target>();
		this.playersSteps = 0;
		this.policy = new MySokobanPolicy();
		this.setPolicy(policy);
		this.time = System.currentTimeMillis();
		this.is = null;
		this.os = null;
		//Initializing the background array to Floor
		for(int i = 0; i < this.row; i++)
			for(int j = 0; j < this.col; j++)
				this.board[i][j] = new Floor();	
	}
	
	
	public Level(Level level) 
	{
		this.row = level.row;
		this.col = level.col;
		this.itemsOnBoard = level.itemsOnBoard;
		this.board = level.board;
		this.players = level.players;
		this.boxes = level.boxes;
		this.targets = level.targets;
		this.playersSteps = level.playersSteps;
		this.policy = level.policy;
		this.time = System.currentTimeMillis();
		this.is = null;
		this.os = null;
	}

	/**
	* GetItemsOnBoard.
	* 
	* @return the 2D array of movable items
	*/
	public iMoveable[][] getItemsOnBoard() 
	{
		return itemsOnBoard;
	}

	/**
	* SetItemsOnBoard.
	* 
	* @param itemsOnBoard 
	* 			the current level time
	*/
	public void setItemsOnBoard(iMoveable[][] itemsOnBoard) 
	{
		this.itemsOnBoard = itemsOnBoard;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	/**
	* GetTime.
	* 
	* @return thea 2D array of movable items
	*/
	public long getTime()
	{
		return time;
	}

	/**
	* SetTime.
	* 
	* @param time 
	* 			level time
	*/
	public void setTime(long time)
	{
		this.time = time;
	}

	/**
	* GetBoard.
	* 
	* @return the 2D array of unmovable items
	*/
	public iUnmoveable[][] getBoard() 
	{
		return board;
	}

	/**
	* SetBoard.
	* 
	* @param board 
	* 			the 2D array of unmovable items
	*/
	public void setBoard(iUnmoveable[][] board) 
	{
		this.board = board;
	}

	/**
	* GetRow.
	* 
	* @return the length of the 2D arrays
	*/	
	public int getRow() 
	{
		return row;
	}

	/**
	* SetRow.
	* 
	* @param row
	* 			the length of the 2D arrays
	*/	
	public void setRow(int row) 
	{
		this.row = row;
	}

	/**
	* GetCol.
	* 
	* @return the width of the 2D arrays
	*/	
	public int getCol() 
	{
		return col;
	}

	/**
	* SetCol.
	* 
	* @param col
	* 			the width of the 2D arrays
	*/	
	public void setCol(int col) 
	{
		this.col = col;
	}
	
	/**
	* GetPlayers.
	* 
	* @return players list
	*/	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}

	/**
	* SetPlayers.
	* 
	* @param players
	* 			players list
	*/
	public void setPlayers(ArrayList<Player> players)
	{
		this.players = players;
	}

	/**
	* GetBoxes.
	* 
	* @return boxes list
	*/
	public ArrayList<Box> getBoxes()
	{
		return boxes;
	}

	/**
	* SetBoxes.
	* 
	* @param boxes
	* 			boxes list
	*/
	public void setBoxes(ArrayList<Box> boxes)
	{
		this.boxes = boxes;
	}

	/**
	* GetTagets.
	* 
	* @return targets list
	*/
	public ArrayList<Target> getTargets()
	{
		return targets;
	}

	/**
	* SetTagets.
	* 
	* @param targets
	* 			targets list
	*/
	public void setTargets(ArrayList<Target> targets)
	{
		this.targets = targets;
	}

	/**
	* GetPlayersSteps.
	* 
	* @return player's steps
	*/
	public int getPlayersSteps()
	{
		return playersSteps;
	}

	/**
	* SetPlayersSteps.
	* 
	* @param playersSteps
	* 			player's steps
	*/
	public void setPlayersSteps(int playersSteps)
	{
		this.playersSteps = playersSteps;
	}

	/**
	* SetMovableItemInIndex.
	* 
	* @param mov
	* 			movable item
	*/
	public void setMovableItemInIndex(iMoveable mov)
	{
		this.itemsOnBoard[mov.getPosition().getX()][mov.getPosition().getY()] = mov;
	}
	
	/**
	* SetUnmovableItemsInIndex.
	* 
	* @param unmov
	* 			 unmovable item
	*/
	public void setUnmovableItemsInIndex(iUnmoveable unmov)
	{
		this.board[unmov.getPosition().getX()][unmov.getPosition().getY()] = unmov;
	}	
	
	/**
	* GetPolicy.
	* 
	* @return policy
	*/
	public MySokobanPolicy getPolicy()
	{
		return policy;
	}

	/**
	* SetPolicy.
	* 
	* @param policy
	* 			sokoban policy
	*/
	public void setPolicy(MySokobanPolicy policy)
	{
		this.policy = policy;
	}

	/**
	* AddBoxToBoxes.
	* 
	* @param move
	* 			movable item
	*/
	public void addBoxToBoxes(iMoveable move)
	{
		this.boxes.add((Box)move);
	}
	
	/**
	* AddPlayerToPlayers.
	* 
	* @param move
	* 			movable item
	*/
	public void addPlayerToPlayers(iMoveable move)
	{
		this.players.add((Player)move);
	}
	
	/**
	* AddTargetToTargets.
	* 
	* @param unmov
	* 			unmovable item
	*/
	public void addTargetToTargets(iUnmoveable unmov)
	{
		this.targets.add((Target)unmov);
	}
	
	/**
	* IsBoxOnTarger.
	* 
	* @param box
	* 			box
	* 
	* @return true/false if a box is on target
	*/
	public boolean isBoxOnTarget(Box box)
	{
		for(int i=0;i<this.numOfTargets();i++)
			if(box.getPosition().isEqualPosition(getTargets().get(i).getPosition()) == true)
				return true;
		return false;
	}
	
	/**
	* IsBoxOnTarger.
	* 
	* @return numbers of boxes in targets
	*/
	public int numOfBoxesInTarget()
	{
		int count = 0;
		for(int i = 0; i <this.numOfBoxes(); i++)
			if(this.getBoxes().get(i).getIsBoxInTarget() == true)
				count++;
		
		return count;
	}
	
	/**
	* IsBoxOnTarger.
	* 
	* @param move
	* 			movable item
	* @param position
	* 			new position
	* @return true/false if the user can move to the position
	*/
	public boolean isCanMove(iMoveable move, Position position)
	{
		if((this.board[position.getX()][position.getY()]) instanceof Floor)
			return true;
		
		return false;
	}
	
	/**
	* IsFinished.
	* 
	* @return true/false if the level is finish (if the user win)
	*/
	public Boolean isFinished() 
	{
		if (numOfBoxesInTarget() == numOfTargets())
			return true;
		
		return false;
	}

	/**
	* IsLevelSolvable.
	* 
	* @return true/false if the level is solvable
	*/
	public Boolean isLevelSolvable()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* NumOfBoxes.
	* 
	* @return number of boxes 
	*/
	public int numOfBoxes()
	{
		return this.boxes.size();
	}

	/**
	* NumOfPlayers.
	* 
	* @return number of players 
	*/
	public int numOfPlayers()
	{
		return this.players.size();
	}

	/**
	* NumOfTargets.
	* 
	* @return number of targets 
	*/
	public int numOfTargets()
	{
		return this.targets.size();
	}
	
	/*
	@Override
	public String toString()
	{
		return this.boxes.toString();
	}
	 */
	/**
	* IsLevelCorrect.
	* 
	* @return true/false if the level is correct by checking if the number of boxes is equal the number of targets. 
	*/
//	public boolean isLevelCorrect()
//	{
//		if(this.policy.getIsNumOfBoxesEqualToNumOfTargets() == true)
//			if(this.getBoxes().size() != this.getTargets().size())
//				return false;
//		
//		return true;
//	}
	
	/**
	* GetItemInPosition.
	* 
	* @param position
	* 			position of an item
	* @return the item 
	*/
	public iGeneralItem getItemInPosition(Position position)
	{
		iGeneralItem item;		
		if(itemsOnBoard[position.getX()][position.getY()] != null)
			item = (iMoveable) itemsOnBoard[position.getX()][position.getY()];
		else
			item = (iUnmoveable) board[position.getX()][position.getY()];		
		return item;
	}

	/**
	* IsValidPosition.
	* 
	* @param position
	* 			position of an item
	* @return true/false if the position is correct
	*/
	public boolean isValidPosition(Position position)
	{
		if((position.getX() < this.col) && (position.getX() > 0))
			return true;

		if((position.getY() < this.row) && (position.getY() > 0))
			return true;
		
		return false;
	}
	
	public boolean isEmpty()
	{
		if(this.numOfPlayers() == 0)
			return true;
		
		return false;
	}
	
	/**
	* Move.
	* 
	* @param moveType
	* 			move type {up,down,right,left}
	*/
	public void Move(iMoveable item, Position newPos) throws Exception
	{			
		Position backupItemPos = new Position(item.getPosition());
		iGeneralItem itemInNewPos = this.getItemInPosition(newPos);

		if(itemInNewPos instanceof Floor)
		{
			this.getItemsOnBoard()[newPos.getX()][newPos.getY()] = item;
			if(item instanceof Player)
				((Player) item).setPosition(newPos);
			else
				((Box) item).setPosition(newPos);
		}
		
		else if(itemInNewPos instanceof Box)
		{
			Position deltaPos = new Position(this.getPolicy().getDeltaPosition(newPos, item.getPosition()));
			
			Position boxNextPos = new Position(itemInNewPos.getPosition().getX() + deltaPos.getX(), itemInNewPos.getPosition().getY() + deltaPos.getY());
			
			iGeneralItem itemForBox = this.getItemInPosition(boxNextPos);
		
			((Box) itemInNewPos).setPosition(boxNextPos);
			this.itemsOnBoard[boxNextPos.getX()][boxNextPos.getY()] = (Box) itemInNewPos;
			
			
			if(itemForBox instanceof Target)
				((Box) itemInNewPos).setIsBoxInTarget(true);
			
		}
		
		if(item instanceof Player)
		{
			this.itemsOnBoard[newPos.getX()][newPos.getY()] = (Player) item;
			((Player) item).setPosition(newPos);
			this.players.get(0).setPosition(newPos);
		}
		
		else
		{
			this.itemsOnBoard[newPos.getX()][newPos.getY()] = (Box) item;
			((Box) item).setPosition(newPos);
		}
	
		this.getItemsOnBoard()[backupItemPos.getX()][backupItemPos.getY()] = null;
		this.playersSteps++;

	}	
	
}
