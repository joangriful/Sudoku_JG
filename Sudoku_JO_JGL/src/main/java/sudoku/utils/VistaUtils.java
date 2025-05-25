package sudoku.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaUtils {

    public static JButton crearBotonEstilizado(String texto) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(new Color(66, 106, 140));     // azul base
        boton.setForeground(Color.WHITE);                 // texto blanco
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(150, 40));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        aplicarHoverAzul(boton);

        return boton;
    }

    public static void aplicarHoverAzul(JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(91, 132, 165)); // azul hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(66, 106, 140)); // azul base
            }
        });
    }
}
