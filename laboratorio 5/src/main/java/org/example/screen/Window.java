package org.example.screen;

import org.example.Connection;
import org.example.screen.components.Buttons;
import org.example.screen.components.PanelResult;
import org.example.screen.components.TextField;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;

// Mismo estilo y organización de Window.java de la Guía 5.
public class Window extends JFrame {
    private final TextField txtNumero1 = new TextField("Ej: 10");
    private final TextField txtNumero2 = new TextField("Ej: 5");
    private final PanelResult panelResultado = new PanelResult();
    private final JLabel lblEstado = new JLabel(" ");

    public Window(Connection motor) {
        super("Calculadora Java y Prolog");

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);

        agregar(contenido, etiqueta("Primer número"), 6);
        agregar(contenido, txtNumero1, 16);
        agregar(contenido, etiqueta("Segundo número"), 6);
        agregar(contenido, txtNumero2, 20);
        agregar(contenido, crearBotones(motor), 22);
        agregar(contenido, panelResultado, 12);
        agregar(contenido, lblEstado, 0);

        JPanel raiz = new JPanel(new BorderLayout(0, 20));
        raiz.setBackground(Theme.BG);
        raiz.setBorder(new EmptyBorder(24, 28, 24, 28));
        raiz.add(crearEncabezado(), BorderLayout.NORTH);
        raiz.add(contenido, BorderLayout.CENTER);

        setContentPane(raiz);
        setMinimumSize(new Dimension(570, 450));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        lblEstado.setFont(Theme.sans(Font.PLAIN, 12));
        lblEstado.setForeground(Theme.MUTED);

        // Al presionar Enter en cualquiera de los campos, suma por defecto.
        txtNumero1.addActionListener(e -> ejecutar(motor, "suma"));
        txtNumero2.addActionListener(e -> ejecutar(motor, "suma"));
    }

    private JPanel crearEncabezado() {
        JLabel titulo = new JLabel("Calculadora con Prolog");
        titulo.setFont(Theme.sans(Font.BOLD, 24));
        titulo.setForeground(Theme.TEXT);

        JLabel subtitulo = new JLabel("Suma y resta resueltas con reglas en Prolog");
        subtitulo.setFont(Theme.sans(Font.PLAIN, 13));
        subtitulo.setForeground(Theme.MUTED);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.add(titulo);
        p.add(Box.createVerticalStrut(4));
        p.add(subtitulo);
        return p;
    }

    private JPanel crearBotones(Connection motor) {
        Buttons btnSumar = new Buttons("Sumar", Theme.BLUE, Theme.BG, 26, 11, 14);
        Buttons btnRestar = new Buttons("Restar", Theme.GREEN, Theme.BG, 26, 11, 14);
        btnSumar.addActionListener(e -> ejecutar(motor, "suma"));
        btnRestar.addActionListener(e -> ejecutar(motor, "resta"));

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.setOpaque(false);
        p.add(btnSumar);
        p.add(btnRestar);
        return p;
    }

    private void ejecutar(Connection motor, String predicado) {
        String numero1 = txtNumero1.getText().trim();
        String numero2 = txtNumero2.getText().trim();
        String nombre = predicado.equals("suma") ? "Suma" : "Resta";
        panelResultado.setTitulo("Resultado de la " + nombre.toLowerCase());

        if (numero1.isEmpty() || numero2.isEmpty()) {
            mostrar("", "Escribí los dos números primero.", Theme.AMBER);
            return;
        }

        try {
            BigDecimal a = new BigDecimal(numero1);
            BigDecimal b = new BigDecimal(numero2);
            String resultado = motor.consultar(predicado, a, b);
            if (resultado.startsWith("Sin solución")) {
                mostrar("", resultado, Theme.AMBER);
            } else {
                mostrar(resultado, "Listo", Theme.GREEN);
            }
        } catch (NumberFormatException ex) {
            mostrar("", "Ingresá números válidos. Ejemplo: 12 o 3.5", Theme.RED);
        } catch (Exception ex) {
            mostrar("", "Error al consultar Prolog: " + ex.getMessage(), Theme.RED);
        }
    }

    private void mostrar(String resultado, String estado, Color colorEstado) {
        panelResultado.setTexto(resultado);
        lblEstado.setText(estado);
        lblEstado.setForeground(colorEstado);
    }

    private static void agregar(JPanel panel, JComponent c, int espacioDespues) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(c);
        if (espacioDespues > 0) {
            panel.add(Box.createVerticalStrut(espacioDespues));
        }
    }

    private static JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(Theme.sans(Font.BOLD, 12));
        l.setForeground(Theme.MUTED);
        return l;
    }
}
