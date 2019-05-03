package inf112.skeleton.app.board;

public interface IMovementBoardElement {

    /**
     * Coordinates int[]
     * Direction Direction
     * MoveValue int
     * rotationDirection Boolean
     *
     */
    /**
     * Return coordinates as a Array with X as the first value and Y as the second value
     *
     * @return
     */
    int[] getCoordinates();

    /**
     * Return the direction conveyor belt
     * @return
     */
    Direction getDirection ();

    /**
     *Returns movement value
     * @return
     */

    int getMoveValue();

    /**
     * Returns the direction to rotate
     * True if rotate clockwise , False if rotate counter clockwise
     * @return
     */
    boolean getRotationDirection();
}
