package it.unibo.es3;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Logics implementation.
 */
public class LogicsImpl implements Logics {

    private static final String STATE_OFF = " ";
    private static final String STATE_ON = "*";
    private static final int RANDOM_START_CELLS = 3;
    private final Map<Pair<Integer, Integer>, String> map;
    private final int size;

    /**
     * Initialize cells.
     * 
     * @param size
     *              grid area.
     */
    public LogicsImpl(final int size) {
        this.size = size;
        map = new LinkedHashMap<>();
        for (int iR = 0; iR < size; iR++) {
            for (int iC = 0; iC < size; iC++) {
                map.put(new Pair<>(iR, iC), STATE_OFF);
            }
        }
        final List<Pair<Integer, Integer>> used = new LinkedList<>();
        for (int i = 0; i < RANDOM_START_CELLS; i++) {
            Pair<Integer, Integer> randomCell;
            do {
                randomCell = randomCell();
            } while (used.contains(randomCell));
            map.replace(randomCell, STATE_ON);
            used.add(randomCell);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStep() {
        map.entrySet().stream()
            .filter(e -> STATE_ON.equals(e.getValue()))
            .toList()
            .forEach(e -> hit(e.getKey()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimeToQuit() {
        return map.values().stream()
            .allMatch(STATE_ON::equals);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPosition(final Pair<Integer, Integer> position) {
        return map.get(position);
    }

    private void hit(final Pair<Integer, Integer> position) {
        for (int i = -1; i <= 1; i++) {
            for (int y = -1; y <= 1; y++) {
                map.replace(new Pair<>(position.x() + i, position.y() + y), STATE_ON);
            }
        }
    }

    @SuppressFBWarnings(value = "DMI", justification = "Random is needed only here.")
    private Pair<Integer, Integer> randomCell() {
        final Random rnd = new Random();
        return new Pair<>(rnd.nextInt(size), rnd.nextInt(size));
    }
}
