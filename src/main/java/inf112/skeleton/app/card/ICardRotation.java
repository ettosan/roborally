package inf112.skeleton.app.card;

/**
 * Describes rotation movement of robot
 * methods : getRotationDirection and getRotationValue
 */
public interface ICardRotation extends ICard{

    /**
     * gets the rotation direction
     * @return true if right and false if left (can replace true/false with 0 and 1)
     */
    boolean getRotationDirection ();


    /**
     * gets the rotation value (90 degrees or 180 degrees)
     * @return 1 = 90 degrees, 2 = 180 degrees
     */
    int getRotationValue();

}
