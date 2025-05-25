package sudoku.modelo;

public class Sudoku {
    private int[][] tablero;
    private boolean[][] celdasFijas;
    private int[][] solucion;

    public Sudoku() {
        tablero = new int[9][9];
        celdasFijas = new boolean[9][9];
    }

    /**
     * Genera un nuevo tablero de Sudoku según la dificultad.
     */
    public void generarTablero(String dificultad) {
        GeneradorSudoku generador = new GeneradorSudoku();
        generador.generar(this, dificultad);
    }

    /**
     * Verifica si un movimiento es válido según las reglas del Sudoku.
     */
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        if (valor < 1 || valor > 9) return false;
        if (celdasFijas[fila][columna]) return false;

        // Validar fila y columna
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == valor || tablero[i][columna] == valor)
                return false;
        }

        // Validar subcuadro 3x3
        int startRow = (fila / 3) * 3;
        int startCol = (columna / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (tablero[i][j] == valor) return false;
            }
        }

        return true;
    }

    /**
     * Intenta colocar un número si es válido.
     */
    public boolean colocarNumero(int fila, int columna, int valor) {
        if (esMovimientoValido(fila, columna, valor)) {
            tablero[fila][columna] = valor;
            return true;
        }
        return false;
    }

    /**
     * Comprueba si el tablero actual coincide con la solución.
     */
    public boolean estaResuelto() {
        if (solucion == null) return false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] != solucion[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // --- Getters y Setters ---

    public int[][] getTablero() {
        return tablero;
    }

    public boolean[][] getCeldasFijas() {
        return celdasFijas;
    }

    public int[][] getSolucion() {
        return solucion;
    }

    public void setSolucion(int[][] solucion) {
        this.solucion = solucion;
    }

    /**
     * Método auxiliar para mostrar el tablero por consola (uso en depuración).
     */
    public void mostrarTablero() {
        for (int[] fila : tablero) {
            for (int valor : fila) {
                System.out.print((valor == 0 ? "." : valor) + " ");
            }
            System.out.println();
        }
    }
}
