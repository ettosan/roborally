package inf112.skeleton.app.board;

/**
 * Represents a flag
 */
public interface IFlag {

    /**
     * Returns the coordinates for the flag at the board
     * @return coordinates
     */
    int[] getPosition();

    /**
     * Returns the identification number of the flag
     * @return flagId
     */
    int getFlagId();

}