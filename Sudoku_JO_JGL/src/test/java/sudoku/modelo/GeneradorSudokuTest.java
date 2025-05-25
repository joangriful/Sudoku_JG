package sudoku.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorSudokuTest {

    @Test
    void testGeneraTableroValido() {
        Sudoku sudoku = new Sudoku();
        sudoku.generarTablero("medio");

        int[][] tablero = sudoku.getTablero();
        int nonEmptyCount = 0;

        for (int[] fila : tablero) {
            for (int val : fila) {
                if (val != 0) nonEmptyCount++;
                assertTrue(val >= 0 && val <= 9);
            }
        }

        // En dificultad media, debe haber aproximadamente 41 celdas con valor
        assertTrue(nonEmptyCount >= 30 && nonEmptyCount <= 60);
    }

    @Test
    void testSolucionEsCorrecta() {
        Sudoku sudoku = new Sudoku();
        sudoku.generarTablero("facil");
        int[][] solucion = sudoku.getSolucion();

        assertTrue(verificarSolucion(solucion));
    }

    private boolean verificarSolucion(int[][] t) {
        // Fila
        for (int i = 0; i < 9; i++) {
            boolean[] seen = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int v = t[i][j];
                if (seen[v]) return false;
                seen[v] = true;
            }
        }

        // Columna
        for (int j = 0; j < 9; j++) {
            boolean[] seen = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int v = t[i][j];
                if (seen[v]) return false;
                seen[v] = true;
            }
        }

        // Cuadrantes
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                boolean[] seen = new boolean[10];
                for (int i = r; i < r + 3; i++) {
                    for (int j = c; j < c + 3; j++) {
                        int v = t[i][j];
                        if (seen[v]) return false;
                        seen[v] = true;
                    }
                }
            }
        }

        return true;
    }
}
