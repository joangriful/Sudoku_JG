@startuml

' Diagrama de clases del proyecto Sudoku Java

class Sudoku {
  - int[][] tablero
  - boolean[][] celdasFijas
  - int[][] solucion
  + generarTablero(String dificultad)
  + esMovimientoValido(int fila, int col, int valor): boolean
  + colocarNumero(int fila, int col, int valor): boolean
  + colocarNumeroConExcepcion(int fila, int col, int valor)
  + estaResuelto(): boolean
}

class GeneradorSudoku {
  - int[] generarNumerosAleatorios(): int[]
  - copiarMatriz(int[][] origen, int[][] destino)
  - generarSolucion(int[][] tablero): boolean
  - eliminarCeldas(...)
  + generar(Sudoku sudoku, String dificultad)
}

class SudokuPanel {
  - JTextField[][] campos
  - Sudoku sudoku
  - JLabel mensajeLabel
  - JLabel erroresLabel
  - int erroresRestantes
  - Runnable onSudokuResuelto
  + setSudoku(Sudoku sudoku)
  + getCampos(): JTextField[][]
}

class ControlPanel {
  - JButton btnPista, btnComprobar, btnReiniciar
  - JComboBox<String> dificultadCombo
  + setPistasRestantes(int cantidad)
  + getDificultadSeleccionada(): String
  + add*Listener(...)
}

class SudokuWindow {
  - SudokuPanel sudokuPanel
  - ControlPanel controlPanel
  - EstadoPanel estadoPanel
  - TemporizadorVista temporizador
  + getSudokuPanel(): SudokuPanel
  + getControlPanel(): ControlPanel
  + getEstadoPanel(): EstadoPanel
  + getTemporizador(): TemporizadorVista
}

class EstadoPanel {
  - JLabel erroresLabel
  - JLabel mensajeLabel
  + mostrarMensajeError(String)
  + mostrarMensajeInfo(String)
  + mostrarMensajeExito(String)
}

class TemporizadorVista {
  - JLabel etiqueta
  + actualizarTiempo(int segundos)
  + getEtiqueta(): JLabel
}

class TemporizadorLogica {
  - int segundosRestantes
  - Timer timer
  + iniciar()
  + detener()
  + reiniciar(int)
  + getSegundosRestantes(): int
}

class SudokuControlador {
  - Sudoku sudoku
  - SudokuPanel sudokuPanel
  - ControlPanel controlPanel
  - EstadoPanel estadoPanel
  - TemporizadorLogica temporizador
  - int pistasRestantes
  + iniciar()
  + usarPista()
  + comprobar()
  + cambiarDificultad()
  + reiniciar()
}

class MovimientoInvalidoException {
  + MovimientoInvalidoException(int fila, int columna, int valor)
}

class SudokuException {
  + SudokuException(String mensaje)
}

class VistaUtils {
  + estilizarBoton(...)
  + crearComboBox(...)
}

Sudoku --> GeneradorSudoku
SudokuWindow --> SudokuPanel
SudokuWindow --> ControlPanel
SudokuWindow --> EstadoPanel
SudokuWindow --> TemporizadorVista
SudokuPanel --> Sudoku
SudokuControlador --> Sudoku
SudokuControlador --> SudokuPanel
SudokuControlador --> ControlPanel
SudokuControlador --> EstadoPanel
SudokuControlador --> TemporizadorLogica
MovimientoInvalidoException --|> SudokuException

@enduml
