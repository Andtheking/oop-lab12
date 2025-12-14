package it.unibo.es2;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Logics implementation.
 */
public class LogicsImpl implements Logics {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String STATE_OFF = " ";
    private static final String STATE_ON = "*";
    private final Map<Pair<Integer, Integer>, String> map;
    private final int size;

    /**
     * Initialize the grid.
     * 
     * @param size
     *            Area of the grid.
     */
    public LogicsImpl(final int size) {
        this.size = size;
        map = new LinkedHashMap<>();
        for (int iR = 0; iR < size; iR++) {
            for (int iC = 0; iC < size; iC++) {
                map.put(new Pair<>(iR, iC), STATE_OFF);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hit(final Pair<Integer, Integer> position) {
        map.replace(position, invert(map.get(position)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimeToQuit(final Pair<Integer, Integer> position) {
        boolean row = true;
        boolean col = true;
        for (int i = 0; i < size; i++) {
            if (isPositionOff(new Pair<>(position.x(), i))) {
                row = false;
            }
            if (isPositionOff(new Pair<>(i, position.y()))) {
                col = false;
            }
        }
        return row || col;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPosition(final Pair<Integer, Integer> position) {
        return map.get(position);
    }

    private boolean isPositionOff(final Pair<Integer, Integer> position) {
        return STATE_OFF.equals(getPosition(position));
    }

    private String invert(final String s) {
        return switch (s) {
            case STATE_OFF -> STATE_ON;
            default -> STATE_OFF;
        };
    }
}
