package it.unibo.es3;

import java.util.Set;

/**
 * Logics interface.
 */
public interface Logics {

    /**
     * Advance the game by one tick.
     */
    void tick();

    /**
     * Check if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isOver();

    /**
     * Get the positions of the active cells.
     *
     * @return a set of pairs representing the positions of the active cells
     */
    Set<Pair<Integer, Integer>> getPositions();

}
