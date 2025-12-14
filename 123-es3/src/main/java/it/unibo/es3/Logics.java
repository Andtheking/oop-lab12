package it.unibo.es3;

/**
 * Logics for the GUI.
 */
public interface Logics {

    /**
     * Do a step.
     */
    void doStep();

    /**
     * Whether the program should quit.
     * 
     * @return
     *          Whether the program should quit.
     */
    boolean isTimeToQuit();

    /**
     * Get cell value.
     * 
     * @param position
     *                  cell to get
     * @return
     *                  cell value.
     */
    String getPosition(Pair<Integer, Integer> position);

}
