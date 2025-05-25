package sudoku.controlador;

import sudoku.modelo.Sudoku;
import sudoku.vista.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuControlador {
    private final Sudoku sudoku;
    private final SudokuPanel sudokuPanel;
    private final ControlPanel controlPanel;
    private final EstadoPanel estadoPanel;
    private final TemporizadorVista temporizadorVista;
    private final TemporizadorLogica temporizador;
    private int pistasRestantes;

    public SudokuControlador(SudokuWindow ventana) {
        this.controlPanel = ventana.getControlPanel();
        this.sudokuPanel = ventana.getSudokuPanel();
        this.estadoPanel = ventana.getEstadoPanel();
        this.temporizadorVista = ventana.getTemporizadorVista();

        // Crear el modelo y generar tablero
        this.sudoku = new Sudoku();
        String dificultad = controlPanel.getDificultadSeleccionada();
        sudoku.generarTablero(dificultad);

        sudokuPanel.setSudoku(sudoku);
        pistasRestantes = dificultad.equals("facil") ? 5 : dificultad.equals("medio") ? 3 : 0;
        controlPanel.setPistasRestantes(pistasRestantes);

        // Crear lógica del temporizador
        final TemporizadorLogica[] tempHolder = new TemporizadorLogica[1];

        tempHolder[0] = new TemporizadorLogica(
                1000,
                () -> temporizadorVista.actualizarTiempo(tempHolder[0].getSegundosRestantes()),
                this::manejarDerrota
        );

        this.temporizador = tempHolder[0];




        inicializarEventos();
    }

    public void iniciar() {
        temporizador.iniciar();
    }

    private void inicializarEventos() {
        controlPanel.addPistaListener(e -> usarPista());
        controlPanel.addReiniciarListener(e -> reiniciar());
        controlPanel.addComprobarListener(e -> comprobar());
        controlPanel.addDificultadListener(e -> cambiarDificultad());
    }

    private void usarPista() {
        int[][] solucion = sudoku.getSolucion();
        int[][] tablero = sudoku.getTablero();

        List<int[]> vacias = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] == 0) vacias.add(new int[]{i, j});
            }
        }

        if (pistasRestantes <= 0) {
            estadoPanel.mostrarMensajeInfo("⚠️ No te quedan pistas.");
            return;
        }

        if (vacias.isEmpty()) {
            estadoPanel.mostrarMensajeInfo("El tablero ya está completo.");
            return;
        }

        pistasRestantes--;
        controlPanel.setPistasRestantes(pistasRestantes);

        Random rand = new Random();
        int[] pos = vacias.get(rand.nextInt(vacias.size()));
        int fila = pos[0];
        int col = pos[1];
        int valor = solucion[fila][col];

        tablero[fila][col] = valor;
        sudokuPanel.getCampos()[fila][col].setText(String.valueOf(valor));
        sudokuPanel.getCampos()[fila][col].setEditable(false);
        sudokuPanel.getCampos()[fila][col].setBackground(new java.awt.Color(204, 229, 255));

        estadoPanel.mostrarMensajeInfo("🔍 Pista usada en [" + (fila + 1) + "," + (col + 1) + "]");
    }

    private void comprobar() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField campo = sudokuPanel.getCampos()[i][j];
                if (campo.isEditable()) {
                    campo.requestFocus();
                    campo.transferFocus();
                }
            }
        }

        SwingUtilities.invokeLater(() -> {
            if (sudoku.estaResuelto()) {
                manejarVictoria();
            } else {
                estadoPanel.mostrarMensajeError("❌ El Sudoku no está completo o hay errores.");
            }
        });
    }

    private void manejarVictoria() {
        temporizador.detener();
        JOptionPane.showMessageDialog(null, "✅ ¡Sudoku resuelto correctamente!");
        salir();
    }

    private void manejarDerrota() {
        JOptionPane.showMessageDialog(null, "⏱ Tiempo agotado. Has perdido.");
        salir();
    }

    private void reiniciar() {
        cambiarVentana();
    }

    private void cambiarDificultad() {
        cambiarVentana();
    }

    private void cambiarVentana() {
        String dif = controlPanel.getDificultadSeleccionada();
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(controlPanel);
        new SudokuWindow(dif);
        ventana.dispose();
    }

    private void salir() {
        System.exit(0);
    }
}
