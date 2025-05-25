package sudoku.modelo;

import java.util.Random;

public class GeneradorSudoku {

    private static final int VACIAS_FACIL = 30;
    private static final int VACIAS_MEDIO = 40;
    private static final int VACIAS_DIFICIL = 50;

    /**
     * Genera números aleatorios del 1 al 9 en orden aleatorio.
     */
    private int[] generarNumerosAleatorios() {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();
        for (int i = 0; i < numeros.length; i++) {
            int j = rand.nextInt(numeros.length);
            int temp = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temp;
        }
        return numeros;
    }

    /**
     * Genera un tablero válido de Sudoku y lo modifica según la dificultad.
     */
    public void generar(Sudoku sudoku, String dificultad) {
        int[][] tablero = sudoku.getTablero();
        boolean[][] celdasFijas = sudoku.getCeldasFijas();

        int[][] solucion = new int[9][9];
        generarSolucion(solucion);             // Genera solución válida
        copiarMatriz(solucion, tablero);       // Copia al tablero inicial
        eliminarCeldas(tablero, celdasFijas, dificultad); // Aplica dificultad

        sudoku.setSolucion(solucion);          // Guarda solución completa
    }

    /**
     * Copia una matriz en otra.
     */
    private void copiarMatriz(int[][] origen, int[][] destino) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                destino[i][j] = origen[i][j];
    }

    /**
     * Algoritmo backtracking para generar un tablero válido completo.
     */
    private boolean generarSolucion(int[][] tablero) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[fila][col] == 0) {
                    int[] numeros = generarNumerosAleatorios();
                    for (int num : numeros) {
                        if (esSeguro(tablero, fila, col, num)) {
                            tablero[fila][col] = num;
                            if (generarSolucion(tablero)) return true;
                            tablero[fila][col] = 0;
                        }
                    }
                    return false; // No se pudo completar desde esta posición
                }
            }
        }
        return true; // Tablero completo
    }

    /**
     * Verifica si es seguro colocar un número en una celda.
     */
    private boolean esSeguro(int[][] t, int fila, int col, int num) {
        for (int i = 0; i < 9; i++)
            if (t[fila][i] == num || t[i][col] == num) return false;

        int startRow = fila / 3 * 3, startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++)
            for (int j = startCol; j < startCol + 3; j++)
                if (t[i][j] == num) return false;

        return true;
    }

    /**
     * Elimina celdas del tablero según la dificultad y actualiza el array de celdas fijas.
     */
    private void eliminarCeldas(int[][] t, boolean[][] fijas, String dificultad) {
        int vacias = switch (dificultad.toLowerCase()) {
            case "facil" -> VACIAS_FACIL;
            case "medio" -> VACIAS_MEDIO;
            case "dificil" -> VACIAS_DIFICIL;
            default -> VACIAS_FACIL;
        };

        Random rand = new Random();
        while (vacias > 0) {
            int fila = rand.nextInt(9), col = rand.nextInt(9);
            if (t[fila][col] != 0) {
                t[fila][col] = 0;
                fijas[fila][col] = false;
                vacias--;
            }
        }

        // Marca las demás como fijas
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (t[i][j] != 0) fijas[i][j] = true;
    }
}
