package inf112.skeleton.app.board.ConveyorBelts;

import inf112.skeleton.app.board.Direction;

import java.util.Arrays;

//Uses the IConveyorTurn interface because they have the same methods, but different functionality (handled in game)
public class ConveyorInto implements IConveyorInto {
    private Direction dir;
    private int moveValue;
    private int[] position;
    private boolean turnDirection;

    /**
     * @param dir The direction the conveyorbelt should move robots
     * @param moveValue The amount of times a conveyorbelt should be activated per phase
     * @param position the coordinates of the conveyorbelt
     * @param turnDirection Direction the belt turns robot (if it should be turned), true for clockwise, false for counterclockwise
     */
    public ConveyorInto(Direction dir, int moveValue, int[] position, boolean turnDirection) {
        this.dir = dir;
        this.moveValue = moveValue;
        this.position = position;
        this.turnDirection = turnDirection;
    }

    @Override
    public boolean getTurnDirection() {
        return turnDirection;
    }

    @Override
    public Direction getDirection() {
        return dir;
    }

    @Override
    public boolean isExpressType() {
        return moveValue == 2;
    }

    @Override
    public int[] getPosition() {
        return position;
    }

    @Override
    public boolean shouldRotate(int[] previousPosition) {
        //Calculates what the "previous" position must have been for a turn to take place
        int[] tempPos = position.clone();
        if(turnDirection) {
            tempPos[0] += dir.next().getDeltaX();
            tempPos[1] += dir.next().getDeltaY();
        } else {
            tempPos[0] += dir.previous().getDeltaX();
            tempPos[1] += dir.previous().getDeltaY();
        }
        return Arrays.equals(previousPosition, tempPos);
    }
}
