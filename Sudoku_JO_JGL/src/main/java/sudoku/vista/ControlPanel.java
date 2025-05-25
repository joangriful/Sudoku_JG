package sudoku.vista;

import sudoku.utils.VistaUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private final JButton btnPista;
    private final JButton btnComprobar;
    private final JButton btnReiniciar;
    private final JComboBox<String> dificultadCombo;

    public ControlPanel(String dificultadInicial) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(242, 246, 252));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] opciones = {"facil", "medio", "dificil"};
        dificultadCombo = new JComboBox<>(opciones);
        dificultadCombo.setSelectedItem(dificultadInicial);
        dificultadCombo.setMaximumSize(new Dimension(150, 35));
        dificultadCombo.setBackground(new Color(66, 106, 140));
        dificultadCombo.setForeground(Color.WHITE);
        dificultadCombo.setFont(new Font("SansSerif", Font.BOLD, 14));
        dificultadCombo.setFocusable(false);
        dificultadCombo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dificultadCombo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        dificultadCombo.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnPista = VistaUtils.crearBotonEstilizado("Pista");
        btnComprobar = VistaUtils.crearBotonEstilizado("Comprobar");
        btnReiniciar = VistaUtils.crearBotonEstilizado("Reiniciar");

        add(Box.createVerticalStrut(10));
        add(dificultadCombo);
        add(Box.createVerticalStrut(20));
        add(btnPista);
        add(Box.createVerticalStrut(10));
        add(btnComprobar);
        add(Box.createVerticalStrut(10));
        add(btnReiniciar);
    }

    public void setPistasRestantes(int cantidad) {
        btnPista.setText("Pista (" + cantidad + ")");
        btnPista.setEnabled(cantidad > 0);
    }

    public String getDificultadSeleccionada() {
        return (String) dificultadCombo.getSelectedItem();
    }

    public void addPistaListener(ActionListener listener) {
        btnPista.addActionListener(listener);
    }

    public void addComprobarListener(ActionListener listener) {
        btnComprobar.addActionListener(listener);
    }

    public void addReiniciarListener(ActionListener listener) {
        btnReiniciar.addActionListener(listener);
    }

    public void addDificultadListener(ActionListener listener) {
        dificultadCombo.addActionListener(listener);
    }
}
