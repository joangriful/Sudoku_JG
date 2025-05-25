# Sudoku Java - Proyecto Final

Este proyecto implementa un juego completo de Sudoku en Java con interfaz gráfica usando Swing. El sistema incluye generación automática de tableros para distintos niveles de dificultad, validación de movimientos, control de errores, pistas, cronómetro, arquitectura MVC y pruebas unitarias con JUnit 5.

---

## 🌐 Características
- Tableros generados aleatoriamente por nivel: fácil, medio, difícil.
- Validación en tiempo real de movimientos.
- Manejo de errores con retroalimentación visual.
- Sistema de pistas limitado según dificultad.
- Temporizador configurable con acción al terminar el tiempo.
- Arquitectura limpia basada en MVC.
- Excepciones personalizadas.
- Pruebas unitarias exigentes con JUnit 5.

---

## 🔺 Estructura de Carpetas
src/
├── sudoku/
│ ├── modelo/ # Lógica del juego (modelo)
│ │ ├── Sudoku.java
│ │ └── GeneradorSudoku.java
│ ├── vista/ # Interfaz gráfica (Swing)
│ │ ├── SudokuPanel.java
│ │ ├── ControlPanel.java
│ │ ├── SudokuWindow.java
│ │ ├── EstadoPanel.java
│ │ └── TemporizadorVista.java
│ ├── controlador/ # Controlador principal
│ │ └── SudokuControlador.java
│ ├── utils/ # Utilidades visuales y estilos
│ │ └── VistaUtils.java
│ └── excepciones/ # Excepciones propias
│ ├── SudokuException.java
│ └── MovimientoInvalidoException.java

└── test/
├── sudoku/modelo/
│ ├── SudokuTest.java
│ └── GeneradorSudokuTest.java



---

## 🔄 Flujo de Ejecución
- `SudokuWindow`: crea la ventana y los componentes.
- `SudokuControlador`: conecta eventos de la vista con la lógica.
- `Sudoku`: modelo de datos con validación y resolución.
- `GeneradorSudoku`: genera la solución y oculta celdas según dificultad.

---

## 🔧 Clases y Métodos Principales

### `Sudoku`
- `generarTablero(String dificultad)`
- `esMovimientoValido(int fila, int col, int valor)`
- `colocarNumero(...)`
- `colocarNumeroConExcepcion(...)`
- `estaResuelto()`

### `GeneradorSudoku`
- `generar(Sudoku sudoku, String dificultad)`
- `generarSolucion(...)`
- `eliminarCeldas(...)`

### `SudokuControlador`
- `iniciar()`
- `usarPista()`
- `comprobar()`
- `cambiarDificultad()`
- `reiniciar()`

### `SudokuPanel`
- `setSudoku(Sudoku sudoku)`
- `validarEntrada(...)`
- `resaltarZona(...)`
- `limpiarResaltado()`
- `desactivarTablero()`

### `ControlPanel`
- `addPistaListener(...)`
- `addComprobarListener(...)`
- `addReiniciarListener(...)`
- `addDificultadListener(...)`

### `TemporizadorVista`
- `getEtiqueta()`
- `actualizarTiempo(int segundos)`

### `VistaUtils`
- `estilizarBoton(...)`
- `crearComboBox(...)`

### `Excepciones`
- `SudokuException`: base para todas las excepciones del juego.
- `MovimientoInvalidoException`: se lanza si el jugador hace un movimiento inválido.

---

## 🧪 Pruebas Unitarias (JUnit 5)

### `SudokuTest`
- Validación de movimientos válidos e inválidos.
- Colocación en celdas fijas y verificación de solución.

### `GeneradorSudokuTest`
- Comprobación de generación de tableros según dificultad.
- Verificación de que la solución es válida (sin duplicados).

---

## 🚀 Ejecución

### Compilar y ejecutar:
```bash
mvn clean compile exec:java -Dexec.mainClass="sudoku.vista.SudokuWindow"

