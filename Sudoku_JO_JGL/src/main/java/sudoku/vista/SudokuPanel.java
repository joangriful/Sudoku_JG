package sudoku.vista;

import sudoku.modelo.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SudokuPanel extends JPanel {
    private final JTextField[][] campos = new JTextField[9][9];
    private Sudoku sudoku;
    private final JLabel mensajeLabel;
    private final JLabel erroresLabel;
    private int erroresRestantes;
    private final Runnable onSudokuResuelto;

    public SudokuPanel(String dificultad, Runnable onSudokuResuelto, EstadoPanel estadoPanel) {
        this.mensajeLabel = estadoPanel.getMensajeLabel();
        this.erroresLabel = estadoPanel.getErroresLabel();
        this.erroresRestantes = dificultad.equals("facil") ? 8 : dificultad.equals("medio") ? 5 : 3;
        this.onSudokuResuelto = onSudokuResuelto;

        setBackground(new Color(242, 246, 252));
        setLayout(new GridLayout(9, 9, 1, 1));

        Font fuente = new Font("SansSerif", Font.BOLD, 20);

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JTextField campo = new JTextField();
                campo.setHorizontalAlignment(JTextField.CENTER);
                campo.setFont(fuente);
                campo.setBorder(BorderFactory.createMatteBorder(
                        (fila % 3 == 0 ? 3 : 1),     // borde superior más grueso cada 3 filas
                        (col % 3 == 0 ? 3 : 1),     // borde izquierdo más grueso cada 3 columnas
                        (fila == 8 ? 3 : 1),        // borde inferior más grueso al final
                        (col == 8 ? 3 : 1),         // borde derecho más grueso al final
                        new Color(160, 185, 217)    // mismo color neutro que usabas
                ));

                campo.setOpaque(true);
                campo.setBackground(Color.WHITE);
                campo.setFocusable(true);

                int f = fila, c = col;

                campo.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        validarEntrada(f, c);
                        limpiarResaltado();
                    }

                    @Override
                    public void focusGained(FocusEvent e) {
                        resaltarZona(f, c);
                    }
                });

                campo.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        resaltarZona(f, c);
                    }
                });

                campos[fila][col] = campo;
                add(campo);
            }
        }
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;

        // Actualizar celdas con los valores del tablero
        int[][] tablero = sudoku.getTablero();
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                int valor = tablero[fila][col];
                JTextField campo = campos[fila][col];

                if (valor != 0) {
                    campo.setText(String.valueOf(valor));
                    campo.setEditable(false);
                    campo.setBackground(new Color(219, 228, 237));
                } else {
                    campo.setText("");
                    campo.setEditable(true);
                    campo.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void resaltarZona(int fila, int col) {
        limpiarResaltado();
        Color resaltado = new Color(230, 240, 250);
        Color celdaActiva = new Color(204, 228, 255);

        for (int i = 0; i < 9; i++) {
            if (campos[fila][i].isEditable()) campos[fila][i].setBackground(resaltado);
            if (campos[i][col].isEditable()) campos[i][col].setBackground(resaltado);
        }

        int boxRow = fila / 3 * 3;
        int boxCol = col / 3 * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (campos[i][j].isEditable()) campos[i][j].setBackground(resaltado);
            }
        }

        if (campos[fila][col].isEditable()) campos[fila][col].setBackground(celdaActiva);
    }

    private void limpiarResaltado() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (campos[fila][col].isEditable()) {
                    campos[fila][col].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void validarEntrada(int fila, int col) {
        JTextField campo = campos[fila][col];
        if (!campo.isEditable() || sudoku == null) return;

        try {
            String texto = campo.getText().trim();
            if (texto.isEmpty()) return;

            int valor = Integer.parseInt(texto);
            if (!sudoku.esMovimientoValido(fila, col, valor)) {
                erroresRestantes--;
                mensajeLabel.setForeground(Color.RED);
                mensajeLabel.setText("❌ Número incorrecto.");
                erroresLabel.setText("Errores restantes: " + erroresRestantes);
                campo.setText("");

                if (erroresRestantes <= 0) {
                    mensajeLabel.setText("Has cometido demasiados errores.");
                    desactivarTablero();
                }

            } else {
                sudoku.colocarNumero(fila, col, valor);
                campo.setEditable(false);
                campo.setBackground(new Color(204, 255, 204));
                mensajeLabel.setForeground(new Color(0, 128, 0));
                mensajeLabel.setText("✔️ Número válido. Celda bloqueada.");

                if (sudoku.estaResuelto()) {
                    onSudokuResuelto.run();
                }
            }
        } catch (NumberFormatException e) {
            erroresRestantes--;
            mensajeLabel.setForeground(Color.RED);
            mensajeLabel.setText("⚠️ Caracter no válido. Solo números del 1 al 9.");
            erroresLabel.setText("Errores restantes: " + erroresRestantes);
            campo.setText("");

            if (erroresRestantes <= 0) {
                mensajeLabel.setText("Has cometido demasiados errores.");
                desactivarTablero();
            }
        }
    }

    private void desactivarTablero() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                campos[fila][col].setEditable(false);
            }
        }
    }

    public JTextField[][] getCampos() {
        return campos;
    }

    public int getErroresRestantes() {
        return erroresRestantes;
    }

    public void setErroresRestantes(int errores) {
        this.erroresRestantes = errores;
    }
}
