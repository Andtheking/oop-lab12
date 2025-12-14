package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<Pair<Integer, Integer>, JButton> cells = new LinkedHashMap<>();

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        final Logics logics = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel panel = new JPanel(new GridLayout(width, width));
        mainPanel.add(panel, BorderLayout.CENTER);
        this.getContentPane().add(mainPanel);
        // Create buttons and add them to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final var pos = new Pair<>(j, i);
                final JButton button = new JButton(logics.getPosition(pos));
                this.cells.put(pos, button);
                panel.add(button);
            }
        }
        final JButton step = new JButton(">");
        step.addActionListener(e -> {
            logics.doStep();
            cells.entrySet().stream()
                .forEach(entry -> entry.getValue().setText(logics.getPosition(entry.getKey())));
            if (logics.isTimeToQuit()) {
                dispose();
            }
        });
        mainPanel.add(step, BorderLayout.SOUTH);
        pack();
        this.setVisible(true);
    }
}
