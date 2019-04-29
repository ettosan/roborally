package inf112.skeleton.app.game;

/**
 * A simple enum class to keep track of what the game should be doing
 */
public enum GameState {
    SETUP,
    DEALING_CARDS,
    CHOOSING_CARDS,
    ANNOUNCING_POWER_DOWN,
    EXECUTING_PHASES,
    END_OF_ROUND_CLEANUP;

    private static GameState[] states = values();

    /**
     * Returns the next game state in order
     *
     * @param includeSetup Whether the setup state should be skipped or not
     * @return next GameState
     */
    public GameState nextState(boolean includeSetup) {
        GameState nextState = states[(ordinal() + 1) % states.length];
        if(!includeSetup && nextState == GameState.SETUP)
            nextState = GameState.DEALING_CARDS;

        return nextState;
    }
}
