package inf112.skeleton.app.board;

public class MovementBoardElement implements IMovementBoardElement {
    private int[] coordinates;
    private Direction direction;
    private int moveValue;
    private Boolean rotationDirection;

    public MovementBoardElement(int[] coordinates, Direction direction, int moveValue, Boolean rotationDirection) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.moveValue = moveValue;
        this.rotationDirection = rotationDirection;
    }

    /**
     * Return coordinates as a Array with X as the first value and Y as the second value
     *
     * @return coordinates as an int[]
     */
    @Override
    public int[] getCoordinates() {
        return coordinates;
    }

    /**
     * Return the direction of the conveyor belt points(if the element is a conveyor belt)
     *
     * @return Direction or null if not a conveyor belt
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns movement value (movement caused by the element)
     *
     * @return moveValue, or null if the element doesn't have one
     */
    @Override
    public int getMoveValue() {
        return moveValue;
    }

    /**
     * Returns the direction to rotate
     * True if rotate clockwise , False if rotate counter clockwise
     *
     * @return boolean indicating direction
     */
    @Override
    public boolean getRotationDirection() {
        return rotationDirection;
    }
}