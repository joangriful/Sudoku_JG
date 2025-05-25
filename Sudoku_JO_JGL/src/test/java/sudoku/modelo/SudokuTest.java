package sudoku.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    private Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = new Sudoku();
        sudoku.generarTablero("facil");
    }

    @Test
    void testColocarNumeroCorrectamente() {
        int[][] solucion = sudoku.getSolucion();
        int[][] tablero = sudoku.getTablero();
        boolean[][] fijas = sudoku.getCeldasFijas();

        outerLoop:
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!fijas[i][j]) {
                    int valorCorrecto = solucion[i][j];
                    assertTrue(sudoku.colocarNumero(i, j, valorCorrecto));
                    assertEquals(valorCorrecto, tablero[i][j]);
                    break outerLoop;
                }
            }
        }
    }

    @Test
    void testMovimientoEnCeldaFijaInvalido() {
        boolean[][] fijas = sudoku.getCeldasFijas();
        int[][] tablero = sudoku.getTablero();

        outerLoop:
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (fijas[i][j]) {
                    int original = tablero[i][j];
                    assertFalse(sudoku.colocarNumero(i, j, (original % 9) + 1));
                    break outerLoop;
                }
            }
        }
    }

    @Test
    void testEstaResueltoDevuelveFalseAlInicio() {
        assertFalse(sudoku.estaResuelto());
    }

    @Test
    void testEsMovimientoValidoDetectaDuplicadoFila() {
        int fila = 0;
        int valor = 9;
        sudoku.getTablero()[fila][0] = valor;
        assertFalse(sudoku.esMovimientoValido(fila, 1, valor));
    }

    @Test
    void testEsMovimientoValidoDetectaDuplicadoColumna() {
        int col = 0;
        int valor = 8;
        sudoku.getTablero()[0][col] = valor;
        assertFalse(sudoku.esMovimientoValido(1, col, valor));
    }

    @Test
    void testEsMovimientoValidoDetectaDuplicadoBloque() {
        sudoku.getTablero()[0][0] = 5;
        assertFalse(sudoku.esMovimientoValido(1, 1, 5));
    }
}
