package inf112.skeleton.app.game;

/**
 * Created by mari on 02.05.2019.
 */
public enum PhaseState {
    REVEAL_CARDS,
    MAKE_MOVEMENT_PRIORITY_LIST,
    MOVE_ROBOTS,
    ACTIVATE_BOARD_ELEMENTS,
    FIRE_LASERS,
    ACTIVATE_CHECKPOINTS;

    private static PhaseState[] states = values();

    /**
     * Returns the next game state in order
     *
     * @return next phaseState
     */
    public PhaseState nextState() {
        return states[(ordinal() + 1) % states.length];

    }

}
