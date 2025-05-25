package sudoku.controlador;

import sudoku.modelo.Sudoku;
import java.util.Scanner;

public class JuegoSudoku {

    public void iniciar() {


        Scanner scanner = new Scanner(System.in);
        Sudoku sudoku = new Sudoku();

        System.out.println("Selecciona dificultad (facil, medio, dificil):");
        String dificultad = scanner.nextLine();
        sudoku.generarTablero(dificultad);

        int pistas = switch (dificultad) {
            case "facil" -> 5;
            case "medio" -> 3;
            default -> 0;
        };

        long startTime = System.currentTimeMillis();

        while (true) {
            sudoku.mostrarTablero();

            long elapsed = (System.currentTimeMillis() - startTime) / 1000;
            if (elapsed >= 300) {
                System.out.println("⏱ Tiempo agotado. ¡Has perdido!");
                break;
            }

            System.out.println("Introduce fila, columna y valor (0 0 0 para salir):");
            int fila = scanner.nextInt(), columna = scanner.nextInt(), valor = scanner.nextInt();

            if (fila == 0 && columna == 0 && valor == 0) break;

            if (!sudoku.colocarNumero(fila - 1, columna - 1, valor))
                System.out.println("❌ Movimiento inválido.");
            else if (sudoku.estaResuelto()) {
                sudoku.mostrarTablero();
                System.out.println("✅ ¡Sudoku resuelto correctamente!");
                break;
            }
        }
    }
}

