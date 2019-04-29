package inf112.skeleton.app.game;

import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.board.IProgramRegister;
import inf112.skeleton.app.card.ICard;
import inf112.skeleton.app.card.ICardMovement;
import inf112.skeleton.app.card.ICardRotation;
import inf112.skeleton.app.graphics.GameGFX;
import inf112.skeleton.app.robot.IRobot;

import java.util.ArrayList;

/**
 * The interface contains the logic for the whole game.
 * game will implement board as field.
 */
public interface IGame {

	/**
	 * Retrieves the board from IBoard
	 * @return board from IBoard
	 */
	IBoard getBoard();


	/**
	 * Absolute movement of the robot in the current register
	 * @param coordinate the new coordinate
	 */
	void absoluteMove(IRobot robot, int[] coordinate);


	/**
	 * Relative movement (robot)
	 * @param robot to be moved
	 * @param card the movement card
	 */
	void relativeMove(IRobot robot, ICardMovement card);


	/**
	 * Checks if tile contains a robot and returns the robot
	 * @param coordinate x-ccordinate on index 0,
	 *                   y-ccordinate on index 1 on board
	 * @return IRobot robot
	 */
	IProgramRegister checkIfContainsRobot(int[] coordinate);

	/**
	 * Turns the current Robot
	 * @param robot the robot to be rotated
	 * @param card the rotation card
	 */
	void rotationMove(IRobot robot, ICardRotation card);

	/**
	 * execute a phase
	 */
	void doPhase(int phaseNumber);


	/**
	 * execute a round
	 */
	void progressRound(GameGFX GraphicsInterface);


	/**
	 * @return an ordered list of events
	 */
	ArrayList<Event> makeEventList();


	/**
	 * reads and executes events in order
	 */
	Event readEvents(ArrayList<Event> listOfEvents);


	/**
	 * repair the robot
	 * @param programRegister to be repaired
	 */
	void repair(IProgramRegister programRegister);

	/**
	 * updates the backup of the robot
	 * @param robot to update the backup of
	 */
	void updateBackUp(IRobot robot);


	/**
	 * Deals cards to all of the programRegisters
	 */
	void dealCards();


	/**
	 * first checks if there is a flag and a robot,
	 * then checks if the robot hits flags in right order.
	 * If so, updates the robot programming card.
	 * Finally, it always places a new backup.
	 *
	 */
	void activateFlag();

	/**
	 * restores the destroyed robot and places it back on the board in the
	 * backup location
	 * @param programRegister
	 */
	void restoreRobot(IProgramRegister programRegister);


	/**
	 * removes the card
	 * @param cards
	 */
	void removeCard(boolean[] cards);

	/**
	 * Activate Coveyorbelts
	 */
	void activateConveyorBelts();

	/**
	 * Adds a card to the deck
	 *
	 * @param card to be added to the deck
	 */
	void addCardToDeck(ICard card);

	/**
	 * Checks if it's possible to go from one space to the other
	 * Has to be adjacent, if not, an exception will be thrown
	 *
	 * @param startCoordinates the start of the movement
	 * @param destinationCoordinates the end of the movement
	 * @return true if possible, false if not
	 */
	boolean canMove(int[] startCoordinates, int[] destinationCoordinates);

	/**
	 * Checks if there is a wall in the given direction for the given position.
	 * @param position Position to check
	 * @param dir Direction to check
	 * @return true if there is a wall, false if there is not.
	 */
	boolean checkForWall(int[] position, Direction dir);

    /**
     * Checks if robot is on a flag-tile
     * @param robot The robot to check
     * @return true if it is on a flag, false otherwise
     */
    boolean checkIfOnFlag(IRobot robot);

	/**
	 * Activate lasers
	 * Iterate over the robots and lasers
	 * Changes HP
	 */
	void activateLasers();

	/**
	 * Activate robot lasers
	 */
	void activateRobotLasers();

    /**
     * Iterates through the registers and performs any repairs on robots on repairSites
     * @return true if it is on a repairSite, false otherwise
     */
    void doRepairs();

    /**
     * Checks if a robot has won the game
     * @return true if robot has won, false otherwise
     */
    boolean winCheck();

    /**
     * Ends the game if robot has won
     */
    void gameOver();
}
