package inf112.skeleton.app.robot;

import inf112.skeleton.app.board.Direction;

/**
 * The most important piece in the game,
 * represents the robots and controls their abilities
 *
 * Should store its direction, HP and ID
 */
public interface IRobot {
    /**
     * Returns the current direction the robot is facing
     *
     * @return direction
     */
    Direction getDir();

    /**
     * Changes the current direction
     *
     * @param dir, the new direction to face
     */
    void setDir(Direction dir);

    /**
     * Rotates the robot in the given direction
     *
     * @param rotateDirection, true for clockwise, false for counterclockwise
     */
    void rotate(Boolean rotateDirection);

    /**
     * Returns the ID of the robot
     *
     * @return ID
     */
    int getID();

    /**
     * returns an array of the backup
     * with coordinate x on pos 0, and coordinate y on pos 1
     *
     * @return position
     */
    int[] getBackup();


    /**
     * sets the state of the backup
     * with the coordinate x on pos 0, and coordinate y on pos 1
     *
     * @param backup
     */
    void setBackup(int[] backup);

    /**
     * returns the position of the robot as an array,
     * with x as the first value,
     * and y as the second value
     *
     * @return the position of the robot
     */
    int[] getPosition();

    /**
     * Sets the position of the robot
     *
     * @param coordinate -coordinate of current position
     */
    void setPosition(int[] coordinate);
}