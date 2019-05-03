package inf112.skeleton.app.card;

/**
 * Describes the cards that move the robots
 * Methods : getMoveValue
 *
 */
public interface ICardMovement extends ICard{

   // int moveValue();

    /**
     * Gets move value, if negative = backup move
     * @return amount of robots movement
     */
    int getMoveValue ();


}
