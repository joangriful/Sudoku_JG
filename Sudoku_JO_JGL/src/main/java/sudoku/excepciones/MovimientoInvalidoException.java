package sudoku.excepciones;

public class MovimientoInvalidoException extends SudokuException {
    public MovimientoInvalidoException(int fila, int columna, int valor) {
        super("Movimiento inválido: valor " + valor + " en posición [" + fila + ", " + columna + "]");
    }
}

