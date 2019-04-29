package inf112.skeleton.app.board;

/**
 * Represents a laser
 */
public interface ILaser {
    /**
     * Returns how much damage a laser does
     * @return damage
     */
    int getDamage();

    /**
     * Returns the direction as a boolean
     * @return direction, true if vertical, false if horizontal
     */
    boolean isVertical();

    /**
     * Returns the position of the laser
     * @return int[] array, int[0] = x, int [1] = y.
     */
    int[] getPosition();
}
