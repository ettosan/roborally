package inf112.skeleton.app.card;

/**
 * Construction of cards
 * Methods : getType and getPriority
 */
public interface ICard {

    /**
     * gets the type of the card (Icardmovement or Icardrotation)
     * @return int 1 if movement or 2 if rotation
     */
    int getType();

    /**
     * Gets the priority number of the card
     */
    int getPriority();


}
