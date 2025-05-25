package sudoku.vista;

import sudoku.controlador.SudokuControlador;

import javax.swing.*;
import java.awt.*;

public class SudokuWindow extends JFrame {
    private final SudokuPanel sudokuPanel;
    private final ControlPanel controlPanel;
    private final EstadoPanel estadoPanel;
    private final TemporizadorVista temporizadorVista;

    public SudokuWindow(String dificultad) {
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear paneles visuales (sin lógica)
        estadoPanel = new EstadoPanel();
        controlPanel = new ControlPanel(dificultad);
        controlPanel.setPreferredSize(new Dimension(180, 100));
        temporizadorVista = new TemporizadorVista();
        sudokuPanel = new SudokuPanel(dificultad, this::manejarVictoria, estadoPanel);

        // Agregar componentes al layout
        add(temporizadorVista.getEtiqueta(), BorderLayout.NORTH);
        add(sudokuPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);
        add(estadoPanel, BorderLayout.SOUTH);

        // Iniciar controlador con lógica del temporizador
        new SudokuControlador(this).iniciar();

        setVisible(true);
    }

    private void manejarDerrota() {
        JOptionPane.showMessageDialog(this, "⏱ Tiempo agotado. Has perdido.");
        System.exit(0);
    }

    private void manejarVictoria() {
        JOptionPane.showMessageDialog(this, "✅ ¡Has completado el Sudoku correctamente!");
        System.exit(0);
    }

    // Getters para el controlador
    public SudokuPanel getSudokuPanel() {
        return sudokuPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public EstadoPanel getEstadoPanel() {
        return estadoPanel;
    }

    public TemporizadorVista getTemporizadorVista() {
        return temporizadorVista;
    }
}
