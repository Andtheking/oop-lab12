package it.unibo.es2;

/**
 * Logics for the GUI app.
 */
public interface Logics {

    /**
     * Invert a cell.
     * 
     * @param position 
     *                  which cell to invert.
     */
    void hit(Pair<Integer, Integer> position);

    /**
     * When the program should quit.
     * 
     * @param position
     *                  cell to check quit conditions
     * @return
     *                  if the program should quit.
     */
    boolean isTimeToQuit(Pair<Integer, Integer> position);

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
