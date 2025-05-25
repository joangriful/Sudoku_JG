package sudoku;

import sudoku.vista.SudokuWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuWindow("medio"));
    }
}

