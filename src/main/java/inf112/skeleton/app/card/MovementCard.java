package inf112.skeleton.app.card;

/**
 * A fairly straightforward implementation of the ICardMovement interface
 */
public class MovementCard implements ICardMovement {
    private final int type = 1;
    private int priority;
    private int moveValue;

    /**
     * Constructor for the movement cards
     *
     * @param priority the priority of the card
     * @param moveValue the movement value of the card (negative if going backwards)
     */
    public MovementCard(int priority, int moveValue) {
        this.priority = priority;
        this.moveValue = moveValue;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getMoveValue() {
        return moveValue;
    }
}
