package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.board.ConveyorBelts.IConveyorBelt;

import java.util.ArrayList;

/**
 * The main structure that contains the game board.
 * As of right now it should only keep a list of the game squares,
 * be able to access those squares and return height and width
 *
 * The height and width should be final after construction (at least for now)
 */
public interface IBoard {
    /**
     * Returns the height of the board
     * @return height
     */
    int getHeight();

    /**
     * Returns the width of the board
     * @return width
     */

    int getWidth();

    /**
     * Returns the map
     *
     * @return the map
     */
    TiledMap getMap();

    /**
     * Returns a Laser from a square, given the x and y coordinates, will return null if there is no laser
     *
     * @param x the x coordinate of the square to be checked
     * @param y the y coordinate of the square to be checked
     * @return Laser, if there is one
     */
    ILaser getLaser(int x, int y);

    /**
     * If there is a conveyor belt in the given position, it constructs and returns a corresponding object
     *
     * @param x the x coordinate of the square to be checked
     * @param y the y coordinate of the square to be checked
     * @return ConveyorBelt object, if it's present on the board
     */
    IConveyorBelt getConveyorBelt(int x, int y);

    /**
     * Returns a BoardElement from a square, given the x and y coordinate, will return null if it's a normal square
     *
     * @param x the x coordinate of the square to be checked
     * @param y the y coordinate of the square to be checked
     * @return the type of BoardElement in the square, or null if none are present
     */
    BoardElement getBoardElement(int x, int y);

    /**
    * Returns all (if any) walls in a square
    *
    * @param x the x coordinate of the square to check
    * @param y the y coordinate of the square to check
    * @return a list of wall BoardElements or null if none are present
    */
    ArrayList<BoardElement> getWalls(int x, int y);
}
