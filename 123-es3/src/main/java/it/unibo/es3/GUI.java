package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new LinkedHashMap<>();
    private final Logics logics;

    /**
     * Constructor.
     *
     * @param size the size of the grid
     */
    public GUI(final int size) {
        this.logics = new LogicsImpl(size);
        // JFrame settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Layout
        final var panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        final var moveButton = new JButton(">");
        this.getContentPane().add(BorderLayout.SOUTH, moveButton);
        // Button action
        moveButton.addActionListener(e -> {
            logics.tick();
            this.updateView();
            if (logics.isOver()) {
                dispose();
            }
        });
        // Create grid buttons
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(j, i));
                panel.add(jb);
            }
        }
        this.updateView();
        pack();
        this.setVisible(true);
    }

    private void updateView() {
        final Set<Pair<Integer, Integer>> positions = logics.getPositions();
        cells.forEach((b, p) -> b.setText(positions.contains(p) ? "*" : " "));
    }
}
