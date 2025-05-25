package sudoku.vista;

import javax.swing.*;
import java.awt.*;

public class TemporizadorVista {
    private final JLabel etiqueta;

    public TemporizadorVista() {
        this.etiqueta = new JLabel("Tiempo restante: 0:00", SwingConstants.CENTER);
        this.etiqueta.setFont(new Font("Monospaced", Font.BOLD, 16));
    }

    public void actualizarTiempo(int segundosRestantes) {
        int minutos = segundosRestantes / 60;
        int segundos = segundosRestantes % 60;
        String tiempo = String.format("Tiempo restante: %d:%02d", minutos, segundos);
        etiqueta.setText(tiempo);
    }

    public JLabel getEtiqueta() {
        return etiqueta;
    }
}
