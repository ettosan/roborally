package inf112.skeleton.app.game;

import java.util.Arrays;
import java.util.List;

/**
 * Contains all of the different events
 * we can add events later
 */
public enum Event {

    
    MOVE_ROBOT,
    FIRE_LASER_ROBOT,
    FIRE_LASER_BOARD,
    REPAIR,
    CONVEYORBELT,
    DEAL_PROGRAM_CARDS,
    POWER_DOWN_CHECK,
    REVEAL_PROGRAM_CARDS,
    ACTIVATE_GEARS,
    ROTATE_ROBOT,
    FLAG_CHECK,
    REMOVE_PROGRAM_CARDS,
    DESTRUCTION_CHECK,
    WIN_CHECK;

    /**
     * list of robot events
     */
    public static final List<Event> ROBOT_EVENTS = Arrays.asList(MOVE_ROBOT,ROTATE_ROBOT,FIRE_LASER_ROBOT,POWER_DOWN_CHECK);

    /**
     * list of board events
     */
    public static final List<Event> BOARD_EVENTS = Arrays.asList(FIRE_LASER_BOARD,REPAIR,FLAG_CHECK,ACTIVATE_GEARS,CONVEYORBELT);

    /**
     * list of game/logic events
     */
    public static final List<Event> GAME_EVENTS = Arrays.asList(DEAL_PROGRAM_CARDS,REVEAL_PROGRAM_CARDS,
            REMOVE_PROGRAM_CARDS,DESTRUCTION_CHECK,WIN_CHECK);
}
