package inf112.skeleton.app.board.ConveyorBelts;

/**
 * Represents the conveyorbelts that combine the straight and turn types into one
 * Extends IConveyorTurn because it uses it's method
 */
public interface IConveyorInto extends IConveyorTurn {
    /**
     * Checks if a robot moved onto the conveyorbelt should rotate based on it's previous position
     * @param previousPosition the previous position of the robot (before moving onto this belt)
     * @return true if it should rotate, false if it should not
     */
    boolean shouldRotate(int[] previousPosition);
}
