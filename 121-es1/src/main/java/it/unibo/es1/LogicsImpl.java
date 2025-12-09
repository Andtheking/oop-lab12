package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final List<Integer> values;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.values = new ArrayList<>(Collections.nCopies(size, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.values.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return Collections.unmodifiableList(values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        return values.stream().map(x -> x < values.size()).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        final int n = this.values.get(elem) + 1;
        this.values.set(elem, n);
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        return this.values.stream()
            .map(String::valueOf)
            .collect(Collectors.joining("|", "<<", ">>"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return this.values.stream().allMatch(i -> Objects.equals(i, this.values.getFirst()));
    }
}
