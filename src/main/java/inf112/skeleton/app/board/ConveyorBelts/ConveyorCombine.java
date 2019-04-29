package inf112.skeleton.app.board.ConveyorBelts;

import inf112.skeleton.app.board.Direction;

import java.util.Arrays;

public class ConveyorCombine implements IConveyorCombine {
    private Direction dir;
    private int moveValue;
    private int[] position;

    /**
     * @param dir The direction the conveyorbelt should move robots
     * @param moveValue The amount of times a conveyorbelt should be activated per phase
     * @param position the coordinates of the conveyorbelt
     */
    public ConveyorCombine(Direction dir, int moveValue, int[] position) {
        this.dir = dir;
        this.moveValue = moveValue;
        this.position = position;
    }

    @Override
    public boolean getRotationDirectionFromPreviousPosition(int[] previousPosition) {
        //The positions the robot must have been in previously to have a rotation
        int[] positionToTurnClockwise = position.clone();
        int[] positionToTurnCounterClockwise = position.clone();

        //Calculates the previous positions based on the direction it must've come from
        positionToTurnClockwise[0] += dir.next().getDeltaX();
        positionToTurnClockwise[1] += dir.next().getDeltaY();
        positionToTurnCounterClockwise[0] += dir.previous().getDeltaX();
        positionToTurnCounterClockwise[1] += dir.previous().getDeltaY();

        if(Arrays.equals(previousPosition, positionToTurnClockwise))
            return true;
        else if(Arrays.equals(previousPosition, positionToTurnClockwise))
            return false;
        else
            throw new IllegalArgumentException("The supplied position could not have lead into this conveyor belt");
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
}
