package inf112.skeleton.app.card;

/**
 * A fairly straightforward implementation of the ICardRotation interface
 */
public class RotationCard implements ICardRotation {
    private final int type = 2;
    private int priority;
    private boolean rotationDirection;
    private int rotationValue;

    /**
     * Constructor for the rotation cards
     *
     * @param priority the priority of the card
     * @param rotationDirection the direction of the card (TRUE = right, FALSE = left)
     * @param rotationValue the amount of rotation to be performed, 1 = 90 degrees
     */
    public RotationCard(int priority, boolean rotationDirection, int rotationValue) {
        this.priority = priority;
        this.rotationDirection = rotationDirection;
        this.rotationValue = rotationValue;
    }

    @Override
    public boolean getRotationDirection() {
        return rotationDirection;
    }

    @Override
    public int getRotationValue() {
        return rotationValue;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
