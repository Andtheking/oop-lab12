package it.unibo.es3;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Logics implementation.
 */
public class LogicsImpl implements Logics, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final int gridSize;
    private final Set<Pair<Integer, Integer>> set = new LinkedHashSet<>();

    /**
     * Constructor.
     *
     * @param gridSize the size of the grid
     */
    @SuppressFBWarnings(
        value = "DMI_RANDOM_USED_ONLY_ONCE",
        justification = "False positive, see: https://github.com/spotbugs/spotbugs/issues/3830"
    )
    public LogicsImpl(final int gridSize) {
        this.gridSize = gridSize;
        final Random random = new Random(0);
        while (set.size() < 3) {
            this.set.add(new Pair<>(random.nextInt(gridSize), random.nextInt(gridSize)));
        }
    }

    private boolean neighbors(final Pair<Integer, Integer> p1, final Pair<Integer, Integer> p2) {
        return Math.abs(p1.x() - p2.x()) <= 1 && Math.abs(p1.y() - p2.y()) <= 1 && !p1.equals(p2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        final Set<Pair<Integer, Integer>> s = new LinkedHashSet<>();
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                final var p = new Pair<>(x, y);
                if (set.stream().anyMatch(pp -> neighbors(p, pp))) {
                    s.add(p);
                }
            }
        }
        set.addAll(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pair<Integer, Integer>> getPositions() {
        return Collections.unmodifiableSet(this.set);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.set.size() == this.gridSize * this.gridSize;
    }
}
