package inf112.skeleton.app.board;

import java.util.Arrays;
import java.util.List;

public enum BoardElement {
    FLAG1(null, 1),
    FLAG2(null, 2),
    FLAG3(null, 3),
    FLAG4(null, 4) ,
    CONVEYORBELT(null, -1),
    GEAR_LEFT(null, -1),
    GEAR_RIGHT(null, -1),
    HOLE(null, -1),
    WRENCH(null, -1),
    SPECIAL_WRENCH(null, -1),
    STARTING_POSITION_1(null, 1),
    STARTING_POSITION_2(null, 2),
    STARTING_POSITION_3(null, 3),
    STARTING_POSITION_4(null, 4),
    STARTING_POSITION_5(null, 5),
    STARTING_POSITION_6(null, 6),
    STARTING_POSITION_7(null, 7),
    STARTING_POSITION_8(null, 8),
    PUSHER_EVEN_NORTH(Direction.NORTH, -1),
    PUSHER_EVEN_EAST(Direction.EAST, -1),
    PUSHER_EVEN_SOUTH(Direction.SOUTH, -1),
    PUSHER_EVEN_WEST(Direction.WEST, -1),
    PUSHER_ODD_NORTH(Direction.NORTH, -1),
    PUSHER_ODD_EAST(Direction.EAST, -1),
    PUSHER_ODD_SOUTH(Direction.SOUTH, -1),
    PUSHER_ODD_WEST(Direction.WEST, -1),
    WALL_NORTH(Direction.NORTH, -1),
    WALL_EAST(Direction.EAST, -1),
    WALL_SOUTH(Direction.SOUTH, -1),
    WALL_WEST(Direction.WEST, -1);

    private final Direction direction;
    private final int value;

    BoardElement(Direction dir, int value) {
        this.direction = dir;
        this.value = value;
    }

    /**
     * Returns the elements direction, if any
     * @return direction or null if empty
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the value
     * @return value an integer valuable
     */
    public int getValue() {
        return value;
    }

    /**
     * Contains the wrenches
     */
    public static final List<BoardElement> WRENCHES = Arrays.asList(WRENCH, SPECIAL_WRENCH);

    /**
     * Contains all of the flags
     */
    public static final List<BoardElement> FLAGS = Arrays.asList(FLAG1, FLAG2, FLAG3, FLAG4);

    /**
     * Contains all of the starting points
     */
    public static final List<BoardElement> STARTING_POINTS = Arrays.asList(STARTING_POSITION_1, STARTING_POSITION_2,
            STARTING_POSITION_3, STARTING_POSITION_4, STARTING_POSITION_5, STARTING_POSITION_6, STARTING_POSITION_7,
            STARTING_POSITION_8);

    /**
     * Contains all of the pushers
     */
    public static final List<BoardElement> PUSHERS = Arrays.asList(PUSHER_EVEN_NORTH, PUSHER_EVEN_EAST,
            PUSHER_EVEN_SOUTH, PUSHER_EVEN_WEST, PUSHER_ODD_NORTH, PUSHER_ODD_EAST, PUSHER_ODD_SOUTH, PUSHER_ODD_WEST);

    /**
     * Contains all of the pushers that activate on even phases
     */
    public static final List<BoardElement> PUSHERS_EVEN = PUSHERS.subList(0,4);

    /**
     * Contains all of the pushers that activate on odd phases
     */
    public static final List<BoardElement> PUSHERS_ODD = PUSHERS.subList(4,8);

    /**
     * Contains all of the walls
     */
    public static final List<BoardElement> WALLS = Arrays.asList(WALL_NORTH, WALL_EAST, WALL_SOUTH, WALL_WEST);

    /**
     * Contains the gears
     */
    public static final List<BoardElement> GEARS = Arrays.asList(GEAR_LEFT, GEAR_RIGHT);
}
