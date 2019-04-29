package inf112.skeleton.app.board.ConveyorBelts;

import inf112.skeleton.app.board.Direction;

public class ConveyorStraight implements IConveyorBelt{
    //Doesn't have it's own interface because it has no special effects
    private Direction dir;
    private int moveValue;
    private int[] position;

    /**
     * @param dir The direction the conveyorbelt should move robots
     * @param moveValue The amount of times a conveyorbelt should be activated per phase
     * @param position the coordinates of the conveyorbelt
     */
    public ConveyorStraight(Direction dir, int moveValue, int[] position) {
        this.dir = dir;
        this.moveValue = moveValue;
        this.position = position;
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
