package it.unibo.es2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Implementation of the Logics interface.
 */
public final class LogicsImpl implements Logics {

    private final List<List<Boolean>> grid = new ArrayList<>();

    /**
     * Constructs a LogicsImpl with the specified size.
     *
     * @param size the size of the grid
     */
    public LogicsImpl(final int size) {
        for (int i = 0; i < size; i++) {
            final List<Boolean> row = new ArrayList<>();
            this.grid.add(row);
            for (int j = 0; j < size; j++) {
                row.add(false);
            }
        }
    }

    private boolean gridGet(final int row, final int col) {
        return this.grid.get(row).get(col);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final int row, final int col) {
        final boolean val = !this.gridGet(row, col);
        this.grid.get(row).set(col, val);
        return val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnyRowFull() {
        return this.grid.stream().anyMatch(l -> l.stream().allMatch(b -> b));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnyColumnFull() {
        return IntStream.range(0, grid.size()).anyMatch(index ->
            this.grid.stream().allMatch(l -> l.get(index))
        );
    }

}
