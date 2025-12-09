package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final int size;
    private final List<NumberStatePair> values;

    /**
     * Constructor.
     *
     * @param logicsSize the size of the logics
     */
    public LogicsImpl(final int logicsSize) {
        size = logicsSize;
        values = new ArrayList<>();
        for (int i = 0; i < logicsSize; i++) {
            values.add(new NumberStatePair(0, true));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return Collections.unmodifiableList(
            values.stream()
                .map(s -> s.number)
                .toList()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        return Collections.unmodifiableList(
            values.stream()
                .map(s -> s.state)
                .toList()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        return values.get(elem).incrementNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        return values.stream()
            .map(s -> Integer.toString(s.getNumber()))
            .collect(Collectors.joining("|", "<<", ">>"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return Boolean.logicalOr(
            values.stream()
                .allMatch(s -> !s.isEnabled()),
            values.stream()
                .allMatch(s -> s.getNumber() == values.get(0).getNumber())
        );
    }

    private final class NumberStatePair {
        private int number;
        private boolean state;

        private NumberStatePair(final int number, final boolean state) {
            this.number = number;
            this.state = state;
        }

        private int getNumber() {
            return this.number;
        }

        private int incrementNumber() {
            this.number += 1;
            setState(this.number < size);
            return this.number;
        }

        private void setState(final boolean newState) {
            this.state = newState;
        }

        private boolean isEnabled() {
            return this.state;
        }
    }
}
