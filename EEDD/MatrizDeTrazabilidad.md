# 📋 Matriz de Trazabilidad - Proyecto Sudoku Java

Esta matriz relaciona los requisitos funcionales (RF) y no funcionales (RNF) con las clases, métodos y pruebas implementadas en el sistema.

| Requisito | Descripción                                                  | Clases / Métodos Relacionados                                 | Pruebas Relacionadas                      |
|-----------|--------------------------------------------------------------|----------------------------------------------------------------|-------------------------------------------|
| RF1       | Generar tablero según dificultad                             | `Sudoku.generarTablero()`<br>`GeneradorSudoku.generar()`       | `GeneradorSudokuTest.testGeneracion()`   |
| RF2       | Introducir valores en el tablero                             | `SudokuPanel.validarEntrada()`<br>`Sudoku.colocarNumero()`     | `SudokuTest.testColocacion()`            |
| RF3       | Validar movimientos en tiempo real                           | `Sudoku.esMovimientoValido()`<br>`validarEntrada()`            | `SudokuTest.testValidaciones()`          |
| RF4       | Mostrar mensajes de error/información                        | `EstadoPanel.mostrarMensaje*()`                                | Manual / Visual                           |
| RF5       | Solicitar pistas limitadas                                   | `SudokuControlador.usarPista()`<br>`ControlPanel.setPistas()`  | `SudokuTest.testPistas()`                |
| RF6       | Temporizador con acción al finalizar                         | `TemporizadorLogica`<br>`TemporizadorVista`                    | Manual / Visual                           |
| RF7       | Finalizar juego por victoria o derrota                       | `SudokuControlador.comprobar()`<br>`Sudoku.estaResuelto()`     | `SudokuTest.testResolucion()`            |
| RF8       | Reiniciar o cambiar dificultad                               | `SudokuControlador.reiniciar()`<br>`cambiarDificultad()`       | `SudokuTest.testReinicio()`              |
| RNF1      | Desarrollado en Java 17 o superior                           | Proyecto Java 17 (`pom.xml`)                                   | Validación de entorno                    |
| RNF2      | Arquitectura modular MVC                                     | `vista/`, `modelo/`, `controlador/`                            | Revisión de estructura                    |
| RNF3      | Pruebas unitarias con JUnit                                  | `SudokuTest`, `GeneradorSudokuTest`                            | `mvn test`                                |
| RNF4      | Interfaz gráfica clara y usable                              | `SudokuWindow`, `SudokuPanel`, `ControlPanel`, `EstadoPanel`   | Manual / Visual                           |
| RNF5      | Código mantenible y comentado                                | Todas las clases documentadas                                  | Revisión de código                        |
| RNF6      | Ejecución fluida sin bloqueos                                | Eventos con `SwingUtilities.invokeLater`                       | Comportamiento observado                  |
| RNF7      | Manejo de errores con excepciones                            | `MovimientoInvalidoException`, `SudokuException`               | `SudokuTest.testExcepciones()`           |

