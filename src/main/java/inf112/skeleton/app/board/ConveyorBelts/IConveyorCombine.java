package inf112.skeleton.app.board.ConveyorBelts;

public interface IConveyorCombine extends IConveyorBelt {
    /**
     * Gives you the direction to rotate a robot based on it's previous position
     * Throws an exception if the position matches neither of the directions to turn
     * @param previousPosition the previous position of the robot (before being on the belt)
     * @return true if clockwise rotation, false if counterclockwise rotation
     */
    boolean getRotationDirectionFromPreviousPosition(int[] previousPosition);
}
