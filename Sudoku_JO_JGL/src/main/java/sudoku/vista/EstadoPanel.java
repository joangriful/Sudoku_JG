package sudoku.vista;

import javax.swing.*;
import java.awt.*;

public class EstadoPanel extends JPanel {
    private final JLabel erroresLabel;
    private final JLabel mensajeLabel;

    public EstadoPanel() {
        setLayout(new GridLayout(2, 1));

        erroresLabel = new JLabel("Errores restantes: 0", SwingConstants.CENTER);
        erroresLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        erroresLabel.setForeground(Color.BLUE);

        mensajeLabel = new JLabel(" ", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        mensajeLabel.setForeground(Color.RED);

        add(erroresLabel);
        add(mensajeLabel);
    }

    public void setErroresRestantes(int errores) {
        erroresLabel.setText("Errores restantes: " + errores);
    }

    public void mostrarMensajeError(String mensaje) {
        mensajeLabel.setForeground(Color.RED);
        mensajeLabel.setText(mensaje);
    }

    public void mostrarMensajeExito(String mensaje) {
        mensajeLabel.setForeground(new Color(0, 128, 0));
        mensajeLabel.setText(mensaje);
    }

    public void mostrarMensajeInfo(String mensaje) {
        mensajeLabel.setForeground(new Color(0, 102, 204));
        mensajeLabel.setText(mensaje);
    }

    public JLabel getErroresLabel() {
        return erroresLabel;
    }

    public JLabel getMensajeLabel() {
        return mensajeLabel;
    }
}
