package inf112.skeleton.app.board.ConveyorBelts;

public interface IConveyorTurn extends IConveyorBelt{
    /**
     * Returns the direction the conveyorbelt turns
     * @return true if clockwise, false if counterclockwise
     */
    boolean getTurnDirection();
}
