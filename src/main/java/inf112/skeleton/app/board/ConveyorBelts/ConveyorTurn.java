package inf112.skeleton.app.board.ConveyorBelts;

import inf112.skeleton.app.board.Direction;

public class ConveyorTurn implements IConveyorTurn {
    private Direction dir;
    private int moveValue;
    private int[] position;
    private boolean turnDirection;

    /**
     * @param dir The direction the conveyorbelt should move robots
     * @param moveValue The amount of times a conveyorbelt should be activated per phase
     * @param position the coordinates of the conveyorbelt
     * @param turnDirection Direction the belt turns robot, true for clockwise, false for counterclockwise
     */
    public ConveyorTurn(Direction dir, int moveValue, int[] position, boolean turnDirection) {
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
}
