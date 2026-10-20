package org.example;

import org.example.screen.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        try {
            Connection motor = new Connection();
            SwingUtilities.invokeLater(() -> new Window(motor).setVisible(true));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo cargar Prolog: " + ex.getMessage(),
                    "Error de inicio", JOptionPane.ERROR_MESSAGE);
        }
    }
}
