package inf112.skeleton.app.board;

/**
 * Short and simple implementation of a Direction Enum
 * Uses the four cardinal directions as a base,
 * North, west, south, east
 */
public enum Direction {
    NORTH ("N", 0, 0, 1),
    EAST ("E", 1, 1, 0),
    SOUTH ("S", 2, 0, -1),
    WEST ("W", 3, -1, 0);

    private final String symbol;
    private final int directionValue;
    private final int deltaX;
    private final int deltaY;

    private static Direction[] vals = values();

    Direction(String symbol, int directionValue, int dx, int dy) {
        this.symbol = symbol;
        this.directionValue = directionValue;
        this.deltaX = dx;
        this.deltaY = dy;
    }

    /**
     * Returns the shorter symbol associated with the direction
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    public int getDirectionValue() {
        return  directionValue;
    }

    public int getDirectionInDegrees() {
        return directionValue * 90;
    }

    /**
     * Returns the "next" direction in a clockwise fashion
     *
     * @return next direction
     */
    public Direction next() {
        return vals[(ordinal() + 1) % vals.length];
    }

    /**
     * Returns the "previous" direction in a counterclockwise fashion
     *
     * @return previous direction
     */
    public Direction previous() {
        if(ordinal() == 0)
            return vals[vals.length - 1];
        else
            return vals[(ordinal() - 1) % vals.length];
    }

    /**
     * @return The change to your X-coordinate if you moved one step in this direction
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * @return The change to your Y-coordinate if you moved one step in this direction
     */
    public int getDeltaY() {
        return deltaY;
    }
}